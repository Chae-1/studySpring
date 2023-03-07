package springEx.springEx.v6;

import lombok.extern.slf4j.Slf4j;
import springEx.springEx.domain.Level;
import springEx.springEx.domain.User;

import java.util.List;

import static springEx.springEx.domain.Level.*;

@Slf4j
public class UserService {
    UserDaoV6 userDaoV6;
    public UserService(UserDaoV6 userDaoV6) {
        this.userDaoV6 = userDaoV6;
    }

    public void upgradeLevels() {
        List<User> users = userDaoV6.getAll();
        for (User user : users) {
            if(isUpgradeable(user)) {
                upgradeLevel(user);
            }
        }
    }

    private void upgradeLevel(User user) {
        if(user.getLevel() == BASIC) {
            user.setLevel(SILVER);
        } else if (user.getLevel() == SILVER) {
            user.setLevel(GOLD);
        } // if else-if,
          // 1. 업그레이드 레벨에서 한 단계 위로 상승 시키기 위한 로직.
        userDaoV6.update(user);
    }

    private boolean isUpgradeable(User user) {
        switch(user.getLevel()) {
            case BASIC:
                return user.getLogin() >= 50;
            case SILVER:
                return user.getRecommend() >= 30;
            case GOLD:
                return false;
            default:
                throw new IllegalArgumentException("argument Exception");
        }
        // 1. 유저 등급을 확인한다.
        // 2. 로그인 횟수가 일정 횟수 이상일 때
    }

    public void add(User user) {
        if(user.getLevel() == null)
            user.setLevel(BASIC);
        userDaoV6.add(user);
    }

}
