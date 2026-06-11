package io.eddie.productsservice.controller;

import io.eddie.productsservice.dto.ProductRequest;
import io.eddie.productsservice.entity.Products;
import io.eddie.productsservice.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductApiController {

    private final ProductService service;

    @Operation(
            summary = "상품 추가 API",
            description = "게임 전반적으로 사용되는 아이템 내역 추가하는 API"
    )
    @ApiResponse(
            responseCode = "201",
            description = "저장된 상품에 대한 내역입니다.",
            content = {
                    @Content(
                      mediaType = "application/json",
                      schema = @Schema(
                              implementation = Products.class
                      )
                    )
            }
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Products> save(ProductRequest request) {
        return ResponseEntity.ok(service.save(request));
    }


    @Operation(
            summary = "특정 상품 목록 조회 API",
            description = "특정 아이템을 조회하는 API"
    )
    @GetMapping("/{id}")
    public ResponseEntity<Products> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }


    @Operation(
            summary = "모든 상품 조회 API",
            description = "전체 아이템을 조회하는 API"
    )
    @GetMapping
    public ResponseEntity<List<Products>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @Operation(
            summary = "상품 수정 API",
            description = "전체 아이템을 수정하는 API"
    )
    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody ProductRequest request, @PathVariable Long id) {
        service.update(request, id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "상품 삭제 API",
            description = """
전체 아이템을 삭제하는 API
 * 실제 DB에선 지워지지않습니다.
                    """
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
