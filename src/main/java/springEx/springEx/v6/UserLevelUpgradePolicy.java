package springEx.springEx.v6;

import springEx.springEx.domain.User;

public interface UserLevelUpgradePolicy {
    boolean isUpgradeable(User user);
    void upgradeLevel(User user);
}
