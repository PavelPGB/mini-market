package ru.geekbrains.mini.market.test;

import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;
import ru.geekbrains.mini.market.dto.ProductDto;
import ru.geekbrains.mini.market.endpoints.ProductsService;

import java.util.List;
import java.util.Objects;

import static ru.geekbrains.mini.market.util.RetrofitUtil.getCategoryService;
import static ru.geekbrains.mini.market.util.RetrofitUtil.getProductsService;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

class ProductTest3 {
    ProductDto productDto;
    int productId;

    @SneakyThrows
    @BeforeEach
    void setUp() {
        productDto = new ProductDto()
                .withCategoryTitle(Objects.requireNonNull(getCategoryService().getCategory(2).execute().body()).getTitle())
                .withTitle("Hiphone")
                .withPrice(40000);
    }

    @SneakyThrows
    @Test
    void createProductHiphoneTest() {
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
    @Test
    void сheckingProductAddedToListTest() {
        Response<List<ProductDto>> listResponse = getProductsService().getProducts().
                execute();
        assertThat(listResponse.isSuccessful()).isTrue();
    }

    @SneakyThrows
    @AfterEach
    void tearDown() {
        assertThat(getProductsService().deleteProducts(productId).execute().isSuccessful())
                .isFalse();
    }

    @SneakyThrows
    @Test
    void checkingListAfterDeleteProductTest() {
        Response<List<ProductDto>> listResponse = getProductsService().getProducts().
                execute();
        assertThat(listResponse.isSuccessful()).isTrue();
    }
}
