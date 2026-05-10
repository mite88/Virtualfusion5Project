import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernatePersistenceConfiguration;

public class Bootstrapping3 {

    void main() {

        HibernatePersistenceConfiguration cfg =
                new HibernatePersistenceConfiguration("hibernate-exp")
                        .jdbcDriver("org.h2.Driver")
                        .jdbcUrl("jdbc:h2:mem:test-db")
                        .jdbcUsername("sa")
                        .jdbcPassword("");

        SessionFactory emf = cfg.createEntityManagerFactory();


    }

}
