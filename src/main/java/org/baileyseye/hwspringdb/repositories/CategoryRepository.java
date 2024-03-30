package org.baileyseye.hwspringdb.repositories;

import org.baileyseye.hwspringdb.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer>, 
        JpaSpecificationExecutor<Category> {

    @Query("SELECT c FROM Category c JOIN FETCH c.products")
    List<Category> findAllWithProducts();
}