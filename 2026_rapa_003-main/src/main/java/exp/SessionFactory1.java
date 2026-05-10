package exp;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.sql.DataSource;
import java.io.InputStream;

public class SessionFactory1 {

    static void main(String[] args) throws Exception {

        // SqlSessionFactory
        // DB Connection Settings
        // 실제 쿼리를 실행한다.

        String xmlFileDir = "mybatis-config1.xml";

        InputStream xmlInputStream = Resources.getResourceAsStream(xmlFileDir);

        // MyBatis 전체 설정 정보와 실행 환경을 내부에 포함하고 있는 설정 컨테이너 객체
        // SqlSession 객체를 생성하는 객체
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(xmlInputStream);

        // 실제 작업자
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {


            DataSource dataSource = sqlSessionFactory.getConfiguration()
                    .getEnvironment()
                    .getDataSource();

            System.out.println("DataSource 클래스 : " + dataSource.getClass().getName());

            System.out.println("Sql 세션 획득 성공!");

            // namespace.query_id
            // SELECT 1;
            Integer result = sqlSession.selectOne("test.test");

            System.out.println("result = " + result);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }



    }

}
