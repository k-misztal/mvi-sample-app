package pl.misztal.template.ui.nearby;

import java.util.List;

import pl.misztal.template.model.FeedItem;

/**
 * Created by kmisztal on 12.06.2017.
 *
 * @author Krzysztof Misztal
 */

public interface PartialStateChanges {

    /**
     * Indicates that app is waiting for location updates
     */
    final class WaitingForLocation implements PartialStateChanges {

        @Override
        public String toString() {
            return "WaitingForLocation{}";
        }
    }

    /**
     * Indicates that an error has occurred while obtaining locaiton
     */
    final class LocationError implements PartialStateChanges {
        private final Throwable error;

        public LocationError(Throwable error) {
            this.error = error;
        }

        public Throwable getError() {
            return error;
        }

        @Override
        public String toString() {
            return "LocationError{" +
                    "error=" + error +
                    '}';
        }
    }

    /**
     * Indicates that the first page is loading
     */
    final class FirstPageLoading implements PartialStateChanges {

        @Override
        public String toString() {
            return "FirstPageLoadingState{}";
        }
    }

    /**
     * Indicates that an error has occurred while loading the first page
     */
    final class FirstPageError implements PartialStateChanges {
        private final Throwable error;

        public FirstPageError(Throwable error) {
            this.error = error;
        }

        public Throwable getError() {
            return error;
        }

        @Override
        public String toString() {
            return "FirstPageErrorState{" +
                    "error=" + error +
                    '}';
        }
    }

    /**
     * Indicates that the first page data has been loaded successfully
     */
    final class FirstPageLoaded implements PartialStateChanges {
        private final List<FeedItem> data;

        public FirstPageLoaded(List<FeedItem> data) {
            this.data = data;
        }

        public List<FeedItem> getData() {
            return data;
        }
    }

    /**
     * Next Page has been loaded successfully
     */
    final class NextPageLoaded implements PartialStateChanges {
        private final List<FeedItem> data;

        public NextPageLoaded(List<FeedItem> data) {
            this.data = data;
        }

        public List<FeedItem> getData() {
            return data;
        }
    }

    /**
     * Error while loading new page
     */
    final class NexPageLoadingError implements PartialStateChanges {
        private final Throwable error;

        public NexPageLoadingError(Throwable error) {
            this.error = error;
        }

        public Throwable getError() {
            return error;
        }
    }

    /**
     * Indicates that loading the next page has started
     */
    final class NextPageLoading implements PartialStateChanges {
    }

}