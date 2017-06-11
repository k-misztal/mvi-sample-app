package pl.misztal.template.model.api.model;

import android.support.annotation.Nullable;

import java.util.List;

/**
 * Created by kmisztal on 11.06.2017.
 *
 * @author Krzysztof Misztal
 */

public class Photos {
    public static final String GROUP_VENUE = "venue";
    public static final String GROUP_CHECKIN = "checkin";

    private final List<Group<Image>> groups;

    public Photos(List<Group<Image>> groups) {
        this.groups = groups;
    }

    public List<Group<Image>> getGroups() {
        return groups;
    }

    /**
     * @return default image, which is first venue image, or if not present first checkin photo.
     */
    @Nullable
    public Image getDefault() {
        Group<Image> venue = getVenueGroup();
        Group<Image> checkin = getCheckinGroup();

        if (venue != null && venue.getFirst() != null) {
            return venue.getFirst();

        } else if (checkin != null) {
            return checkin.getFirst();

        } else {
            return null;
        }
    }

    @Nullable
    private Group<Image> getCheckinGroup() {
        if (groups != null) {
            for (Group<Image> group : groups) {
                if (group.getType().equals(GROUP_CHECKIN))
                    return group;
            }
        }

        return null;
    }

    @Nullable
    private Group<Image> getVenueGroup() {
        if (groups != null) {
            for (Group<Image> group : groups) {
                if (group.getType().equals(GROUP_VENUE))
                    return group;
            }
        }

        return null;
    }
}
