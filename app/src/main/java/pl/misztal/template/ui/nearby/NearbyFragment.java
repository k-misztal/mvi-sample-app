package pl.misztal.template.ui.nearby;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import jp.wasabeef.recyclerview.animators.LandingAnimator;
import pl.misztal.template.R;
import pl.misztal.template.di.component.FragmentComponent;
import pl.misztal.template.ui.base.BaseFragment;
import pl.misztal.template.ui.nearby.adapter.NearbyAdapter;
import timber.log.Timber;

/**
 * Created by kmisztal on 11.06.2017.
 *
 * @author Krzysztof Misztal
 */

public class NearbyFragment extends BaseFragment<NearbyView, NearbyPresenter> implements NearbyView {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Inject
    NearbyAdapter nearbyAdapter;

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
        // TODO: 11.06.2017 spacing

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
        // TODO: 11.06.2017 proper impl!
        nearbyAdapter.setItems(viewState.getData());
    }

    @NonNull
    @Override
    public NearbyPresenter createPresenter() {
        return getComponent().nearbyPresenter();
    }
}
