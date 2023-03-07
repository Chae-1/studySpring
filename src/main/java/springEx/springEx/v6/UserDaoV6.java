package springEx.springEx.v6;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import springEx.springEx.domain.Level;
import springEx.springEx.domain.User;
import springEx.springEx.v5.JdbcStrategy;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDaoV6 {
    private JdbcTemplate jdbcTemplate;
    public UserDaoV6(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    public void add(User user) {
        jdbcTemplate.update("insert into user(id, name, password, login, recommend, level) values (?, ?, ?)",
                user.getId(), user.getName(), user.getPassword(),
                user.getLogin(),
                user.getRecommend(),
                user.getLevel().getType());
    }
    public User get(String id) throws SQLException {
        return jdbcTemplate.queryForObject("select * from user where id = ?", getRowMapper(), id);
    }
    private static RowMapper<User> getRowMapper() {
        return (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getString("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
            user.setLevel(Level.getLevel(rs.getInt("level")));
            user.setRecommend(rs.getInt("recommend"));
            user.setLogin(rs.getInt("login"));
            return user;
        };
    }

    public void deleteAll() throws SQLException {
        jdbcTemplate.update("delete from user");
    }
    public List<User> getAll() {
        return jdbcTemplate.query(
                "select * from user",
                getRowMapper()
        );
    }

    public void update(User user) {
        jdbcTemplate.update("update user " +
                "set name = ?, password = ?, login = ?, recommend = ?,  level = ?",
                user.getName(), user.getPassword(), user.getLogin(),
                user.getRecommend(), user.getLevel().getType());
    }
    public int getCount() {
        return jdbcTemplate.query(con -> {
            PreparedStatement ps = con.prepareStatement("select count(*) from user");
            return ps;
        }, rs -> {
            rs.next();
            return rs.getInt(1);
        });

    }
}
