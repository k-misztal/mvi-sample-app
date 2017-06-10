package pl.misztal.template.model.api.model;

import android.support.annotation.Nullable;

import java.util.List;

/**
 * Created by kmisztal on 11.06.2017.
 *
 * @author Krzysztof Misztal
 */

public final class ImageGroup {
    public static final String GROUP_VENUE = "venue";
    public static final String GROUP_CHECKIN = "checkin";

    private final String type;
    private final List<Image> items;

    public ImageGroup(String type, List<Image> items) {
        this.type = type;
        this.items = items;
    }

    public String getType() {
        return type;
    }

    public List<Image> getItems() {
        return items;
    }

    public boolean hasItems() {
        return items != null && !items.isEmpty();
    }

    @Nullable
    public Image getFirst() {
        if (items != null && !items.isEmpty()) {
            return items.get(0);
        }

        return null;
    }
}
