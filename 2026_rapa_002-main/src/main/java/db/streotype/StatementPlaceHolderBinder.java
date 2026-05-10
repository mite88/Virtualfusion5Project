package db.streotype;

import java.sql.PreparedStatement;
import java.sql.SQLException;


public interface StatementPlaceHolderBinder {

    void set(PreparedStatement stmt) throws SQLException;

}
