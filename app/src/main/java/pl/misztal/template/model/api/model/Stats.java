package pl.misztal.template.model.api.model;

/**
 * Created by kmisztal on 11.06.2017.
 *
 * @author Krzysztof Misztal
 */

public final class Stats {
    private final int checkinsCount;
    private final int usersCount;
    private final int tipCount;

    public Stats(int checkinsCount, int usersCount, int tipCount) {
        this.checkinsCount = checkinsCount;
        this.usersCount = usersCount;
        this.tipCount = tipCount;
    }

    public int getCheckinsCount() {
        return checkinsCount;
    }

    public int getUsersCount() {
        return usersCount;
    }

    public int getTipCount() {
        return tipCount;
    }

    @Override
    public String toString() {
        return "Stats{" +
                "checkinsCount=" + checkinsCount +
                ", usersCount=" + usersCount +
                ", tipCount=" + tipCount +
                '}';
    }
}
