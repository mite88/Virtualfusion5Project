import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Bootstrapping1 {

    void main() {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("hibernate-exp-1");

    }

}
