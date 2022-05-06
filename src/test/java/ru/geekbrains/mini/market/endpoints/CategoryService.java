package ru.geekbrains.mini.market.endpoints;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.geekbrains.mini.market.dto.CategoryDto;

public interface CategoryService {

    @GET("categories/{id}")
    Call<CategoryDto> getCategory(@Path("id") int id);
}