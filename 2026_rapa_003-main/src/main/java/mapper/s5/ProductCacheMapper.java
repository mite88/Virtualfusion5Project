package mapper.s5;

import domain.Product;

import java.util.List;

public interface ProductCacheMapper {

    List<Product> findAll();

}
