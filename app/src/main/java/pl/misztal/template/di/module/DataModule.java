package pl.misztal.template.di.module;

import dagger.Module;
import dagger.Provides;
import pl.misztal.template.model.DataManager;
import pl.misztal.template.model.api.FoursquareApi;
import pl.misztal.template.model.database.DatabaseFacade;

@Module
public class DataModule {

    @Provides
    DataManager provideDataManager(FoursquareApi foursquareApi, DatabaseFacade databaseFacade) {
        return new DataManager(databaseFacade, foursquareApi);
    }

}
