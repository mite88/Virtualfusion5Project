import util.ConnectionUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

void main() {

    try (Connection conn = ConnectionUtil.MySql.getConnection()) {

        Statement stmt = conn.createStatement();

        String user1Email = "user1@example.com";
        String user1Pwd = "' OR '' = '";

        String user1LoginSql = genLoginQuery(user1Email, user1Pwd);

        ResultSet user1LoginResultSet = stmt.executeQuery(user1LoginSql);

        if ( user1LoginResultSet.next() ) {
            System.out.println("회원 정보 ========");

            String name = user1LoginResultSet.getString("name");
            long balance = user1LoginResultSet.getLong("balance");

            System.out.println("이름 : " + name);
            System.out.println("이메일 : " + user1Email);
            System.out.println("잔고 : " + balance);

        } else {
            System.out.println("회원 정보가 일치하지 않습니다.");
        }

        user1LoginResultSet.close();
        stmt.close();


    } catch ( Exception e ) {
        throw new RuntimeException(e);
    }

}

private static String genLoginQuery(String email, String password) {
    return """
            select
                *
            from
                member
            where
                email = '%s' and password = '%s'
            """.formatted(email, password);
}