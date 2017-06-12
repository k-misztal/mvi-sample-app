package pl.misztal.bonfire.ui.nearby;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import jp.wasabeef.recyclerview.animators.LandingAnimator;
import pl.misztal.bonfire.ExceptionHandler;
import pl.misztal.bonfire.R;
import pl.misztal.bonfire.di.component.FragmentComponent;
import pl.misztal.bonfire.ui.base.BaseFragment;
import pl.misztal.bonfire.ui.nearby.adapter.NearbyAdapter;
import timber.log.Timber;

/**
 * Created by kmisztal on 11.06.2017.
 *
 * @author Krzysztof Misztal
 */

public class NearbyFragment extends BaseFragment<NearbyView, NearbyPresenter> implements NearbyView {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.error_message)
    TextView errorMessage;

    @Inject
    NearbyAdapter nearbyAdapter;

    @Inject
    ExceptionHandler exceptionHandler;

    @BindInt(R.integer.span_count)
    int spanCount;

    public static NearbyFragment newInstance() {
        NearbyFragment nearbyFragment = new NearbyFragment();
        nearbyFragment.setRetainInstance(true);
        return nearbyFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nearby, container, false);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), spanCount);

        recyclerView.setAdapter(nearbyAdapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new LandingAnimator());
    }

    @Override
    protected void inject(@NonNull FragmentComponent component) {
        component.inject(this);
    }

    @Override
    public Observable<Boolean> loadFirstPageIntent() {
        return Observable.just(true).doOnComplete(() -> Timber.d("Requested first page."));
    }

    @Override
    public Observable<Integer> loadNextPageIntent() {
        return nearbyAdapter.getLoadNewPageObservable();
    }

    @Override
    public void render(NearbyViewState viewState) {
        Timber.d("Rendering state:\n%s", viewState.toString());
        if (viewState.isLoadingFirstPage() || viewState.isLoadingFirstPage()) {
            progressBar.setVisibility(View.VISIBLE);
            errorMessage.setVisibility(View.GONE);
            recyclerView.setVisibility(View.INVISIBLE);
        } else if (viewState.getFirstPageError() != null) {
            String error = exceptionHandler.getMessageError(viewState.getFirstPageError());
            progressBar.setVisibility(View.GONE);
            errorMessage.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.INVISIBLE);
            errorMessage.setText(error);
        } else {
            progressBar.setVisibility(View.GONE);
            errorMessage.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            nearbyAdapter.setItems(viewState.getData());

            if (viewState.getData().isEmpty()) {
                errorMessage.setVisibility(View.VISIBLE);
                errorMessage.setText(R.string.no_places);
            }

        }
    }

    @NonNull
    @Override
    public NearbyPresenter createPresenter() {
        return getComponent().nearbyPresenter();
    }
}
