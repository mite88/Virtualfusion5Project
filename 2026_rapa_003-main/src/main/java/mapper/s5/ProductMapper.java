package mapper.s5;

import domain.Product;

import java.util.Optional;

public interface ProductMapper {

    Optional<Product> findById(Long id);
    int updateById(Product product);

}
