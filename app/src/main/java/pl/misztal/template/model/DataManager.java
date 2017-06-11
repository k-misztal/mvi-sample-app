package pl.misztal.template.model;

import javax.inject.Inject;
import javax.inject.Singleton;

import pl.misztal.template.model.api.FoursquareApi;

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