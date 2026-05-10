package util;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

public class IbatisPooledCpConnector
        implements MyBatisSessionConnector {

    private final SqlSessionFactory sqlSessionFactory;
    private final PooledDataSource dataSource;

    public IbatisPooledCpConnector(
            String mappersPackage,
            String aliasPackage
    ) {

        PooledDataSource pooledDataSource = new PooledDataSource();

        pooledDataSource.setDriver(DRIVER);
        pooledDataSource.setUrl(URL);
        pooledDataSource.setUsername(USERNAME);
        pooledDataSource.setPassword(PASSWORD);

        Environment environment = new Environment(
                "prod",
                new JdbcTransactionFactory(), // transactionManger type=jdbc
                pooledDataSource
        );

        // configuration>environments>environment>datasource+transactionManager

        Configuration configuration = new Configuration(environment);

        configuration.setMapUnderscoreToCamelCase(true);
        configuration.addMappers(mappersPackage);

        dataSource = pooledDataSource;
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);


    }

    @Override
    public SqlSession openSession() {
        return sqlSessionFactory.openSession();
    }

    @Override
    public SqlSession openSession(boolean autoCommit) {
        return sqlSessionFactory.openSession(autoCommit);
    }

}
