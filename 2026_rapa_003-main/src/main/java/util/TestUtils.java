package util;

import domain.Product;

import java.util.List;

public class TestUtils {

    public static void printResults(List<Product> products) {

        System.out.println("검색 결과 ===========");
        for (Product product : products) {
            System.out.println("상품 번호: " + product.getId());
            System.out.println("상품 이름: " + product.getName());
            System.out.println("상품 가격: " + product.getPrice());
            System.out.println("상품 재고: " + product.getStock());
        }

        System.out.println();
        System.out.println();

    }

    public static void printResult(Product product) {

        System.out.println("검색 결과 ===========");
        System.out.println("상품 번호: " + product.getId());
        System.out.println("상품 이름: " + product.getName());
        System.out.println("상품 가격: " + product.getPrice());
        System.out.println("상품 재고: " + product.getStock());


        System.out.println();
        System.out.println();

    }

}
