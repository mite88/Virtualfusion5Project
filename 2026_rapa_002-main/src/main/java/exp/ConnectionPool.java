import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;


void main() throws Exception {

    // DataSource
    // JDBC Interface
    String JDBC_URL = "jdbc:mysql://localhost:3306/jdbc_shop";
    String USERNAME = "rapa_jdbc";
    String PASSWORD = "pwd1!";

    String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";

    HikariConfig config = new HikariConfig();

    config.setJdbcUrl(JDBC_URL);
    config.setUsername(USERNAME);
    config.setPassword(PASSWORD);
    config.setDriverClassName(DRIVER_CLASS_NAME);

    config.setMaximumPoolSize(10);
    config.setMinimumIdle(5);

    HikariDataSource hikariCp = new HikariDataSource(config);

    final int MAX_REPEAT_COUNT = 50;

    long start = System.currentTimeMillis();

    IntStream.rangeClosed(1, MAX_REPEAT_COUNT)
            .forEach( _ -> {
                try (Connection conn = hikariCp.getConnection()) {
                    conn.createStatement().executeQuery("select 1");
                }catch (Exception e) {throw new RuntimeException(e);}
            });

    long end = System.currentTimeMillis() - start;

    IO.println("%d번 연결시 %d ms 만큼 시간 소요".formatted(MAX_REPEAT_COUNT, end));

}