package com.asksira.imagepickersheetdemo.Model;

import com.asksira.imagepickersheetdemo.network.TenunNetworkInterface;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import dagger.Module;
import dagger.Provides;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import timber.log.Timber;

@Module(
        complete = false,
        library = true
)

public class APIModule {
    public static final String ENDPOINT = "https://www.jtenun.com/ModulTerbaru/api-tenun/";
    public static final String ROOT = "https://www.jtenun.com/";
    public static final String ACCESS_TOKEN_TEMP = "Jt3nuN_20161130";

    @Provides
    Retrofit provideRetrofit(Call.Factory callFactory, Gson gson) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addConverterFactory(ScalarsConverterFactory.create())
                .callFactory(callFactory)
                .baseUrl(ROOT)
                .build();
    }

    @Singleton
    @Provides
    Gson provideGson() {
        return new GsonBuilder()
                .setLenient()
                .create();
    }

    @Singleton
    @Provides
    Call.Factory provideCallFactory() {
        // creating a KeyStore containing our trusted CAs
        SSLContext sslContext;

        try {
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }};

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new SecureRandom());

            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            return new OkHttpClient().newBuilder()
                    .addInterceptor(loggingInterceptor)   //if something wrong
//                    .interceptors().add(loggingInterceptor)
                    .connectTimeout(600, TimeUnit.SECONDS)
                    .readTimeout(600, TimeUnit.SECONDS)
                    .writeTimeout(600, TimeUnit.SECONDS)
                    .hostnameVerifier((hostname, session) -> true)
                    .sslSocketFactory(sc.getSocketFactory(), new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[]{};
                        }
                    })
                    .build();
        } catch (Exception e) {
            Timber.i("Exception : " + e);
            return null;
        }
    }

    @Singleton
    @Provides
    TenunNetworkInterface provideService(Retrofit retrofit) {
        return retrofit.create(TenunNetworkInterface.class);
    }
}
