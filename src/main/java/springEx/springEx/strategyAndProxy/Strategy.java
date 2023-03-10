package springEx.springEx.strategyAndProxy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface Strategy {
    PreparedStatement execute(Connection c) throws SQLException;
}
