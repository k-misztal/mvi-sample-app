package pl.misztal.bonfire.model.api.model;

import java.util.List;

/**
 * Created by kmisztal on 11.06.2017.
 *
 * @author Krzysztof Misztal
 */

public final class Location {
    private final String address;
    private final double lat;
    private final double lng;
    private final float distance;
    private final List<String> formattedAddress;

    public Location(String address, double lat, double lng, float distance,
                    List<String> formattedAddress) {

        this.address = address;
        this.lat = lat;
        this.lng = lng;
        this.distance = distance;
        this.formattedAddress = formattedAddress;
    }

    public String getAddress() {
        return address;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public float getDistance() {
        return distance;
    }

    public List<String> getFormattedAddress() {
        return formattedAddress;
    }
}
