package springEx.springEx.v1;
import springEx.springEx.domain.User;
import java.sql.*;

import static springEx.springEx.domain.ConnectionStatus.*;
// v1 -> connection에 관한 부분을 단순하게 메서드로 추출
// 결국 관심사 자체는 분리 하지 못했다.
public class UserDao {
    // 관심사, UserDao, add
    //  1. db에 user 정보를 저장.
    //  1. connection, 2. sql 파라미터 바인딩, 3. 쿼리 실행  4. close()

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

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
