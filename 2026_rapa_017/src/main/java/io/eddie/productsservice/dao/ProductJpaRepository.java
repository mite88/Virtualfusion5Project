package io.eddie.productsservice.dao;

import io.eddie.productsservice.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJpaRepository extends JpaRepository<Products, Long> {
}
