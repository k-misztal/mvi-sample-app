package pl.misztal.template;

import android.content.Context;

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
        return null;
    }

}
