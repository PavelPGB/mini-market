package ru.geekbrains.mini.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.geekbrains.mini.market.entites.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
//    @Query("update Product p set p.title = ?1 where p.id = ?2")
//    @Modifying
//    Integer updateProductTitleById(String title, Long id);
}
