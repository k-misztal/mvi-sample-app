package pl.misztal.bonfire.ui.nearby;

import pl.misztal.bonfire.model.api.model.VenuesInfo;

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
        private final VenuesInfo data;

        public FirstPageLoaded(VenuesInfo venuesInfo) {
            this.data = venuesInfo;
        }

        public VenuesInfo getData() {
            return data;
        }
    }

    /**
     * Next Page has been loaded successfully
     */
    final class NextPageLoaded implements PartialStateChanges {
        private final VenuesInfo data;

        public NextPageLoaded(VenuesInfo data) {
            this.data = data;
        }

        public VenuesInfo getData() {
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