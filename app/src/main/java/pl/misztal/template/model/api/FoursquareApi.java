package pl.misztal.template.model.api;


import io.reactivex.Observable;
import pl.misztal.template.BuildConfig;
import pl.misztal.template.model.api.model.VenuesInfo;
import pl.misztal.template.model.api.model.request.LatLng;
import pl.misztal.template.model.api.model.response.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FoursquareApi {
    String CREDENTIALS = "client_id=" + BuildConfig.CLIENT_ID +
            "&client_secret=" + BuildConfig.SECRET +
            "&v=" + BuildConfig.VERSION;

    @GET("/venues/explore?venuesPhotos=1" + CREDENTIALS)
    Observable<Response<VenuesInfo>> getRecommendedVenues(@Query("ll") LatLng latLng, @Query("llAcc") float accuracy);
}
