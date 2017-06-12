package pl.misztal.bonfire;

import android.content.Context;
import android.support.annotation.StringRes;

import java.net.UnknownHostException;

import javax.inject.Inject;
import javax.inject.Singleton;

import pl.misztal.bonfire.location.LocationScanningException;

@Singleton
public class ExceptionHandler {
    Context context;

    @Inject
    public ExceptionHandler(Context context) {
        this.context = context;
    }

    public String getMessageError(Throwable throwable) {
        if (throwable instanceof UnknownHostException) {
            return getString(R.string.no_internet);
        } else if (throwable instanceof LocationScanningException) {
            return getString(R.string.location_error);
        }

        return context.getString(R.string.something_went_wrong);
    }

    private String getString(@StringRes int res) {
        return context.getString(res);
    }
}
