package pl.misztal.template.model;

import javax.inject.Inject;
import javax.inject.Singleton;

import pl.misztal.template.model.api.FoursquareApi;
import pl.misztal.template.model.database.DatabaseFacade;

@Singleton
public class DataManager {

    private DatabaseFacade databaseFacade;
    private FoursquareApi foursquareApi;

    @Inject
    public DataManager(DatabaseFacade databaseFacade, FoursquareApi foursquareApi) {
        this.databaseFacade = databaseFacade;
        this.foursquareApi = foursquareApi;
    }

    public DatabaseFacade db() {
        return databaseFacade;
    }

    public FoursquareApi api() {
        return foursquareApi;
    }
}