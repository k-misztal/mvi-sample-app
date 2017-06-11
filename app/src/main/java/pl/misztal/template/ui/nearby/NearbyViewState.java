package pl.misztal.template.ui.nearby;

import java.util.Collections;
import java.util.List;

import pl.misztal.template.model.FeedItem;

public final class NearbyViewState {
    private final boolean waitingForLocation; //can be true initially when location is unknown or not accurate enough

    private final boolean loadingFirstPage; //loading first page
    private final Throwable firstPageError;

    private final List<FeedItem> data; //data to be displayed

    private final boolean loadingNextPage;
    private final Throwable nextPageError;

    private NearbyViewState(boolean waitingForLocation, boolean loadingFirstPage,
                            Throwable firstPageError, List<FeedItem> data, boolean loadingNextPage,
                            Throwable nextPageError) {

        this.waitingForLocation = waitingForLocation;
        this.loadingFirstPage = loadingFirstPage;
        this.firstPageError = firstPageError;
        this.data = data;
        this.loadingNextPage = loadingNextPage;
        this.nextPageError = nextPageError;
    }

    public boolean isWaitingForLocation() {
        return waitingForLocation;
    }

    public boolean isLoadingFirstPage() {
        return loadingFirstPage;
    }

    public Throwable getFirstPageError() {
        return firstPageError;
    }

    public List<FeedItem> getData() {
        return data;
    }

    public boolean isLoadingNextPage() {
        return loadingNextPage;
    }

    public Throwable getNextPageError() {
        return nextPageError;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NearbyViewState that = (NearbyViewState) o;

        if (waitingForLocation != that.waitingForLocation) return false;
        if (loadingFirstPage != that.loadingFirstPage) return false;
        if (loadingNextPage != that.loadingNextPage) return false;
        if (firstPageError != null ? !firstPageError.equals(that.firstPageError) : that.firstPageError != null)
            return false;
        if (data != null ? !data.equals(that.data) : that.data != null) return false;
        return nextPageError != null ? nextPageError.equals(that.nextPageError) : that.nextPageError == null;

    }

    @Override
    public int hashCode() {
        int result = (waitingForLocation ? 1 : 0);
        result = 31 * result + (loadingFirstPage ? 1 : 0);
        result = 31 * result + (firstPageError != null ? firstPageError.hashCode() : 0);
        result = 31 * result + (data != null ? data.hashCode() : 0);
        result = 31 * result + (loadingNextPage ? 1 : 0);
        result = 31 * result + (nextPageError != null ? nextPageError.hashCode() : 0);
        return result;
    }


    public static final class Builder {
        private boolean waitingForLocation;
        private boolean loadingFirstPage;
        private Throwable firstPageError;
        private List<FeedItem> data;
        private boolean loadingNextPage;
        private Throwable nextPageError;

        public Builder() {
            data = Collections.emptyList();
        }

        public Builder(NearbyViewState copy) {
            this.waitingForLocation = copy.waitingForLocation;
            this.loadingFirstPage = copy.loadingFirstPage;
            this.firstPageError = copy.firstPageError;
            this.data = copy.data;
            this.loadingNextPage = copy.loadingNextPage;
            this.nextPageError = copy.nextPageError;
        }

        public Builder withWaitingForLocation(boolean val) {
            waitingForLocation = val;
            return this;
        }

        public Builder withNewLocation(String val) {
            return this;
        }

        public Builder withLoadingFirstPage(boolean val) {
            loadingFirstPage = val;
            return this;
        }

        public Builder withFirstPageError(Throwable val) {
            firstPageError = val;
            return this;
        }

        public Builder withData(List<FeedItem> val) {
            data = val;
            return this;
        }

        public Builder withLoadingNextPage(boolean val) {
            loadingNextPage = val;
            return this;
        }

        public Builder withNextPageError(Throwable val) {
            nextPageError = val;
            return this;
        }

        public NearbyViewState build() {
            return new NearbyViewState(waitingForLocation, loadingFirstPage, firstPageError,
                    data, loadingNextPage, nextPageError);
        }
    }
}
