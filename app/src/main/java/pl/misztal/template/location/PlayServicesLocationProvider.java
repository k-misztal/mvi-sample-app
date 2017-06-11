package pl.misztal.template.location;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

/**
 * Created by kmisztal on 11.06.2017.
 *
 * @author Krzysztof Misztal
 */

public class PlayServicesLocationProvider implements LocationProvider,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    private static final int SMALLEST_DISPLACEMENT_METERS = 50;

    private final GoogleApiClient googleApiClient;

    @Nullable
    private BehaviorSubject<Location> subject;

    private boolean fusedLocationRequested = false;
    private boolean isWorking = false;


    private FusedLocationProviderApi fusedLocationProviderApi;

    public PlayServicesLocationProvider(GoogleApiClient apiClient,
                                        FusedLocationProviderApi fusedLocationProviderApi) {
        this.googleApiClient = apiClient;
        this.fusedLocationProviderApi = fusedLocationProviderApi;
    }

    @Override
    public synchronized Observable<Location> start() {
        if (!googleApiClient.isConnectionFailedListenerRegistered(this)) {
            googleApiClient.registerConnectionFailedListener(this);
        }
        if (!googleApiClient.isConnectionCallbacksRegistered(this)) {
            googleApiClient.registerConnectionCallbacks(this);
        }

        isWorking = true;

        if (subject == null) {
            subject = BehaviorSubject.create();
        }

        googleApiClient.connect();

        return subject;
    }

    @Override
    public synchronized void stop() {
        if (!isWorking) {
            return;
        }

        if (googleApiClient.isConnectionFailedListenerRegistered(this)) {
            googleApiClient.unregisterConnectionFailedListener(this);
        }
        if (googleApiClient.isConnectionCallbacksRegistered(this)) {
            googleApiClient.unregisterConnectionCallbacks(this);
        }

        cancelFusedLocationUpdates();
        googleApiClient.disconnect();

        isWorking = false;

        if (subject != null && !subject.hasThrowable()) {
            subject.onComplete();
        }
        subject = null;
    }

    @Override
    public synchronized void onLocationChanged(Location location) {
        if (subject != null) {
            subject.onNext(location);
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (!googleApiClient.isConnected()) {
            onErrorDetected(new LocationScanningException(
                    "Tried to request location updates but GoogleApiClient is not connected"));
        } else if (isWorking) {
            LocationRequest locationRequest = new LocationRequest();
            locationRequest.setInterval(INTERVAL_MS);
            locationRequest.setFastestInterval(FASTEST_INTERVAL_MS);
            locationRequest.setPriority(STRATEGY);
            locationRequest.setSmallestDisplacement(SMALLEST_DISPLACEMENT_METERS);

            try {
                fusedLocationProviderApi
                        .requestLocationUpdates(googleApiClient, locationRequest, this);
                fusedLocationRequested = true;
            } catch (SecurityException e) {
                onErrorDetected(e);
            }
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        //called when connection is suspended, but play services should reconnect by itself.
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        onErrorDetected(new LocationScanningException("Error scanning for location"));
    }

    synchronized boolean isWorking() {
        return isWorking;
    }

    private void cancelFusedLocationUpdates() {
        if (fusedLocationRequested && googleApiClient.isConnected()) {
            fusedLocationProviderApi.removeLocationUpdates(googleApiClient, this);
        }
        fusedLocationRequested = false;
    }


    private synchronized void onErrorDetected(Throwable throwable) {
        if (subject != null) {
            subject.onError(throwable);
        }
        stop();
    }
}
