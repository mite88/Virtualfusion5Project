package exp;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import mapper.s1.TestMapperV2;

import java.io.InputStream;

public class SessionFactory2 {

    static void main() throws Exception {

        String xmlFileDir = "mybatis-config2.xml";

        InputStream xmlInputStream = Resources.getResourceAsStream(xmlFileDir);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(xmlInputStream);

        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {

            TestMapperV2 mapper = sqlSession.getMapper(TestMapperV2.class);
            Integer result = mapper.test();

            System.out.println("result = " + result);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

}
