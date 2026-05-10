package exp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    static void main() {

        final String PORT = "3306";
        final String URL = "jdbc:mysql://localhost:%s/jdbc_shop".formatted(PORT);
        final String USERNAME = "rapa_jdbc";
        final String PASSWORD = "pwd1!";

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {

            System.out.println("데이터베이스 연결 성공!");

            System.out.println("사용된 드라이버 이름: " + connection.getMetaData().getDriverName());
            System.out.println("연결된 url: " + connection.getMetaData().getURL());


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}