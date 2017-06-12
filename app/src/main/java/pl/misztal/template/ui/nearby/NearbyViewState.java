package pl.misztal.template.ui.nearby;

import java.util.Collections;
import java.util.List;

import pl.misztal.template.model.FeedItem;
import pl.misztal.template.ui.base.ViewState;

public final class NearbyViewState implements ViewState {

    private final boolean waitingForLocation; //can be true initially when location is unknown or not accurate enough
    private final boolean loadingFirstPage; //loading first page
    private final Throwable firstPageError;
    private final Throwable locationError;

    private final List<FeedItem> data; //data to be displayed

    private NearbyViewState(boolean waitingForLocation, boolean loadingFirstPage,
                            Throwable firstPageError, Throwable locationError, List<FeedItem> data) {

        this.waitingForLocation = waitingForLocation;
        this.loadingFirstPage = loadingFirstPage;
        this.firstPageError = firstPageError;
        this.locationError = locationError;
        this.data = data;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NearbyViewState that = (NearbyViewState) o;

        if (waitingForLocation != that.waitingForLocation) return false;
        if (loadingFirstPage != that.loadingFirstPage) return false;
        if (firstPageError != null ? !firstPageError.equals(that.firstPageError) : that.firstPageError != null)
            return false;
        return data != null ? data.equals(that.data) : that.data == null;

    }

    @Override
    public int hashCode() {
        int result = (waitingForLocation ? 1 : 0);
        result = 31 * result + (loadingFirstPage ? 1 : 0);
        result = 31 * result + (firstPageError != null ? firstPageError.hashCode() : 0);
        result = 31 * result + (locationError != null ? locationError.hashCode() : 0);
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "NearbyViewState{" +
                "waitingForLocation=" + waitingForLocation +
                ", loadingFirstPage=" + loadingFirstPage +
                ", firstPageError=" + firstPageError +
                ", locationError=" + locationError +
                ", dataSize=" + data.size() +
                '}';
    }

    public Builder builder() {
        return new Builder(this);
    }

    public static final class Builder {
        private boolean waitingForLocation;
        private boolean loadingFirstPage;
        private Throwable firstPageError;
        private Throwable locationError;
        private List<FeedItem> data;

        public Builder() {
            data = Collections.emptyList();
        }

        public Builder(NearbyViewState copy) {
            this.waitingForLocation = copy.waitingForLocation;
            this.loadingFirstPage = copy.loadingFirstPage;
            this.firstPageError = copy.firstPageError;
            this.data = copy.data;
            this.locationError = copy.locationError;
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

        public Builder withLocationError(Throwable locationError) {
            this.locationError = locationError;
            return this;
        }

        public NearbyViewState build() {
            return new NearbyViewState(waitingForLocation, loadingFirstPage, firstPageError, locationError, data);
        }
    }
}
