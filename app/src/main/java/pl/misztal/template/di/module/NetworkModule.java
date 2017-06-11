package pl.misztal.template.di.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import pl.misztal.template.BuildConfig;
import pl.misztal.template.model.api.FoursquareApi;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Provides
    FoursquareApi provideRestService(OkHttpClient client, Gson gson) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit.create(FoursquareApi.class);
    }

    @Provides
    OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        if (BuildConfig.DEBUG) {
            try {
                final Interceptor interceptor = (Interceptor) Class.forName("com.facebook.stetho.okhttp3.StethoInterceptor").newInstance();
                builder.addNetworkInterceptor(interceptor);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return builder.build();
    }

    @Provides
    Gson provideGson() {
        GsonBuilder builder = new GsonBuilder();
        builder.setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ"); // TODO: 10.06.2017 check date format

        return builder.create();
    }

}
