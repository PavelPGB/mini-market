package ru.geekbrains.mini.market.endpoints;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;
import ru.geekbrains.mini.market.dto.ProductDto;

import java.util.List;

public interface ProductsService {

    @GET("products")
    Call<List<ProductDto>> getProducts();

    @POST("products")
    Call<ProductDto> createProducts(@Body ProductDto productDto);

    @PUT("products/{id}")
    Call<ProductDto> changeProducts(@Path("id") int id, @Body ProductDto productDto);

    @GET("products/{id}")
    Call<ProductDto> getProduct(@Path("id") int id);

    @DELETE("products/{id}")
    Call<ResponseBody> deleteProducts(@Path("id") int id);
}