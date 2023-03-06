package springEx.springEx.v2;

import springEx.springEx.domain.ConnectionStatus;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static springEx.springEx.domain.ConnectionStatus.*;

public class UserDaoTemplateMethodVersionA extends UserDaoTemplateMethod{
    @Override
    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
