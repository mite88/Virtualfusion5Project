package util;

import java.sql.Connection;
import java.sql.DriverManager;

public abstract class ConnectionUtil {

    public static abstract class H2 {

        private static final String URL = "jdbc:h2:mem:test_db";
        private static final String USER = "sa";
        private static final String PASSWORD = "";

        public static Connection getConnection() {
            try {
                return DriverManager.getConnection(URL, USER, PASSWORD);
            } catch ( Exception e ) {
                System.out.println("데이터베이스와의 연결에 실패했습니다");
                throw new RuntimeException(e);
            }
        }

    }

    public static abstract class MySql {

        private static final String URL = "jdbc:mysql://localhost:3306/jdbc_shop";
        private static final String USER = "rapa_jdbc";
        private static final String PASSWORD = "pwd1!";

        public static Connection getConnection() {
            try {
                return DriverManager.getConnection(URL, USER, PASSWORD);
            } catch ( Exception e ) {
                System.out.println("데이터베이스와의 연결에 실패했습니다");
                throw new RuntimeException(e);
            }
        }

    }

}
