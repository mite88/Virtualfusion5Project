package io.eddie.productsservice.entity;

import io.eddie.productsservice.dto.ProductRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    private Long price;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    @Builder
    public Products(String name, String description, Long price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public void updateClock() {
        this.updatedAt = LocalDateTime.now();
    }

    public void update(ProductRequest request) {
        this.name = request.name();
        this.description = request.description();
        this.price = request.price();

        updateClock();
    }

}
