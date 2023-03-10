package springEx.springEx.strategyAndProxy;

import org.springframework.jdbc.core.JdbcTemplate;
import springEx.springEx.domain.ConnectionStatus;
import springEx.springEx.domain.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static springEx.springEx.domain.ConnectionStatus.*;

public class TestDao {
    private JdbcTemplate template;

    public void add(User user) {
        template.update("");
    }
}
