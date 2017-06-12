package pl.misztal.template.ui.nearby;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import pl.misztal.template.di.scope.FragmentSingleton;
import pl.misztal.template.location.LocationProvider;
import pl.misztal.template.model.DataManager;
import pl.misztal.template.model.api.model.request.LatLng;
import pl.misztal.template.ui.base.BasePresenter;
import timber.log.Timber;

/**
 * Created by kmisztal on 11.06.2017.
 *
 * @author Krzysztof Misztal
 */

@FragmentSingleton
public class NearbyPresenter extends BasePresenter<NearbyView, NearbyViewState> {

    public static final float MINIMUM_ACCURACY = 500;

    private final DataManager dataManager;
    private final LocationProvider locationProvider;

    @Inject
    public NearbyPresenter(DataManager dataManager, LocationProvider locationProvider) {
        this.dataManager = dataManager;
        this.locationProvider = locationProvider;
    }

    @Override
    protected void bindIntents() {
        Observable<PartialStateChanges> loadFirstPage = locationProvider.start()
//                .doOnNext(location -> Timber.d("New location with precision: %f", location.getAccuracy()))
//                .filter(location -> location.getAccuracy() < 500)
                .firstOrError()
                .observeOn(Schedulers.io())
                .flatMap(location -> dataManager.api().getRecommendedVenues(new LatLng(location), location.getAccuracy()))
                .map(venuesInfoResponse -> venuesInfoResponse.getResponse().getAllVenues())
                .map(venues -> (PartialStateChanges) new PartialStateChanges.FirstPageLoaded(new ArrayList<>(venues)))
                .toObservable()
                .startWith(new PartialStateChanges.FirstPageLoading())
                .onErrorReturn(PartialStateChanges.FirstPageError::new);

        // TODO: 12.06.2017 next page loading
        Observable<PartialStateChanges> allIntentsObservable = loadFirstPage
                .observeOn(AndroidSchedulers.mainThread());

        NearbyViewState initialState = new NearbyViewState.Builder().withWaitingForLocation(true).build();

        subscribeViewState(
                allIntentsObservable.scan(initialState, this::reduce).distinctUntilChanged(),
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
            return previous.builder().withWaitingForLocation(false)
                    .withLoadingFirstPage(false)
                    .withData(((PartialStateChanges.FirstPageLoaded) stateChanges).getData())
                    .build();
        }

        if (stateChanges instanceof PartialStateChanges.FirstPageError) {
            return previous.builder().withLoadingFirstPage(false)
                    .withFirstPageError(((PartialStateChanges.FirstPageError) stateChanges).getError())
                    .build();
        }

        throw new IllegalStateException("PartialStateChanges not supported: " + stateChanges.toString());
    }

    @Override
    protected void unbindIntents() {
        Timber.d("Cleaning up NearbyPresenter.");
        super.unbindIntents();
        locationProvider.stop();
    }
}
