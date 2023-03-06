package springEx.springEx.v5;

import springEx.springEx.ConnectionMaker;
import springEx.springEx.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// 1. 연결에 대한 관심사를 완전히 다른 클래스로 분리
public class UserDaoV5 {
    private MyJdbcTemplate template;

    public UserDaoV5(ConnectionMaker maker) {
        template = new MyJdbcTemplate(maker);
    }

    public void add(User user) {
        template.template(new JdbcStrategy() {
            @Override
            public PreparedStatement strategy(Connection c) throws SQLException {
                PreparedStatement statement = c.prepareStatement("insert into user(id, name, password) values(?, ?, ?)");
                statement.setString(1, user.getId());
                statement.setString(2, user.getName());
                statement.setString(3, user.getPassword());
                return statement;
            }
        });
    }

    // 1. user 정보를 조회 하는 것에 관심
    public User get(String id) throws SQLException {
        User user = new User();
        template.dmlTemplate((c) -> {
            PreparedStatement ps = c.prepareStatement("select * from user where id = ?");
            ps.setString(1, id);
            return ps;
        }, (ps) -> {
            ResultSet rs = ps.executeQuery();
            rs.next();
            user.setId(rs.getString("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
            return rs;
        });

        return user;
    }

    public void deleteAll() throws SQLException {
        executeSql("delete from user");
    }

    private void executeSql(String sql) {
        template.template((c) -> {
            return c.prepareStatement(sql);
        });
    }
}
