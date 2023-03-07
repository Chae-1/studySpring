package springEx.springEx.v6;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import springEx.springEx.domain.User;

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


    public void upgradeLevels() {
        List<User> users = userDaoV6.getAll();
        for (User user : users) {
            if(isUpgradeable(user)) {
                upgradeLevel(user);
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
