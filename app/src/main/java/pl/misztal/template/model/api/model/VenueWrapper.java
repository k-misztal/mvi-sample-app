package pl.misztal.template.model.api.model;

/**
 * Created by kmisztal on 12.06.2017.
 *
 * @author Krzysztof Misztal
 */

public final class VenueWrapper {
    private final Venue venue;

    public VenueWrapper(Venue venue) {
        this.venue = venue;
    }

    public Venue getVenue() {
        return venue;
    }
}
