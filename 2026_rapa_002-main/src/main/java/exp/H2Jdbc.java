import java.sql.Connection;
import java.sql.DriverManager;

void main() {

    final String URL = "jdbc:h2:mem:test_db";
    final String USERNAME = "sa";
    final String PASSWORD = "";

    try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {

        System.out.println("데이터베이스 연결 성공!");

        System.out.println("사용된 드라이버 이름: " + connection.getMetaData().getDriverName());
        System.out.println("연결된 url: " + connection.getMetaData().getURL());

    } catch ( Exception e ) {
        System.out.println("예외가 발생했습니다.. ㅠㅠ");
        throw new RuntimeException(e);
    }

}