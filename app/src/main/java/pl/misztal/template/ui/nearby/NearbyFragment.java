package pl.misztal.template.ui.nearby;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.ButterKnife;
import io.reactivex.Observable;
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

    @Inject
    NearbyAdapter nearbyAdapter;

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
    protected void inject(@NonNull FragmentComponent component) {
        component.inject(this);
    }

    @Override
    public Observable<Boolean> loadFirstPageIntent() {
        return Observable.just(true).doOnComplete(() -> Timber.d("Requested first page."));
    }

    @Override
    public Observable<Boolean> loadNextPageIntent() {
        return null;
    }

    @Override
    public void render(NearbyViewState viewState) {

    }

    @NonNull
    @Override
    public NearbyPresenter createPresenter() {
        return getComponent().nearbyPresenter();
    }
}
