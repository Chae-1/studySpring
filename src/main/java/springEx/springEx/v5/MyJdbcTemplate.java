package springEx.springEx.v5;

import org.springframework.jdbc.core.JdbcTemplate;
import springEx.springEx.ConnectionMaker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MyJdbcTemplate {
    private ConnectionMaker connectionMaker;

    public MyJdbcTemplate(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public void template(JdbcStrategy strategy) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = connectionMaker.getConnection();
            ps = strategy.strategy(c);
            ps.executeUpdate();
        } catch(SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    public void dmlTemplate(JdbcStrategy strategy, JdbcDMLStrategy dmlStrategy) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            c = connectionMaker.getConnection();
            ps = strategy.strategy(c);
            rs = dmlStrategy.executeQuery(ps);
        } catch(SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }


}
