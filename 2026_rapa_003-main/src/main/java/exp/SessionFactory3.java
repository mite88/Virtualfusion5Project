package exp;

import mapper.s1.TestMapperV3;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

public class SessionFactory3 {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/mybatis_shop";
    private static final String USERNAME = "rapa_mybatis";
    private static final String PASSWORD = "pwd1!";


    static void main() {


        PooledDataSource dataSource = new PooledDataSource();

        dataSource.setDriver(DRIVER);
        dataSource.setUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);

        // <transactionManager>
        JdbcTransactionFactory transactionFactory = new JdbcTransactionFactory();

        Environment environment = new Environment(
                "exp3",
                transactionFactory,
                dataSource
        );

        Configuration configuration = new Configuration(environment);

        configuration.addMappers("mapper.s1");

        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(configuration);

        try (SqlSession session = sessionFactory.openSession()) {

            TestMapperV3 mapper = session.getMapper(TestMapperV3.class);

            Integer result = mapper.test();

            System.out.println("result = " + result);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }



    }

}
