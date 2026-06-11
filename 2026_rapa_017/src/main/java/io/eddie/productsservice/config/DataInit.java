package io.eddie.productsservice.config;

import io.eddie.productsservice.dto.ProductRequest;
import io.eddie.productsservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

/**
 * packageName    : io.eddie.productsservice.config
 * fileName       : DataInit
 * author         : Admin
 * date           : 26. 6. 11.
 * description    : 테스트에서 db 저장해서 쓰기
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 6. 11.        Admin       최초 생성
 */
@Component
@RequiredArgsConstructor
public class DataInit implements ApplicationRunner {

    private final ProductService productService;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        IntStream.rangeClosed(1, 20).forEach(i -> {
            // 1. 객체 생성
            ProductRequest request = new ProductRequest(
                    "상품명_%d".formatted(i),
                    "상품_설명_%d".formatted(i),
                    i * 10000L
            );

            // 2. 반복문 안에서 즉시 저장
            productService.save(request);
        });
    }
}
