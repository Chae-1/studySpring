package springEx.springEx;

import springEx.springEx.domain.ConnectionStatus;
import springEx.springEx.v5.JdbcStrategy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static springEx.springEx.domain.ConnectionStatus.*;

public class DaoTx {
    public void tx(Connection con, JdbcStrategy strategy) throws SQLException {
        PreparedStatement ps = null;
        con.setAutoCommit(false);
        try {
            ps = con.prepareStatement("insert into user values()");
            strategy.strategy(con);//service
            con.commit();
        } catch (SQLException e) {
            con.rollback();
        } finally {

            ps.close();
            con.close();
        }

    }
}
