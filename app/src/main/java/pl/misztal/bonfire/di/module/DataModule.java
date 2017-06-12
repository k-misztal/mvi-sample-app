package pl.misztal.bonfire.di.module;

import dagger.Module;
import dagger.Provides;
import pl.misztal.bonfire.model.DataManager;
import pl.misztal.bonfire.model.api.FoursquareApi;

@Module
public class DataModule {

    @Provides
    DataManager provideDataManager(FoursquareApi foursquareApi) {
        return new DataManager(foursquareApi);
    }

}
