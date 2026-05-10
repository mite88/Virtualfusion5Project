package exp;

import util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ResultSetTest {

    static void main() {

        try (Connection conn = ConnectionUtil.MySql.getConnection()) {

            String selectAllProductSql = """
                    select
                        *
                    from
                        product
                    """;

            PreparedStatement multipleResultStmt = conn.prepareStatement(selectAllProductSql);
            ResultSet multipleResultSet = multipleResultStmt.executeQuery();

            // Iterator
            while(multipleResultSet.next()) {

                long id = multipleResultSet.getLong("id");
                String name = multipleResultSet.getString("name");
                int price = multipleResultSet.getInt("price");
                int stock = multipleResultSet.getInt("stock");

                System.out.printf("%d번 상품 %s, 가격: %d, 재고: %d\n", id, name, price, stock);

            }
            System.out.println();

            multipleResultSet.close();
            multipleResultStmt.close();

            long targetMemberId = 1L;

            String selectSpecificProductQuery = """
                    select
                        *
                    from
                        product
                    where
                        id = ?
                    """;

            PreparedStatement singleResultStmt = conn.prepareStatement(selectSpecificProductQuery);
            singleResultStmt.setLong(1, targetMemberId);

            ResultSet singleRs = singleResultStmt.executeQuery();

            if ( singleRs.next() ) {
                long id = singleRs.getLong("id");
                String name = singleRs.getString("name");
                int price = singleRs.getInt("price");
                int stock = singleRs.getInt("stock");

                System.out.printf("%d번 상품 %s, 가격: %d, 재고:%d\n", id, name, price, stock);
            }
            System.out.println();

            singleRs.close();
            singleResultStmt.close();

        } catch ( Exception e) {
            throw new RuntimeException(e);
        }


    }

}
