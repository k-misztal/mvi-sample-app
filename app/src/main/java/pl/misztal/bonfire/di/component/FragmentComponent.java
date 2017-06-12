package pl.misztal.bonfire.di.component;

import android.content.Context;
import android.view.LayoutInflater;

import dagger.Component;
import pl.misztal.bonfire.ExceptionHandler;
import pl.misztal.bonfire.di.scope.FragmentSingleton;
import pl.misztal.bonfire.location.LocationProvider;
import pl.misztal.bonfire.model.DataManager;
import pl.misztal.bonfire.ui.nearby.NearbyFragment;
import pl.misztal.bonfire.ui.nearby.NearbyPresenter;

/**
 * Created by kmisztal on 10.06.2017.
 *
 * @author Krzysztof Misztal
 */
@FragmentSingleton
@Component(dependencies = ActivityComponent.class)
public interface FragmentComponent {

    DataManager dataManager();

    Context context();

    LayoutInflater layoutInflater();

    NearbyPresenter nearbyPresenter();

    LocationProvider locationProvider();

    ExceptionHandler exceptionHandler();

    void inject(NearbyFragment nearbyFragment);
}
