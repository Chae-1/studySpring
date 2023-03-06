package springEx.springEx.v6;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement("insert into user(id, name, password) values(?, ?, ?)");
                ps.setString(1, user.getId());
                ps.setString(2, user.getName());
                ps.setString(3, user.getPassword());
                return ps;
            }
        });
    }

    // 1. user 정보를 조회 하는 것에 관심
    public User get(String id) throws SQLException {
        return jdbcTemplate.queryForObject("select * from user where id = ?", new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                user.setId(rs.getString("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                return user;
            }
        }, id);
    }

    public void deleteAll() throws SQLException {
        jdbcTemplate.update("delete from user");
    }
    public List<User> getAll() {
        return null;
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
