import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MappingEntityManagerFactory1 {

    void main() {

        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-exp-2")) {
            emf.close();
        } catch ( Exception e ) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

}
