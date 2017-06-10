package pl.misztal.template.di.module;

import dagger.Module;
import dagger.Provides;
import pl.misztal.template.model.DataManager;
import pl.misztal.template.model.api.RestService;
import pl.misztal.template.model.database.DatabaseFacade;

@Module
public class DataModule {

    @Provides
    DataManager provideDataManager(RestService restService, DatabaseFacade databaseFacade) {
        return new DataManager(databaseFacade, restService);
    }

}
