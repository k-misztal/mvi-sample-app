package pl.misztal.template.di.component;

import android.content.Context;
import android.view.LayoutInflater;

import javax.inject.Singleton;

import dagger.Component;
import pl.misztal.template.di.module.AndroidModule;
import pl.misztal.template.di.module.DataModule;
import pl.misztal.template.di.module.NetworkModule;
import pl.misztal.template.di.module.SchedulerModule;
import pl.misztal.template.model.DataManager;

/**
 * Created by kmisztal on 10.06.2017.
 *
 * @author Krzysztof Misztal
 */

@Singleton
@Component(modules = {DataModule.class, NetworkModule.class, SchedulerModule.class,
        AndroidModule.class})
public interface AppComponent {
    DataManager dataManager();

    Context context();

    LayoutInflater layoutInflater();
}
