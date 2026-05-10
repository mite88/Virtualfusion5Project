package exp;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.sql.DataSource;
import java.io.InputStream;

public class SessionFactory4 {

    static void main() throws Exception {

        String xmlFileDir = "mybatis-hikaricp-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(xmlFileDir);

        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sessionFactory.openSession()) {

            DataSource dataSource = sessionFactory.getConfiguration()
                    .getEnvironment()
                    .getDataSource();

            System.out.println("DataSource 클래스 : " + dataSource.getClass().getName());


        } catch (Exception e) {
            throw new RuntimeException(e);
        }



    }

}
