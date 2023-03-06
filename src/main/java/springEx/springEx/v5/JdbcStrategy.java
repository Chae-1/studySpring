package springEx.springEx.v5;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface JdbcStrategy {
    PreparedStatement strategy(Connection c) throws SQLException;
}
