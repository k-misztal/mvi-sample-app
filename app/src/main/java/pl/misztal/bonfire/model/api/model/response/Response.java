package pl.misztal.bonfire.model.api.model.response;

/**
 * Generic class for FoursquareResponse
 *
 * @author Krzysztof Misztal
 */

public class Response<T> {
    private final Meta meta;
    private final T response;

    public Response(Meta meta, T response) {
        this.meta = meta;
        this.response = response;
    }

    public Meta getMeta() {
        return meta;
    }

    public T getResponse() {
        return response;
    }
}
