package util;

import org.apache.ibatis.session.SqlSession;

public interface MyBatisSessionConnector {

    String DRIVER = "com.mysql.cj.jdbc.Driver";
    String URL = "jdbc:mysql://localhost:3306/mybatis_shop";
    String USERNAME = "rapa_mybatis";
    String PASSWORD = "pwd1!";

    SqlSession openSession();
    SqlSession openSession(boolean autoCommit);

}
