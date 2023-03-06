package springEx.springEx.v2;

import springEx.springEx.domain.User;
import java.sql.*;
// 1. 연결에 대한 관심사를 추상 메서드를 사용해서 하위 클래스로 퍼트렸다.
// 2. 깔끔하게 분리가 된다. 상위 클래스에서는 어떻게 연결이 되는지 알지 못한다.
// 단점, 상속을 이용한다.
public abstract class UserDaoTemplateMethod {

    public void add(User user) throws SQLException {
        Connection c = getConnection();

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
        Connection c = getConnection();
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

    protected abstract Connection getConnection() throws SQLException;

    public static void main(String[] args) {
        Connection c = null;
    }

}
