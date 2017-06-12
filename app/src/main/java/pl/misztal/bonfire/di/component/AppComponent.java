package pl.misztal.bonfire.di.component;

import android.content.Context;
import android.view.LayoutInflater;

import javax.inject.Singleton;

import dagger.Component;
import pl.misztal.bonfire.ExceptionHandler;
import pl.misztal.bonfire.di.module.AndroidModule;
import pl.misztal.bonfire.di.module.DataModule;
import pl.misztal.bonfire.di.module.LocationModule;
import pl.misztal.bonfire.di.module.NetworkModule;
import pl.misztal.bonfire.location.LocationProvider;
import pl.misztal.bonfire.model.DataManager;

/**
 * Created by kmisztal on 10.06.2017.
 *
 * @author Krzysztof Misztal
 */

@Singleton
@Component(modules = {DataModule.class, NetworkModule.class, AndroidModule.class, LocationModule.class})
public interface AppComponent {
    DataManager dataManager();

    Context context();

    ExceptionHandler exceptionHandler();

    LayoutInflater layoutInflater();

    LocationProvider locationProvider();
}
