import util.ConnectionUtil;

import java.sql.Connection;

void main() {
    connectionTest();
}

void connectionTest() {
    final int MAX_REPEAT_COUNT = 50;

    long start = System.currentTimeMillis();

    IntStream.rangeClosed(1, MAX_REPEAT_COUNT)
            .forEach( _ -> {
                try (Connection conn = ConnectionUtil.MySql.getConnection()) {
                    conn.createStatement().executeQuery("select 1");
                }catch (Exception e) {throw new RuntimeException(e);}
            });

    long end = System.currentTimeMillis() - start;

    IO.println("%d번 연결시 %d ms 만큼 시간 소요".formatted(MAX_REPEAT_COUNT, end));

}