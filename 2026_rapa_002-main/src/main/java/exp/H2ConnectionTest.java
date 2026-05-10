import util.ConnectionUtil;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.Statement;

void main() {

    String memberTableDdl = """
            CREATE TABLE member(
              id bigint primary key auto_increment,
              name varchar(50) not null,
              email varchar(100) unique not null,
              balance int default 0
            );
            """;

    String productTableDdl = """
            CREATE TABLE product(
              id bigint primary key auto_increment,
              name varchar(100) not null,
              price int not null,
              stock int not null default 0
            );
            """;

    try (Connection conn = ConnectionUtil.H2.getConnection()) {

        Statement stmt = conn.createStatement();

        stmt.execute(memberTableDdl);
        stmt.execute(productTableDdl);

        stmt.close();

        DatabaseMetaData metaData = conn.getMetaData();

        ResultSet memberTableResultSet = metaData.getTables(
                null,
                null,
                "%",
                new String[]{"TABLE"}
        );

        while ( memberTableResultSet.next() ) {
            String tableName = memberTableResultSet.getString("TABLE_NAME");
            if ( tableName.equals("MEMBER") || tableName.equals("PRODUCT") ) {
                System.out.println(tableName);
            }
        }

        memberTableResultSet.close();

        ResultSet memberColumns = metaData.getColumns(
                null,
                null,
                "MEMBER",
                "%"
        );

        while ( memberColumns.next() ) {
            System.out.println("컬럼명 : " + memberColumns.getString("COLUMN_NAME"));
            System.out.println("타입 : " + memberColumns.getString("TYPE_NAME"));
        }

        memberColumns.close();

    } catch ( Exception e ) {

    }


}