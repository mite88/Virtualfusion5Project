import domain.Product;
import mapper.s5.ProductMapper;
import org.apache.ibatis.session.SqlSession;
import util.HikariMyBatisConnector;
import util.MyBatisSessionConnector;

import java.util.NoSuchElementException;
import java.util.Optional;

public class CacheTests {

    private static final String MAPPER_PACKAGE = "mapper.s5";
    private static final String ALIAS_PACKAGE = "domain";

    private static final MyBatisSessionConnector connector = new HikariMyBatisConnector(
            MAPPER_PACKAGE,
            ALIAS_PACKAGE
    );

    void main() {
//        test1();
        test2();
    }

    void test1() {

        System.out.println("CacheTests.test1");

        try (SqlSession session = connector.openSession()) {

            ProductMapper mapper = session.getMapper(ProductMapper.class);

            long targetId = 1L;

            System.out.println("첫 번째 product 조회 시도");
            Optional<Product> productOptional1 = mapper.findById(targetId);
            productOptional1.ifPresent(p -> System.out.println("상품이 조회되었습니다!"));

            System.out.println("두 번째 product 조회 시도");
            Optional<Product> productOptional2 = mapper.findById(targetId);
            productOptional2.ifPresent(p -> System.out.println("상품이 조회되었습니다!"));

            System.out.println("product1과 product2 비교");
            System.out.println(productOptional1.get() == productOptional2.get());

        }

        try (SqlSession session = connector.openSession()) {

            ProductMapper mapper = session.getMapper(ProductMapper.class);

            long targetId = 1L;

            System.out.println("세 번째 product 조회 시도");
            Optional<Product> productOptional1 = mapper.findById(targetId);
            productOptional1.ifPresent(p -> System.out.println("상품이 조회되었습니다!"));


        }




    }

    void test2() {

        System.out.println("CacheTests.test2");

        try (SqlSession session = connector.openSession()) {

            ProductMapper mapper = session.getMapper(ProductMapper.class);

            long targetId = 1L;

            System.out.println("첫 번째 product 조회 시도");
            Optional<Product> productOptional1 = mapper.findById(targetId);
            productOptional1.ifPresent(p -> System.out.println("상품이 조회되었습니다!"));

            System.out.println("두 번째 product 조회 시도");
            Optional<Product> productOptional2 = mapper.findById(targetId);
            productOptional2.ifPresent(p -> System.out.println("상품이 조회되었습니다!"));

            System.out.println("product1과 product2 비교");
            System.out.println(productOptional1.get() == productOptional2.get());

            Product product1 = productOptional1.get();
            Product product2 = productOptional2.get();

            System.out.println("product1 과 product2의 동일성 검사" + (product1 == product2));

            System.out.println("상품 재고 업데이트 시도");
            mapper.updateById(
                    new Product(
                            product1.getId(),
                            product1.getName(),
                            product1.getPrice(),
                            100
                    )
            );

            System.out.println("세 번째 product 조회 시도");
            Optional<Product> productOptional3 = mapper.findById(targetId);
            productOptional2.ifPresent(p -> System.out.println("상품이 조회되었습니다!"));
            Product product3 = productOptional3.get();
            System.out.println("product1 과 product3의 동일성 검사" + (product1 == product3));

            System.out.println("네 번째 product 조회 시도");
            Optional<Product> productOptional4 = mapper.findById(targetId);
            productOptional4.ifPresent(p -> System.out.println("상품이 조회되었습니다!"));

            session.clearCache();

            System.out.println("다섯 번째 product 조회 시도");
            Optional<Product> productOptional5 = mapper.findById(targetId);
            productOptional5.ifPresent(p -> System.out.println("상품이 조회되었습니다!"));

            session.commit();
//            session.rollback();

            System.out.println("여섯 번째 product 조회 시도");
            Optional<Product> productOptional6 = mapper.findById(targetId);
            productOptional6.ifPresent(p -> System.out.println("상품이 조회되었습니다!"));



        }

    }

}
