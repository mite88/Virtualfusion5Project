import util.ConnectionPoolUtil;

import java.sql.*;

void main() throws Exception {

    Connection connection = ConnectionPoolUtil.getConnection();

    try {

        connection.setAutoCommit(false);

        Long memberId = 1L;
        Long productId = 1L;
        int quantity = 2;

        int stock = getStock(connection, productId);
        if ( stock < quantity ) {
            throw new RuntimeException("재고가 부족합니다");
        }

        int price = getPrice(connection, productId);
        int totalPrice = price * quantity;

        int balance = getBalance(connection, memberId);
        if ( balance < totalPrice )
            throw new RuntimeException("잔액이 부족합니다");



        // 재고 차감 + 잔액 차감 + 주문 생성 + 주문 상세 생성
        decreaseStock(connection, productId, quantity);

        decreaseBalance(connection, memberId, totalPrice);

        Long orderId = insertOrder(connection, memberId);

        insertOrderItem(connection, orderId, productId, quantity, price);

        connection.commit();

    } catch (Exception e) {
        connection.rollback();
    } finally {
        connection.setAutoCommit(true);
    }

}

private static int getStock(Connection conn, Long productId) throws SQLException {

    String sql = "select stock from product where id = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setLong(1, productId);
        try (ResultSet rs = stmt.executeQuery()) {
            return rs.next() ? rs.getInt("stock") : 0;
        }
    }

}

private static int getPrice(Connection conn, Long productId) throws SQLException {

    String sql = "select price from product where id = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setLong(1, productId);
        try (ResultSet rs = stmt.executeQuery()) {
            return rs.next() ? rs.getInt("price") : 0;
        }
    }

}

private static int getBalance(Connection conn, Long memberId) throws SQLException {

    String sql = "select balance from member where id = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setLong(1, memberId);
        try (ResultSet rs = stmt.executeQuery()) {
            return rs.next() ? rs.getInt("balance") : 0;
        }
    }

}

private static void decreaseStock(Connection conn, Long productId, int quantity) throws SQLException {

    String sql = "update product set stock = stock - ? where id = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setLong(1, quantity);
        stmt.setLong(2, productId);
        stmt.executeUpdate();
    }

}

private static void decreaseBalance(Connection conn, Long memberId, int amount) throws SQLException {

    String sql = "update member set balance = balance - ? where id = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setLong(1, amount);
        stmt.setLong(2, memberId);
        stmt.executeUpdate();
    }

}

private static Long insertOrder(Connection conn, Long memberId) throws SQLException {

    String sql = "insert into orders (member_id, status) values (? , 'ORDERED')";

    try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

        stmt.setLong(1, memberId);
        stmt.executeUpdate();

        try (ResultSet keys = stmt.getGeneratedKeys()) {
            if (keys.next()) return keys.getLong(1);
            throw new RuntimeException("주문 Id 생성 실패");
        }

    }

}

private static void insertOrderItem(Connection conn, Long orderId, Long productId, int quantity, int price) throws SQLException {

    String sql = "insert into order_item (order_id, product_id, quantity, price) values (?, ?, ?, ?)";

    try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

        stmt.setLong(1, orderId);
        stmt.setLong(2, productId);
        stmt.setLong(3, quantity);
        stmt.setLong(4, price);
        stmt.executeUpdate();

    }

}


