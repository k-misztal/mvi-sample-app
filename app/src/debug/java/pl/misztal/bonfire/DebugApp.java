package pl.misztal.bonfire;

import static com.facebook.stetho.Stetho.defaultInspectorModulesProvider;
import static com.facebook.stetho.Stetho.initialize;
import static com.facebook.stetho.Stetho.newInitializerBuilder;

public class DebugApp extends App {

    @Override
    public void onCreate() {
        super.onCreate();
        initialize(newInitializerBuilder(this)
                .enableWebKitInspector(defaultInspectorModulesProvider(this))
                .build());
    }

}
