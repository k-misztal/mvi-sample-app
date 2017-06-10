package pl.misztal.template.model.api.model;

/**
 * Created by kmisztal on 11.06.2017.
 *
 * @author Krzysztof Misztal
 */

public final class Venue {
    private final String name;
    private final float rating;
    private final Stats stats;
    private final String url;
    private final Photos photos;

    public Venue(String name, float rating, Stats stats, String url, Photos photos) {
        this.name = name;
        this.rating = rating;
        this.stats = stats;
        this.url = url;
        this.photos = photos;
    }

    public String getName() {
        return name;
    }

    public float getRating() {
        return rating;
    }

    public Stats getStats() {
        return stats;
    }

    public String getUrl() {
        return url;
    }

    public Photos getPhotos() {
        return photos;
    }
}
