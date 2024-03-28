package org.baileyseye.hwspringdb.repositories;

import org.baileyseye.hwspringdb.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByProductPriceLessThan(BigDecimal priceLimit);

    @Query("SELECT p FROM Product p WHERE p.id IN :ids")
    List<Product> getProductsByIds(List<Integer> ids);
}