import domain.Product;
import domain.ProductSortType;
import mapper.s3.S3ProductMapper;
import mapper.s3.S3ProductMapperV2;
import org.apache.ibatis.session.SqlSession;
import util.HikariMyBatisConnector;
import util.MyBatisSessionConnector;
import util.TestUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static util.TestUtils.*;

public class DynamicQueryTests {

    private static final String MAPPER_PACKAGE = "mapper.s3";
    private static final String ALIAS_PACKAGE = "domain";

    private static final MyBatisSessionConnector connector = new HikariMyBatisConnector(
            MAPPER_PACKAGE,
            ALIAS_PACKAGE
    );

    static void main() {
//        test1();
//        test2();
//        test3();
//        test4();
//        test5();
//        test6();
        test7();
    }

    private static void test7() {

        System.out.println("DynamicQueryTests.test7");

        try (SqlSession session = connector.openSession()) {

            S3ProductMapperV2 mapper = session.getMapper(S3ProductMapperV2.class);

            Map<String, Object> params = new HashMap<>();

            // Case1. 이름만 검색
            params.put("name", "노트");
            List<Product> products = mapper.search(params);

            // 예상 실행 SQL
            // SELECT id, name, price, stock WHERE 1 = 1 AND name LIKE '%노트%'
            printResults(products);

        }


    }

    /**
     * Bulk Insert
     */
    private static void test6() {

        System.out.println("DynamicQueryTests.test6");

        try (SqlSession session = connector.openSession(true)) {

            S3ProductMapper mapper = session.getMapper(S3ProductMapper.class);

            List<Product> products = List.of(
                    new Product(null, "벌크_추가_상품_1", 1, 1),
                    new Product(null, "벌크_추가_상품_1", 1, 1),
                    new Product(null, "벌크_추가_상품_1", 1, 1),
                    new Product(null, "벌크_추가_상품_1", 1, 1),
                    new Product(null, "벌크_추가_상품_1", 1, 1)
            );

            int affectedRows = mapper.saveAll(products);

            System.out.println("affectedRows = " + affectedRows);

        }

    }

    /**
     * For Each
     */
    private static void test5() {

        System.out.println("DynamicQueryTests.test5");

        try (SqlSession session = connector.openSession()) {

            S3ProductMapper mapper = session.getMapper(S3ProductMapper.class);

            mapper.findByIds(List.of(1L,3L,5L,7L,9L));

        }


    }

    /**
     * If Condition
     */
    static void test1() {

        System.out.println("DynamicQueryTests.test1");

        try (SqlSession session = connector.openSession()) {

            S3ProductMapper mapper = session.getMapper(S3ProductMapper.class);

            Map<String, Object> params = new HashMap<>();

            // Case1. 이름만 검색
            params.put("name", "노트");
            List<Product> products = mapper.search(params);

            // 예상 실행 SQL
            // WHERE 1 = 1 AND name LIKE '%노트%'
            printResults(products);

            // Case2. 가격 범위만
            params.clear();

            params.put("minPrice", 10000);
            params.put("maxPrice", 100000);
            List<Product> result2 = mapper.search(params);

            // 예상 실행 SQL
            // WHERE 1 = 1 AND price >= 10000 AND price <= 100000
            printResults(result2);

            // Case3. 조건 없을때
            params.clear();

            List<Product> result3 = mapper.search(params);

            // 예상 실행 SQL
            // WHERE 1 = 1
            printResults(result3);

        }

    }

    /**
     * Where Condition
     */
    static void test2() {

        System.out.println("DynamicQueryTests.test2");

        try (SqlSession session = connector.openSession()) {

            S3ProductMapper mapper = session.getMapper(S3ProductMapper.class);

            Map<String, Object> params = new HashMap<>();

            params.put("name", "노트");

            List<Product> products = mapper.search2(params);

            printResults(products);

        }

    }

    /**
     * Choose Condition
     */
    static void test3() {

        System.out.println("DynamicQueryTests.test3");

        try (SqlSession session = connector.openSession()) {

            S3ProductMapper mapper = session.getMapper(S3ProductMapper.class);

            System.out.println("가격 기준 정렬");
            mapper.findAllSorted(ProductSortType.PRICE);

            System.out.println("재고 기준 정렬");
            mapper.findAllSorted(ProductSortType.STOCK);

            System.out.println("이름 기준 정렬");
            mapper.findAllSorted(ProductSortType.NAME);

            System.out.println("기본값 (ID)");
            mapper.findAllSorted(ProductSortType.ID);

        }


    }

    /**
     * Dynamic With Update Query
     */
    static void test4() {

        System.out.println("DynamicQueryTests.test4");

        try (SqlSession session = connector.openSession()) {

            S3ProductMapper mapper = session.getMapper(S3ProductMapper.class);

            Product updateRequest = new Product(1L, "수정된 상품 이름", null, null);

            mapper.updateProduct(updateRequest);

        }

    }

}
