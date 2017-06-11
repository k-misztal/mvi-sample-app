package pl.misztal.template.di.module;

import android.content.Context;
import android.view.LayoutInflater;

import dagger.Module;
import dagger.Provides;

/**
 * Created by kmisztal on 11.06.2017.
 *
 * @author Krzysztof Misztal
 */

@Module
public class AndroidModule {
    private final Context context;

    public AndroidModule(Context context) {
        this.context = context.getApplicationContext();
    }

    @Provides
    Context provideApplicationContext() {
        return context;
    }

    @Provides
    LayoutInflater provideLayoutInflater(Context context) {
        return LayoutInflater.from(context);
    }

}
