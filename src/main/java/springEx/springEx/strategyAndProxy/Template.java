package springEx.springEx.strategyAndProxy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static springEx.springEx.domain.ConnectionStatus.*;

public class Template {

    public void template(Strategy strategy) {
        Connection c = null;
        PreparedStatement ps = null;
        try {
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);

            ps = strategy.execute(c);
            ps.executeQuery();

            c.commit();
        } catch(Exception e) {
            try {
                c.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } finally {
            try {
                ps.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                c.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
