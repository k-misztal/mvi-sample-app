package pl.misztal.bonfire.location;

import android.location.Location;

import com.google.android.gms.location.LocationRequest;

import io.reactivex.Observable;


/**
 * Created by kmisztal on 11.06.2017.
 *
 * @author Krzysztof Misztal
 */

public interface LocationProvider {
    int INTERVAL_MS = 12000;
    int STRATEGY = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY;
    int FASTEST_INTERVAL_MS = 8000;

    Observable<Location> start();

    void stop();
}
