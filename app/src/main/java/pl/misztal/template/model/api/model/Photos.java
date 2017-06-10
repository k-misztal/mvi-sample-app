package pl.misztal.template.model.api.model;

import android.support.annotation.Nullable;

import java.util.List;

/**
 * Created by kmisztal on 11.06.2017.
 *
 * @author Krzysztof Misztal
 */

public class Photos {
    private final List<ImageGroup> groups;

    public Photos(List<ImageGroup> groups) {
        this.groups = groups;
    }

    public List<ImageGroup> getGroups() {
        return groups;
    }

    /**
     * @return default image, which is first venue image, or if not present first checkin photo.
     */
    @Nullable
    public Image getDefault() {
        ImageGroup venue = getVenueGroup();
        ImageGroup checkin = getCheckinGroup();

        if (venue != null && venue.getFirst() != null) {
            return venue.getFirst();

        } else if (checkin != null) {
            return checkin.getFirst();

        } else {
            return null;
        }
    }

    @Nullable
    private ImageGroup getCheckinGroup() {
        if (groups != null) {
            for (ImageGroup group : groups) {
                if (group.getType().equals(ImageGroup.GROUP_CHECKIN))
                    return group;
            }
        }

        return null;
    }

    @Nullable
    private ImageGroup getVenueGroup() {
        if (groups != null) {
            for (ImageGroup group : groups) {
                if (group.getType().equals(ImageGroup.GROUP_VENUE))
                    return group;
            }
        }

        return null;
    }
}
