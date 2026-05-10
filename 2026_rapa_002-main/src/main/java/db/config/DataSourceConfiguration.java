package db.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

/*
설정 클래스
 */
public class DataSourceConfiguration {

    private static final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/jdbc_shop";
    private static final String USERNAME = "rapa_jdbc";
    private static final String PASSWORD = "pwd1!";

    private static final HikariDataSource dataSource;

    static {

        HikariConfig config = new HikariConfig();

        config.setDriverClassName(DRIVER_CLASS_NAME);
        config.setJdbcUrl(URL);
        config.setUsername(USERNAME);
        config.setPassword(PASSWORD);

        dataSource = new HikariDataSource(config);

    }

    public static DataSource getDataSource() {
        return dataSource;
    }


}
