package pl.misztal.template.model.api.model;

import android.support.annotation.Nullable;

import pl.misztal.template.model.FeedItem;

/**
 * Created by kmisztal on 11.06.2017.
 *
 * @author Krzysztof Misztal
 */

public final class Venue implements FeedItem {
    private final String id;
    private final String name;
    private final float rating;
    private final Stats stats;
    private final String url;
    private final Photos photos;

    public Venue(String id, String name, float rating, Stats stats, String url, Photos photos) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.stats = stats;
        this.url = url;
        this.photos = photos;
    }

    public String getId() {
        return id;
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

    @Nullable
    public Image getDefaultPhoto() {
        if (photos != null) {
            return photos.getDefault();
        }

        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Venue venue = (Venue) o;

        if (Float.compare(venue.rating, rating) != 0) return false;
        if (id != null ? !id.equals(venue.id) : venue.id != null) return false;
        if (name != null ? !name.equals(venue.name) : venue.name != null) return false;
        return url != null ? url.equals(venue.url) : venue.url == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (rating != +0.0f ? Float.floatToIntBits(rating) : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }
}
