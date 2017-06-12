package pl.misztal.template.di.component;

import android.content.Context;
import android.view.LayoutInflater;

import dagger.Component;
import pl.misztal.template.ExceptionHandler;
import pl.misztal.template.di.scope.FragmentSingleton;
import pl.misztal.template.location.LocationProvider;
import pl.misztal.template.model.DataManager;
import pl.misztal.template.ui.nearby.NearbyFragment;
import pl.misztal.template.ui.nearby.NearbyPresenter;

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
