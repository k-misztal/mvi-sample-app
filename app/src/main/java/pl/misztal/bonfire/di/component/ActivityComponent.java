package pl.misztal.bonfire.di.component;

import android.content.Context;
import android.view.LayoutInflater;

import dagger.Component;
import pl.misztal.bonfire.ExceptionHandler;
import pl.misztal.bonfire.di.scope.ActivitySingleton;
import pl.misztal.bonfire.location.LocationProvider;
import pl.misztal.bonfire.model.DataManager;

/**
 * Created by kmisztal on 10.06.2017.
 *
 * @author Krzysztof Misztal
 */
@ActivitySingleton
@Component(dependencies = AppComponent.class)
public interface ActivityComponent {
    DataManager dataManager();

    Context context();

    ExceptionHandler exceptionHandler();

    LayoutInflater layoutInflater();

    LocationProvider locationProvider();
}
