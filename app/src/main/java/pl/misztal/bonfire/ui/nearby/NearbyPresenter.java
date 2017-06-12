package pl.misztal.bonfire.ui.nearby;

import android.location.Location;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import pl.misztal.bonfire.di.scope.FragmentSingleton;
import pl.misztal.bonfire.location.LocationProvider;
import pl.misztal.bonfire.model.AdditionalItemsLoadable;
import pl.misztal.bonfire.model.DataManager;
import pl.misztal.bonfire.model.FeedItem;
import pl.misztal.bonfire.model.api.model.VenuesInfo;
import pl.misztal.bonfire.model.api.model.request.LatLng;
import pl.misztal.bonfire.model.api.model.response.Response;
import pl.misztal.bonfire.ui.base.BasePresenter;
import timber.log.Timber;

/**
 * Created by kmisztal on 11.06.2017.
 *
 * @author Krzysztof Misztal
 */

@FragmentSingleton
public class NearbyPresenter extends BasePresenter<NearbyView, NearbyViewState> {

    public static final int PAGE_LIMIT = 10;

    private final DataManager dataManager;
    private final LocationProvider locationProvider;
    private Location location;

    @Inject
    public NearbyPresenter(DataManager dataManager, LocationProvider locationProvider) {
        this.dataManager = dataManager;
        this.locationProvider = locationProvider;
    }

    @Override
    protected void bindIntents() {
        //get location and after that load first page
        Observable<PartialStateChanges> loadFirstPage = locationProvider.start()
                .firstOrError()
                .observeOn(Schedulers.io())
                .flatMap(location -> {
                    this.location = location;
                    return dataManager.api()
                            .getRecommendedVenues(new LatLng(location), location.getAccuracy(), PAGE_LIMIT, 0);
                })
                .map(Response::getResponse)
                .map(info -> (PartialStateChanges) new PartialStateChanges.FirstPageLoaded(info))
                .toObservable()
                .startWith(new PartialStateChanges.FirstPageLoading())
                .onErrorReturn(PartialStateChanges.FirstPageError::new);

        //loading next page
        Observable<PartialStateChanges> nextPage = intent(NearbyView::loadNextPageIntent)
                .flatMap(integer -> dataManager.api()
                        .getRecommendedVenues(new LatLng(location), location.getAccuracy(), PAGE_LIMIT, integer)
                        .map(Response::getResponse)
                        .map(venuesInfo -> (PartialStateChanges) new PartialStateChanges.NextPageLoaded(venuesInfo))
                        .toObservable()
                        .startWith(new PartialStateChanges.NextPageLoading())
                        .onErrorReturn(PartialStateChanges.NexPageLoadingError::new)
                        .subscribeOn(Schedulers.io()));

        Observable<PartialStateChanges> allIntentsObservable = Observable.merge(loadFirstPage, nextPage)
                .observeOn(AndroidSchedulers.mainThread());

        NearbyViewState initialState = new NearbyViewState.Builder().withWaitingForLocation(true).build();

        subscribeViewState(
                allIntentsObservable.scan(initialState, this::reduce),
                NearbyView::render);
    }

    private NearbyViewState reduce(NearbyViewState previous, PartialStateChanges stateChanges) {
        if (stateChanges instanceof PartialStateChanges.WaitingForLocation) {
            return previous.builder().withWaitingForLocation(true).build();
        }

        if (stateChanges instanceof PartialStateChanges.LocationError) {
            return previous.builder()
                    .withWaitingForLocation(false)
                    .withLocationError(((PartialStateChanges.LocationError) stateChanges).getError())
                    .build();
        }

        if (stateChanges instanceof PartialStateChanges.FirstPageLoading) {
            return previous.builder().withWaitingForLocation(false)
                    .withLoadingFirstPage(true).build();
        }

        if (stateChanges instanceof PartialStateChanges.FirstPageLoaded) {
            VenuesInfo info = ((PartialStateChanges.FirstPageLoaded) stateChanges).getData();
            List<FeedItem> data = new ArrayList<>(info.getAllVenues());
            if (info.getTotalResults() > data.size()) {
                addLoadable(data, new AdditionalItemsLoadable(info.getTotalResults() - data.size(), false, null));
            }

            return previous.builder().withWaitingForLocation(false)
                    .withLoadingFirstPage(false)
                    .withData(data)
                    .build();
        }

        if (stateChanges instanceof PartialStateChanges.FirstPageError) {
            return previous.builder().withLoadingFirstPage(false)
                    .withFirstPageError(((PartialStateChanges.FirstPageError) stateChanges).getError())
                    .build();
        }

        if (stateChanges instanceof PartialStateChanges.NextPageLoading) {
            List<FeedItem> data = previous.getData();
            addLoadable(data, new AdditionalItemsLoadable(0, true, null));
            return previous.builder().withData(data).build();
        }

        if (stateChanges instanceof PartialStateChanges.NextPageLoaded) {
            VenuesInfo info = ((PartialStateChanges.NextPageLoaded) stateChanges).getData();
            List<FeedItem> data = previous.getData();
            data.addAll(info.getAllVenues());

            if (info.getTotalResults() > data.size()) {
                addLoadable(data, new AdditionalItemsLoadable(info.getTotalResults() - data.size(), false, null));
            } else {
                addLoadable(data, null);
            }
            return previous.builder().withData(data).build();
        }

        if (stateChanges instanceof PartialStateChanges.NexPageLoadingError) {
            Throwable error = ((PartialStateChanges.NexPageLoadingError) stateChanges).getError();
            List<FeedItem> data = previous.getData();
            addLoadable(data, new AdditionalItemsLoadable(0, false, error));
            return previous.builder().withData(data).build();
        }

        throw new IllegalStateException("PartialStateChanges not supported: " + stateChanges.toString());
    }

    @Override
    protected void unbindIntents() {
        Timber.d("Cleaning up NearbyPresenter.");
        super.unbindIntents();
        locationProvider.stop();
    }

    //adds loadable at the end, replacing old one
    private static void addLoadable(List<FeedItem> list, AdditionalItemsLoadable loadable) {
        Iterator<FeedItem> iterator = list.iterator();
        while (iterator.hasNext()) {
            FeedItem item = iterator.next();
            if (item instanceof AdditionalItemsLoadable) {
                iterator.remove();
            }
        }

        if (loadable != null) {
            list.add(loadable);
        }
    }
}
