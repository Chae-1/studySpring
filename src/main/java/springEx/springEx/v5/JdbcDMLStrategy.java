package springEx.springEx.v5;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface JdbcDMLStrategy {
    ResultSet executeQuery(PreparedStatement ps) throws SQLException;
}
