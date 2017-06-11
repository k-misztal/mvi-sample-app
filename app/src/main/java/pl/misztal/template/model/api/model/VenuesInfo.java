package pl.misztal.template.model.api.model;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by kmisztal on 11.06.2017.
 *
 * @author Krzysztof Misztal
 */

public class VenuesInfo {

    private final float suggestedRadius;
    private final String headerLocation;
    private final int totalResults;
    private final List<Group<Venue>> groups;

    public VenuesInfo(float suggestedRadius, String headerLocation, int totalResults,
                      List<Group<Venue>> groups) {

        this.suggestedRadius = suggestedRadius;
        this.headerLocation = headerLocation;
        this.totalResults = totalResults;
        this.groups = groups;
    }

    public float getSuggestedRadius() {
        return suggestedRadius;
    }

    public String getHeaderLocation() {
        return headerLocation;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public List<Group<Venue>> getGroups() {
        return groups;
    }

    /**
     * @return list of all venues in all groups
     */
    @NonNull
    public List<Venue> getAllVenues() {
        if (groups == null) {
            return Collections.emptyList();
        }

        List<Venue> venues = new ArrayList<>();
        for (Group<Venue> group : groups) {
            venues.addAll(group.getItems());
        }

        return venues;
    }
}
