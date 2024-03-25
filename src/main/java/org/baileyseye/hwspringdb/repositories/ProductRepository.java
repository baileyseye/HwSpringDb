package org.baileyseye.hwspringdb.repositories;

import org.baileyseye.hwspringdb.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

        }