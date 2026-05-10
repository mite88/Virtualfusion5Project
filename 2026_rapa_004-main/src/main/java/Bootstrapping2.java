import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceConfiguration;

import java.util.Map;

public class Bootstrapping2 {

    void main() {

        PersistenceConfiguration cfg =
                new PersistenceConfiguration("hibernate-exp")
                        .property(PersistenceConfiguration.JDBC_DRIVER, "org.h2.Driver")
                        .property(PersistenceConfiguration.JDBC_URL, "jdbc:h2:mem:test-db")
                        .property(PersistenceConfiguration.JDBC_USER, "sa")
                        .property(PersistenceConfiguration.JDBC_PASSWORD, "")
                ;

        EntityManagerFactory emf = cfg.createEntityManagerFactory();

        Map<String, String> configMap = Map.of(
                PersistenceConfiguration.JDBC_DRIVER, "org.h2.Driver",
                PersistenceConfiguration.JDBC_URL, "jdbc:h2:mem:test-db",
                PersistenceConfiguration.JDBC_USER, "sa",
                PersistenceConfiguration.JDBC_PASSWORD, ""
        );

        PersistenceConfiguration config2 = new PersistenceConfiguration("hibernate-exp-2")
                .properties(configMap);

        EntityManagerFactory emf2 = config2.createEntityManagerFactory();

    }

}
