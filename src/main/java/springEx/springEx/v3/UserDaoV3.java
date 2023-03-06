package springEx.springEx.v3;

import springEx.springEx.ConnectionMaker;
import springEx.springEx.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// 1. 연결에 대한 관심사를 완전히 다른 클래스로 분리
public class UserDaoV3 {
    private ConnectionMaker connectionMaker;

    public UserDaoV3(ConnectionMaker maker) {
        this.connectionMaker = maker;
    }

    public void setMaker(ConnectionMaker maker) {
        this.connectionMaker = maker;
    }

    public void add(User user) throws SQLException {
        Connection c = connectionMaker.getConnection();

        PreparedStatement ps = c.prepareStatement("insert into user(id, name, password) values(?, ?, ?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close();
        c.close();
    }

    // 1. user 정보를 조회 하는 것에 관심
    public User get(String id) throws SQLException {
        Connection c = connectionMaker.getConnection();
        PreparedStatement ps = c.prepareStatement("select * from user where id = ?");
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        rs.next();

        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        rs.close();
        ps.close();
        c.close();

        return user;
    }

    public void deleteAll() throws SQLException {
        Connection c = connectionMaker.getConnection();

        PreparedStatement ps = c.prepareStatement("delete from user");
        ps.executeUpdate();

        ps.close();
        c.close();
    }

    public void setConnectionMaker(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }
}
