package pl.misztal.bonfire.di.module;

import android.content.Context;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.misztal.bonfire.location.LocationProvider;
import pl.misztal.bonfire.location.PlayServicesLocationProvider;

/**
 * Created by kmisztal on 11.06.2017.
 *
 * @author Krzysztof Misztal
 */
@Module
public class LocationModule {

    @Provides
    @Singleton
    LocationProvider provideLocationProvider(GoogleApiClient apiClient) {
        return new PlayServicesLocationProvider(apiClient, LocationServices.FusedLocationApi);
    }

    @Provides
    GoogleApiClient provideGoogleApiClientWithLocation(Context context) {
        return new GoogleApiClient.Builder(context)
                .addApi(LocationServices.API)
                .build();
    }
}
