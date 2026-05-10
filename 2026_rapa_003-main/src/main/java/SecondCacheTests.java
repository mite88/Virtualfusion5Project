import domain.Product;
import mapper.s5.ProductCacheMapper;
import org.apache.ibatis.session.SqlSession;
import util.HikariMyBatisConnector;
import util.MyBatisSessionConnector;

import java.util.List;

public class SecondCacheTests {

    private static final String MAPPER_PACKAGE = "mapper.s5";
    private static final String ALIAS_PACKAGE = "domain";

    private static final MyBatisSessionConnector connector = new HikariMyBatisConnector(
            MAPPER_PACKAGE,
            ALIAS_PACKAGE
    );

    void main() {
        test1();
    }

    void test1() {

        System.out.println("SecondCacheTests.test1");

        try (SqlSession session = connector.openSession()) {

            ProductCacheMapper mapper = session.getMapper(ProductCacheMapper.class);

            List<Product> products = mapper.findAll();
            System.out.println("첫 번째 세션 조회: " + products.size() + "건");

        }

        try (SqlSession session = connector.openSession()) {

            ProductCacheMapper mapper = session.getMapper(ProductCacheMapper.class);

            List<Product> products = mapper.findAll();
            System.out.println("두 번째 세션 조회: " + products.size() + "건");

        }

    }

}
