package springEx.springEx.v6;

import springEx.springEx.domain.User;

public class UserLevelUpgradePolicyImpl implements UserLevelUpgradePolicy{
    private UserDaoV6 userDaoV6;
    @Override
    public boolean isUpgradeable(User user) {
        switch(user.getLevel()) {
            case BASIC: return user.getLogin() >= 50;
            case SILVER: return user.getRecommend() >= 30;
            case GOLD: return false;
            default: throw new IllegalArgumentException("argument Exception");
        }
    }

    @Override
    public void upgradeLevel(User user) {
        user.upgradeLevel();
    }
}
