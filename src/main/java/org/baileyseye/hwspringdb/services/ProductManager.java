package org.baileyseye.hwspringdb.services;

import org.baileyseye.hwspringdb.model.Product;

import java.util.List;

public interface ProductManager {
    List<Product> FindAll();
}
