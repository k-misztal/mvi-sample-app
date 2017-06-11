package pl.misztal.template.model.api.model.request;

import java.util.Locale;

/**
 * Created by kmisztal on 11.06.2017.
 *
 * @author Krzysztof Misztal
 */

public final class LatLng {
    private final double lat;
    private final double lng;

    public LatLng(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    @Override
    public String toString() {
        return String.format(Locale.ENGLISH, "%.1f,%.1f", lat, lng);
    }
}
