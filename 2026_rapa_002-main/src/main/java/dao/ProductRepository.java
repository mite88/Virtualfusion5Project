package dao;

import db.JdbcTemplate;
import db.config.DataSourceConfiguration;
import domain.Post;
import domain.Product;

import java.util.List;
import java.util.Optional;

public class ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProductRepository() {
        this.jdbcTemplate = new JdbcTemplate(
                DataSourceConfiguration.getDataSource()
        );
    }

    public List<Product> findAll() {

        String sql = """
        SELECT
            *
        FROM
            product
        """;

        return jdbcTemplate.queryForList(
                sql,
                null,
                rs ->
                        new Product(
                                rs.getLong("id"),
                                rs.getString("name"),
                                rs.getInt("price"),
                                rs.getInt("stock")
                        )

        );

    }

    public Product save(Product product) {

        String sql = """
        INSERT INTO product
            ( name, price, stock )
        VALUES
            ( ?, ?, ? )
        """;

        Long savedId = jdbcTemplate.insertAndReturnKey(
                sql,
                pss -> {
                    pss.setString(1, product.name());
                    pss.setInt(2, product.price());
                    pss.setInt(3, product.stock());
                }
        );

        return new Product(
                savedId,
                product.name(),
                product.price(),
                product.stock()
        );

    }

    public Optional<Product> findById(Long id) {

        String sql = """
        SELECT
            *
        FROM
            product
        WHERE
            id = ?
        """;

        return jdbcTemplate.queryForOptional(
                sql,
                pss -> pss.setLong(1, id),
                rs -> new Product(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getInt("price"),
                        rs.getInt("stock")
                )
        );

    }


}
