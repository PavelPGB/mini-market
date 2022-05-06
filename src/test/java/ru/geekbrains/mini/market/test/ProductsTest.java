package ru.geekbrains.mini.market.test;

import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;
import ru.geekbrains.mini.market.dto.CategoryDto;
import ru.geekbrains.mini.market.dto.ProductDto;
import ru.geekbrains.mini.market.endpoints.CategoryService;
import ru.geekbrains.mini.market.endpoints.ProductsService;
import ru.geekbrains.mini.market.util.RetrofitUtil;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.geekbrains.mini.market.util.RetrofitUtil.getCategoryService;
import static ru.geekbrains.mini.market.util.RetrofitUtil.getProductsService;
import static org.hamcrest.MatcherAssert.assertThat;

class ProductsTest {
    ProductDto productDto;
    int productId;

    @SneakyThrows
    @BeforeEach
    void setUp() {
        productDto = new ProductDto()
                .withCategoryTitle(Objects.requireNonNull(getCategoryService().getCategory(2).execute().body()).getTitle())
                .withTitle(new Faker().food().ingredient())
                .withPrice(500);
    }

    @SneakyThrows
    @Test
    void createProductTest() {
        Response<ProductDto> productDtoResponse = getProductsService().createProducts(productDto)
                .execute();
        assertThat(productDtoResponse.isSuccessful()).isTrue();
        assertThat(productDtoResponse.body())
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(productDto);
        productId = Objects.requireNonNull(productDtoResponse.body()).getId();
    }

    @SneakyThrows
    @AfterEach
    void tearDown() {
        assertThat(getProductsService().deleteProducts(productId).execute().isSuccessful())
                .isTrue();
    }
}