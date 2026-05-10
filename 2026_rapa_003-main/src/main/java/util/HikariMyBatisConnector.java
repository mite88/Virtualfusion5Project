package util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.apache.ibatis.type.TypeAliasRegistry;

public class HikariMyBatisConnector
        implements MyBatisSessionConnector{

    private final SqlSessionFactory sqlSessionFactory;
    private final HikariDataSource dataSource;

    public HikariMyBatisConnector(
            String mappersPackage,
            String aliasPackage
    ) {

        HikariConfig config = new HikariConfig();

        config.setDriverClassName(DRIVER);
        config.setJdbcUrl(URL);
        config.setUsername(USERNAME);
        config.setPassword(PASSWORD);

        dataSource = new HikariDataSource(config);

        // txmanager
        // environment

        Environment environment = new Environment(
                "prod",
                new JdbcTransactionFactory(),
                dataSource
        );

        // environment -> environments -> configuration
        Configuration configuration = new Configuration(environment);

        configuration.setMapUnderscoreToCamelCase(true);
        configuration.addMappers(mappersPackage);

        TypeAliasRegistry typeAliasRegistry = configuration.getTypeAliasRegistry();
        typeAliasRegistry.registerAliases(aliasPackage);

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
