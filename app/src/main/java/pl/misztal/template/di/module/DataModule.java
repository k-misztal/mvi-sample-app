package pl.misztal.template.di.module;

import dagger.Module;
import dagger.Provides;
import pl.misztal.template.model.DataManager;
import pl.misztal.template.model.api.FoursquareApi;

@Module
public class DataModule {

    @Provides
    DataManager provideDataManager(FoursquareApi foursquareApi) {
        return new DataManager(foursquareApi);
    }

}
