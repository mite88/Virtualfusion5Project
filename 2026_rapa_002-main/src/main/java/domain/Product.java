package domain;

public record Product(
        Long id,
        String name,
        Integer price,
        Integer stock
) {
}
