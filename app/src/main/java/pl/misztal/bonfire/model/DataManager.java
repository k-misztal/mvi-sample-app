package pl.misztal.bonfire.model;

import javax.inject.Inject;
import javax.inject.Singleton;

import pl.misztal.bonfire.model.api.FoursquareApi;

@Singleton
public class DataManager {

    private FoursquareApi foursquareApi;

    @Inject
    public DataManager(FoursquareApi foursquareApi) {
        this.foursquareApi = foursquareApi;
    }


    public FoursquareApi api() {
        return foursquareApi;
    }

    //public Database db(){...}
}