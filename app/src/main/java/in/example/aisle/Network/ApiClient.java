package in.example.aisle.Network;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {
    private static Retrofit retrofit = null;
    private static int REQUEST_TIMEOUT = 500;
    private static OkHttpClient okHttpClient;

    private static Retrofit retrofit2 = null;

    private static OkHttpClient okHttpClient2;

    public static Retrofit getClient(Context context) {

        if (okHttpClient == null)
            initOkHttp(context, null);

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://testa2.aisle.co/V1/users/")
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }
    public static Retrofit getClient2(Context context, String token) {

        if (okHttpClient2 == null)
            initOkHttp(context,token);

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        if (retrofit2 == null) {
            retrofit2 = new Retrofit.Builder()
                    .baseUrl("https://testa2.aisle.co/V1/users/")
                    .client(okHttpClient2)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit2;
    }



    private static void initOkHttp(final Context context, String token) {
        OkHttpClient.Builder httpClient = new OkHttpClient().newBuilder()
                .retryOnConnectionFailure(true)
                .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(interceptor);

        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder requestBuilder = original.newBuilder()
                        .addHeader("Content-Type", "application/json");
                requestBuilder.addHeader("Cookie", "__cfduid=df9b865983bd04a5de2cf5017994bbbc71618565720");

               if(token!=null) {
                   Log.d("chackauth","token");
                   requestBuilder.addHeader("Authorization", token);
               }
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });
        if(token!=null) {
            okHttpClient2 = httpClient.build();
        }else {
            okHttpClient = httpClient.build();
        }

    }
}

