import util.ConnectionUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

void main() {

    try (Connection conn = ConnectionUtil.MySql.getConnection()) {

        String insertMemberSql = genMemberInsertQuery("김민수", "1234", "user1@example.com", "10000");

        Statement stmt = conn.createStatement();

//        // execute()
//        boolean executeResult = stmt.execute(insertMemberSql);
//
//        if ( !executeResult ) {
//            int affectedRowCounter = stmt.getUpdateCount();
//            System.out.println("affectedRowCounter = " + affectedRowCounter);
//        }

        // executeUpdate()
//        int affectedRowCount2 = stmt.executeUpdate(
//                genMemberInsertQuery(
//                        "박민준",
//                        "5678",
//                        "user2@example.com",
//                        "30000"
//                )
//        );
//
//        System.out.println("총 %d개의 행이 영향을 받았습니다.".formatted(affectedRowCount2));


        ResultSet selectMemberResultSet = stmt.executeQuery("""
        select
            *
        from
            member
        """);

        while(selectMemberResultSet.next()) {
            System.out.println("""
            id = %d,
            name = %s,
            password = %s,
            email = %s,
            balance = %s
            """.formatted(
                    selectMemberResultSet.getLong("id"),
                    selectMemberResultSet.getString("name"),
                    selectMemberResultSet.getString("password"),
                    selectMemberResultSet.getString("email"),
                    selectMemberResultSet.getInt("balance")
            ));
        }
        selectMemberResultSet.close();

        stmt.close();

    } catch ( Exception e ) {

    }

}

private String genMemberInsertQuery(String name, String pwd, String email, String balance) {
    return """
            INSERT INTO member
              ( name, password, email, balance )
            VALUES
              ( '%s', '%s', '%s', '%s' )
            """.formatted(name, pwd, email, balance);
}