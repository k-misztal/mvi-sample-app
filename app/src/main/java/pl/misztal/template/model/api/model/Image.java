package pl.misztal.template.model.api.model;

/**
 * Created by kmisztal on 11.06.2017.
 *
 * @author Krzysztof Misztal
 */

public final class Image {
    public final static String DEFAULT_SIZE = "cap512";

    private final String prefix;
    private final String suffix;

    public Image(String prefix, String suffix) {
        this.prefix = prefix;
        this.suffix = suffix;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public String getUrl() {
        return prefix + DEFAULT_SIZE + suffix;
    }

    /**
     * @param size size modifier
     * @see <a href="https://developer.foursquare.com/docs/responses/photo">docs</a>
     */
    public String getUrl(String size) {
        return prefix + size + suffix;
    }

}
