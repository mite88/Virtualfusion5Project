package util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class ConnectionPoolUtil {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/jdbc_shop";
    private static final String USERNAME = "rapa_jdbc";
    private static final String PASSWORD = "pwd1!";
    private static final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";

    private static final HikariDataSource hikariDataSource;

    static {

        HikariConfig config = new HikariConfig();

        config.setJdbcUrl(JDBC_URL);
        config.setUsername(USERNAME);
        config.setPassword(PASSWORD);
        config.setDriverClassName(DRIVER_CLASS_NAME);

        hikariDataSource = new HikariDataSource(config);

    }

    public static DataSource getDataSource() {
        return hikariDataSource;
    }

    public static Connection getConnection() throws SQLException {
        return hikariDataSource.getConnection();
    }

}
