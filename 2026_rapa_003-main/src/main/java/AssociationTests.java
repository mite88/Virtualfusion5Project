import domain.Order;
import mapper.s4.OrderMapper;
import org.apache.ibatis.session.SqlSession;
import util.HikariMyBatisConnector;
import util.MyBatisSessionConnector;

import java.util.List;

public class AssociationTests {

    private static final String MAPPER_PACKAGE = "mapper.s4";
    private static final String ALIAS_PACKAGE = "domain";

    private static final MyBatisSessionConnector connector = new HikariMyBatisConnector(
            MAPPER_PACKAGE,
            ALIAS_PACKAGE
    );

    void main() {
//        test1();
        test2();
        test3();
    }

    void test1() {

        System.out.println("AssociationTests.test1");

        try (SqlSession session = connector.openSession()) {

            OrderMapper mapper = session.getMapper(OrderMapper.class);

            long targetId = 1L;

            List<Order> orders = mapper.findByMemberId(targetId);

            for (Order order : orders) {
                String name = order.getMember().getName();
                Long id = order.getId();
                System.out.println("order_id = " + id);
                System.out.println("name = " + name);
            }

        }

    }

    void test2() {

        System.out.println("AssociationTests.test2");

        try (SqlSession session = connector.openSession()) {

            OrderMapper mapper = session.getMapper(OrderMapper.class);

            List<Order> orders = mapper.findAllInlineMapping();
            int rows = orders.size();
            System.out.println("rows = " + rows);

        }

    }

    void test3() {

        System.out.println("AssociationTests.test3");

        try (SqlSession session = connector.openSession()) {

            OrderMapper mapper = session.getMapper(OrderMapper.class);

            List<Order> orders = mapper.findAllWithMemberNestedSelect();

            int rows = orders.size();
            System.out.println("rows = " + rows);


        }


    }
}
