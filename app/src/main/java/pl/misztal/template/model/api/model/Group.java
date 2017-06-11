package pl.misztal.template.model.api.model;

import android.support.annotation.Nullable;

import java.util.List;

/**
 * Created by kmisztal on 11.06.2017.
 *
 * @author Krzysztof Misztal
 */

public class Group<T> {
    private final String type;
    private final String name;
    private final List<T> items;

    public Group(String type, String name, List<T> items) {
        this.type = type;
        this.name = name;
        this.items = items;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public List<T> getItems() {
        return items;
    }

    public boolean hasItems() {
        return items != null && !items.isEmpty();
    }

    /**
     * @return first item of group
     */
    @Nullable
    public T getFirst() {
        if (items != null && !items.isEmpty()) {
            return items.get(0);
        }

        return null;
    }
}
