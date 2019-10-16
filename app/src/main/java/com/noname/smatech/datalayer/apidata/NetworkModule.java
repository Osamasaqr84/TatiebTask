package com.noname.smatech.datalayer.apidata;

import android.content.Context;
import android.provider.SyncStateContract;
import android.text.TextUtils;


import com.noname.smatech.BuildConfig;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;

@Module(includes = ContextModule.class)
public class NetworkModule {
    private static int REQUEST_TIMEOUT = 60;

    @Provides
    @ApplicationScope
    public HttpLoggingInterceptor httpLoggingInterceptor() {
        return new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                if (BuildConfig.DEBUG) {
                    //Timber.i(message);
                }
            }
        }).setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Provides
    @ApplicationScope
    public Cache cache(File fileCach) {
        return new Cache(fileCach, 10 * 1000 * 1000); // 10MB
    }

    @Provides
    @ApplicationScope
    public File fileCach(Context context) {
        return new File(context.getCacheDir(), "okhttp_cach");
    }

    @Provides
    @ApplicationScope
    public Interceptor interceptor() {
        return new Interceptor() {

            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder requestBuilder = original.newBuilder()
                        .addHeader("Content-Type", "application/json")
                        .addHeader("Accept", "application/json");

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        };
    }

    @Provides
    @ApplicationScope
    public OkHttpClient okHttpClient(HttpLoggingInterceptor httpLoggingInterceptor, Cache cache, Interceptor interceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(interceptor)
                .cache(cache)
                .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .build();
    }


}
