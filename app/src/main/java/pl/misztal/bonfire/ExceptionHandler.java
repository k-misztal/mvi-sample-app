package pl.misztal.bonfire;

import android.content.Context;

import java.net.UnknownHostException;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ExceptionHandler {
    Context context;

    @Inject
    public ExceptionHandler(Context context) {
        this.context = context;
    }

    public String getMessageError(Throwable throwable) {
        if (throwable instanceof UnknownHostException) {
            return context.getString(R.string.no_internet);
        }

        return context.getString(R.string.something_went_wrong);
    }

}
