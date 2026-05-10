package mapper.s3;

import domain.Product;
import domain.ProductSortType;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface S3ProductMapperV2 {

    List<Product> search(Map<String, Object> params);
    List<Product> search2(Map<String, Object> params);

    List<Product> findAllSorted(ProductSortType sortType);

    int updateProduct(Product product);

    @Select("""
    SELECT
        id,
        name,
        price,
        stock
    FROM
        product
    WHERE
        id = #{id}
    """)
    Optional<Product> findById(@Param("id") Long id);

    List<Product> findByIds(@Param("ids") List<Long> ids);

    int saveAll(@Param("products") List<Product> products);



}
