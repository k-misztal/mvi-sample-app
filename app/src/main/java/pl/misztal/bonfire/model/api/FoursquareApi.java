package pl.misztal.bonfire.model.api;


import io.reactivex.Single;
import pl.misztal.bonfire.model.api.model.VenuesInfo;
import pl.misztal.bonfire.model.api.model.request.LatLng;
import pl.misztal.bonfire.model.api.model.response.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FoursquareApi {

    @GET("venues/explore?venuesPhotos=1")
    Single<Response<VenuesInfo>> getRecommendedVenues(@Query("ll") LatLng latLng, @Query("llAcc") float accuracy,
                                                      @Query("limit") int limit, @Query("offset") int offset);
}
