package springEx.springEx.v4;

import springEx.springEx.domain.User;
import springEx.springEx.ConnectionMaker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// 1. 연결에 대한 관심사를 완전히 다른 클래스로 분리
public class UserDaoV4 {
    private ConnectionMaker connectionMaker;

    public UserDaoV4(ConnectionMaker maker) {
        this.connectionMaker = maker;
    }

    public void setMaker(ConnectionMaker maker) {
        this.connectionMaker = maker;
    }

    public void add(User user) {
        Connection c = null;
        PreparedStatement ps = null;
        try {
            c = connectionMaker.getConnection();

            ps = c.prepareStatement("insert into user(id, name, password) values(?, ?, ?)");
            ps.setString(1, user.getId());
            ps.setString(2, user.getName());
            ps.setString(3, user.getPassword());

            ps.executeUpdate();
        } catch(SQLException e) {
            throw new RuntimeException(e);
        } finally {
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

    // 1. user 정보를 조회 하는 것에 관심
    public User get(String id) throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            c = connectionMaker.getConnection();

            ps = c.prepareStatement("select * from user where id = ?");
            ps.setString(1, id);
            rs = ps.executeQuery();

            rs.next();
            return new User(rs.getString("id"),
                    rs.getString("name"),
                    rs.getString("password"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
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

    public void deleteAll() throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;
        try {
            c = connectionMaker.getConnection();
            ps = c.prepareStatement("delete from user");
            ps.executeUpdate();
        } catch(SQLException e) {
            throw new RuntimeException(e);
        } finally {
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

    public void setConnectionMaker(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }
}
