package mapper.s2;

import domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

public interface ProductMapperV2 {

    @Select("""
    SELECT
        id,
        name,
        price,
        stock
    FROM
        product
    """)
    List<Product> findAll();

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
    Optional<Product> findById(Long id);

    @Select("""
    SELECT
        id,
        name,
        price,
        stock
    FROM
        product
    WHERE
        stock > 0
    """)
    List<Product> findHasStock();

    @Insert(""" 
    INSERT INTO product
        ( name, price, stock )
    VALUES 
        ( #{name}, #{price}, #{stock} )
    """)
    int save(Product product);

    @Update("""
    UPDATE product
    SET
        name = #{name},
        price = #{price},
        stock = #{stock}
    WHERE
        id = #{id}
    """)
    int updatePrice(@Param("id") Long id, @Param("price") int price);

    @Delete("""
    DELETE FROM product
    WHERE
        id = #{id}
    """)
    int deleteById(Long id);

    @Update("""
    UPDATE product
    SET
        stock = stock - #{quantiy}
    WHERE
        id = #{id}
      AND
        stock >= #{quantity}
    """)
    int decreaseStock(@Param("id") Long id,@Param("quantity") int quantity);

}
