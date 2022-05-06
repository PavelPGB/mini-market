package ru.geekbrains.mini.market.test;

import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;
import ru.geekbrains.mini.market.dto.CategoryDto;
import ru.geekbrains.mini.market.dto.ProductDto;
import ru.geekbrains.mini.market.endpoints.CategoryService;
import ru.geekbrains.mini.market.util.RetrofitUtil;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.geekbrains.mini.market.util.RetrofitUtil.getCategoryService;

class CategoriesTest {
    static CategoryService categoryService;

    @BeforeAll
    static void beforeAll() {

        categoryService = RetrofitUtil.getRetrofit().create(CategoryService.class);
    }

    @SneakyThrows
    @Test
    void categoryByIdPositiveTest() {
        Response<CategoryDto> categoryDtoResponse = categoryService.getCategory(1)
                .execute();
        assertThat(categoryDtoResponse.isSuccessful(), CoreMatchers.is(true));
    }

    @SneakyThrows
    @Test
    void getCategoryWithResponseAssertionsPositiveTest() {
        Response<CategoryDto> categoryDtoResponse = categoryService.getCategory(1)
                .execute();
        assertThat(categoryDtoResponse.isSuccessful(), CoreMatchers.is(true));
        assertThat(categoryDtoResponse.body().getId(), equalTo(1));
        assertThat(categoryDtoResponse.body().getTitle(), equalTo("Food"));
        categoryDtoResponse.body().getProducts().forEach(product ->
        assertThat(product.getCategoryTitle(), equalTo("Food")));
    }

    @SneakyThrows
    @Test
    void categoryByIdNegativeTest() {
        Response<CategoryDto> categoryDtoResponse = categoryService.getCategory(5)
                .execute();
        assertThat(categoryDtoResponse.isSuccessful(), CoreMatchers.is(false));
        assertThat(categoryDtoResponse.code(), equalTo(404));
    }
}