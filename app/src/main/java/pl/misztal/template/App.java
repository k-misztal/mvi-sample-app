package pl.misztal.template;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

import pl.misztal.template.di.component.AppComponent;
import pl.misztal.template.di.component.DaggerAppComponent;
import pl.misztal.template.di.module.AndroidModule;
import timber.log.Timber;

public class App extends Application {

    private static App app;
    private static AppComponent component;

    @Override
    public void onCreate() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }

        super.onCreate();
        app = this;

        initializeToothpick();
        initializeTimber();
        LeakCanary.install(this);
        // TODO: Add Crashlytics!
    }

    private void initializeToothpick() {
        component = DaggerAppComponent.builder()
                .androidModule(new AndroidModule(this))
                .build();
    }

    private void initializeTimber() {
        if (BuildConfig.DEBUG)
            Timber.plant(new Timber.DebugTree());

    }

    public static App get() {
        return app;
    }

    public static AppComponent component() {
        return component;
    }
}
