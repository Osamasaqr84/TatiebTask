package com.noname.smatech.datalayer.apidata;



import android.provider.SyncStateContract;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
 import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = NetworkModule.class)
public class ApiServiceModule {

    private static final String BASE_URL = "https://api.github.com/users/";
    @Provides
    @ApplicationScope
    public ServerGateway getApiService(Retrofit retrofit){
        return retrofit.create(ServerGateway.class);
    }


    @Provides
    @ApplicationScope
    public Retrofit getRetrofit(OkHttpClient okhttpClient){
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(okhttpClient)
                .build();
    }


}
