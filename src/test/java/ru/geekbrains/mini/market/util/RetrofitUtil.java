package ru.geekbrains.mini.market.util;

import io.qameta.allure.okhttp3.AllureOkHttp3;
import lombok.experimental.UtilityClass;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import ru.geekbrains.mini.market.log.LoggingInterceptor;

import ru.geekbrains.mini.market.endpoints.CategoryService;
import ru.geekbrains.mini.market.endpoints.ProductsService;
import ru.geekbrains.mini.market.log.PrettyLogger;

import static ru.geekbrains.mini.market.config.MiniMarketConfig.miniMarketConfig;

@UtilityClass
public class RetrofitUtil {

    public static Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(miniMarketConfig.baseURI())
                .addConverterFactory(JacksonConverterFactory.create())
                .client(new OkHttpClient.Builder()
                        .addInterceptor(new HttpLoggingInterceptor(new PrettyLogger()).setLevel(HttpLoggingInterceptor.Level.BODY))
                        .addInterceptor(new AllureOkHttp3())
                        .build())
                .build();
    }

    public static CategoryService getCategoryService() {

        return getRetrofit().create(CategoryService.class);
    }

    public static ProductsService getProductsService() {

        return getRetrofit().create(ProductsService.class);
    }

}