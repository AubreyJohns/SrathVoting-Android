package com.strath.strathvoting;

import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitClient {

    //private static Retrofit retrofit=null;
    //private static final String BASE_URL = "http://192.168.43.239:80/IAP/candidates.php/";
    private static final String BASE_URL = "https://red-mountie-10018.herokuapp.com/api/";


    private static HttpLoggingInterceptor logger=new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    private static OkHttpClient.Builder okHttp=new OkHttpClient.Builder().addInterceptor(logger).connectTimeout(5, TimeUnit.MINUTES)
            .writeTimeout(5, TimeUnit.MINUTES)
            .readTimeout(5, TimeUnit.MINUTES);

    public static Retrofit.Builder builder=new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(new Gson()))
            //.addConverterFactory(ScalarsConverterFactory.create())
            //.addConverterFactory(GsonConverterFactory.create())
            .client(okHttp.build());

    public static Retrofit retrofit=builder.build();

    public static <S> S buildService(Class<S> serviceType){return retrofit.create(serviceType);}

/*
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttp.build());
        }
        return retrofit;
    }
    */
}
