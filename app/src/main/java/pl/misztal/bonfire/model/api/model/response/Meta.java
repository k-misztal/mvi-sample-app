package pl.misztal.bonfire.model.api.model.response;

/**
 * Created by kmisztal on 11.06.2017.
 *
 * @author Krzysztof Misztal
 */

public final class Meta {
    private final int code;

    public Meta(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
