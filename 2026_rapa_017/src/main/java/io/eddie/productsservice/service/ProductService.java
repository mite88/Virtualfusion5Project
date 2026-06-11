package io.eddie.productsservice.service;

import io.eddie.productsservice.dao.ProductJpaRepository;
import io.eddie.productsservice.dto.ProductRequest;
import io.eddie.productsservice.entity.Products;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductJpaRepository repository;

    @Transactional
    public Products save(ProductRequest request) {

        Products product = Products.builder()
                .name(request.name())
                .description(request.description())
                .price(request.price())
                .build();

        return repository.save(product);

    }

    @Transactional(readOnly = true)
    public Products findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Transactional(readOnly = true)
    public List<Products> findAll() {
        return repository.findAll();
    }

    @Transactional
    public void update(ProductRequest request, Long id) {
        repository.findById(id).ifPresent(product -> product.update(request));
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

}
