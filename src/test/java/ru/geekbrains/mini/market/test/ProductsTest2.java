package ru.geekbrains.mini.market.test;

import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.assertj.core.api.Assertions;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;
import ru.geekbrains.mini.market.dto.CategoryDto;
import ru.geekbrains.mini.market.dto.ProductDto;
import ru.geekbrains.mini.market.endpoints.ProductsService;
import ru.geekbrains.mini.market.entites.Category;
import ru.geekbrains.mini.market.service.ProductService;
import ru.geekbrains.mini.market.util.RetrofitUtil;

import java.util.Objects;

import static org.hamcrest.MatcherAssert.assertThat;
import static ru.geekbrains.mini.market.util.RetrofitUtil.getCategoryService;
import static ru.geekbrains.mini.market.util.RetrofitUtil.getProductsService;
import static org.assertj.core.api.Assertions.assertThat;

class ProductsTest2 {
    ProductDto productDto;
    int productId;

    @SneakyThrows
    @BeforeEach
    void setUp() {
        productDto = new ProductDto()
                .withCategoryTitle(Objects.requireNonNull(getCategoryService().getCategory(1).execute().body()).getTitle())
                .withTitle("Banana")
                .withPrice(100);
    }

    @SneakyThrows
    @Test
    void createProductBananaTest() {
        Response<ProductDto> productDtoResponse = getProductsService().createProducts(productDto)
                .execute();
        Assertions.assertThat(productDtoResponse.isSuccessful()).isTrue();
        Assertions.assertThat(productDtoResponse.body())
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(productDto);
        productId = Objects.requireNonNull(productDtoResponse.body()).getId();
    }

    @SneakyThrows
    @Test
    void changeProductTest() {

        Response<ProductDto> productDtoResponse = getProductsService().changeProducts(productId, productDto)
                .execute();
        assertThat(productDtoResponse.isSuccessful()).isFalse();
        //assertThat(productDtoResponse.body()).
                //.usingRecursiveComparison()
                //.ignoringFields("id")
          //      .isEqualTo(productDto);
        // productId = Objects.requireNonNull(productDtoResponse.body()).getId();

    }

    @SneakyThrows
    @AfterEach
    void tearDown() {
        Assertions.assertThat(getProductsService().deleteProducts(productId).execute().isSuccessful())
                .isFalse();
    }
}
