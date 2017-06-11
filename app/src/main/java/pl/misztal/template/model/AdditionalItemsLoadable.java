package pl.misztal.template.model;

import android.support.annotation.Nullable;

/**
 * Created by kmisztal on 11.06.2017.
 *
 * @author Krzysztof Misztal
 */

public final class AdditionalItemsLoadable implements FeedItem {
    private final int moreItemsAvailableCount;
    private final boolean loading;
    private final Throwable loadingError;

    public AdditionalItemsLoadable(int moreItemsAvailableCount, boolean loading,
                                   @Nullable Throwable loadingError) {

        this.moreItemsAvailableCount = moreItemsAvailableCount;
        this.loading = loading;
        this.loadingError = loadingError;
    }

    public int getMoreItemsCount() {
        return moreItemsAvailableCount;
    }

    public int getMoreItemsAvailableCount() {
        return moreItemsAvailableCount;
    }

    public boolean isLoading() {
        return loading;
    }

    public Throwable getLoadingError() {
        return loadingError;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdditionalItemsLoadable that = (AdditionalItemsLoadable) o;

        if (moreItemsAvailableCount != that.moreItemsAvailableCount) return false;
        if (loading != that.loading) return false;
        return loadingError != null ? loadingError.getClass().equals(that.loadingError.getClass())
                : that.loadingError == null;
    }

    @Override
    public int hashCode() {
        int result = moreItemsAvailableCount;
        result = 31 * result + (loading ? 1 : 0);
        result = 31 * result + (loadingError != null ? loadingError.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AdditionalItemsLoadable{" +
                "moreItemsAvailableCount=" + moreItemsAvailableCount +
                ", loading=" + loading +
                ", loadingError=" + loadingError +
                '}';
    }
}
