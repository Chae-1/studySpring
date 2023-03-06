package springEx.springEx;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static springEx.springEx.domain.ConnectionStatus.*;

public class ConnectionMakerImpl implements ConnectionMaker{
    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
