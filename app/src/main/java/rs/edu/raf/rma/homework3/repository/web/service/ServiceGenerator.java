package rs.edu.raf.rma.homework3.repository.web.service;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rs.edu.raf.rma.homework3.BuildConfig;

public class ServiceGenerator {

    private static final String BASE_URL = "https://ghibliapi.herokuapp.com/";
    private static final boolean DEBUG = true;
    private static final int READ_TIMEOUT = 10;

    private static final HttpLoggingInterceptor sHttpLoggingInterceptor =
            new HttpLoggingInterceptor().setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);

    private static OkHttpClient.Builder sHttpClientBuilder = new OkHttpClient.Builder().readTimeout(READ_TIMEOUT, TimeUnit.SECONDS).connectTimeout(READ_TIMEOUT, TimeUnit.SECONDS).addInterceptor(sHttpLoggingInterceptor);

    private static OkHttpClient sOkHttpClient = sHttpClientBuilder.build();

    private static Retrofit.Builder sBuilder = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create());

    private static Retrofit sRetrofit = sBuilder.client(sOkHttpClient).build();

    public static <S> S createService(Class<S> serviceClass) {
        return sRetrofit.create(serviceClass);
    }

}
