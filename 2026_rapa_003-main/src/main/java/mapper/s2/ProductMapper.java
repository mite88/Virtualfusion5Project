package mapper.s2;

import domain.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

public interface ProductMapper {

    List<Product> findAll();
    Optional<Product> findById(Long id);

    // 재고가 있는 상품만 조회
    List<Product> findHasStock();

    int save(Product product);

    int update(Product product);

    int deleteById(Long id);

    int decreaseStock(@Param("id") Long id,@Param("quantity") int quantity);

}
