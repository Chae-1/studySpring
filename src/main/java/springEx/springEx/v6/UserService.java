package springEx.springEx.v6;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import springEx.springEx.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static springEx.springEx.domain.Level.*;

@Slf4j
@Setter
@Getter
public class UserService {
    private UserDaoV6 userDaoV6;
    private UserLevelUpgradePolicy policy;

    public UserService(UserDaoV6 userDaoV6) {
        this.userDaoV6 = userDaoV6;
    }

    public UserService(UserDaoV6 userDaoV6, UserLevelUpgradePolicy policy) {
        this.userDaoV6 = userDaoV6;
        this.policy = policy;
    }

    // tx의 대상이 되어야 한다.
    // 트랜잭션은 Connection 객체를 공유해야 하는데, service 계층에서 Connection에 대한 의존성을 갖게 되면 x
    //
    public void upgradeLevels() {
        List<User> users = userDaoV6.getAll();
        for (User user : users) {
            if(isUpgradeable(user)) {
                upgradeLevel(user);
            }
        }
    }
    public void upgradeLevelsTx() {
        Connection c = null;
        try {
            c.setAutoCommit(false);

            PreparedStatement ps1 = c.prepareStatement("sql 1");
            ps1.executeQuery();

            PreparedStatement ps2 = c.prepareStatement("sql 1");
            ps1.executeQuery();

            PreparedStatement ps3 = c.prepareStatement("sql 1");
            ps1.executeQuery();

            c.commit();
        } catch (SQLException e) {
            try {
                c.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    protected void upgradeLevel(User user) {
        user.upgradeLevel();
        userDaoV6.update(user);
    }
    protected boolean isUpgradeable(User user) {
        switch(user.getLevel()) {
            case BASIC: return user.getLogin() >= 50;
            case SILVER: return user.getRecommend() >= 30;
            case GOLD: return false;
            default: throw new IllegalArgumentException("argument Exception");
        }
        // 1. 유저 등급을 확인한다.
        // 2. 로그인 횟수가 일정 횟수 이상일 때
    } // 구체 인스턴스의 상태를 가지고 오는 코드가 있는 경우, 의심해봐야 한다.

    public void add(User user) {
        if(user.getLevel() == null)
            user.setLevel(BASIC);
        userDaoV6.add(user);
    }

}
