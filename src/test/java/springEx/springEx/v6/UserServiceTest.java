package springEx.springEx.v6;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springEx.springEx.Factory;
import springEx.springEx.domain.Level;
import springEx.springEx.domain.User;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class UserServiceTest {

    TestUserService userService;
    UserDaoV6 daoV6;
    List<User> users;
    @BeforeEach
    public void init() {
        ApplicationContext context = new AnnotationConfigApplicationContext(Factory.class);
        daoV6 = context.getBean("userDaoV6", UserDaoV6.class);
        userService = new TestUserService(daoV6, "b");
        User user = new User("a", "a", "a", 50, 1, Level.BASIC);
        User user1 = new User("b", "b", "b", 50, 30, Level.SILVER);
        User user2 = new User("c", "c", "c", 50, 50, Level.SILVER);
        users = Arrays.asList(
                user, user1, user2
        );

    }

    @AfterEach
    public void deleteAll() {
        daoV6.deleteAll();
    }

    @Test
    public void addOrNothing() {
        for (User user : users) {
            daoV6.add(user);
        }
        try {
            userService.upgradeLevels();
            Assertions.fail("fail");
        } catch (TestUserServiceException e) {
            e.printStackTrace();
        }
        List<User> all = daoV6.getAll();
        Assertions.assertThat(users.get(0).getLevel()).isEqualTo(Level.SILVER);
    }

    @Test
    public void addTest() {
        daoV6.deleteAll();
        User user = new User("f", "f", "f", 1, 1, null);
        userService.add(user);

        User findUser = daoV6.get(user.getId());
        assertThat(findUser.getLevel()).isEqualTo(Level.BASIC);
    } // 두개의 조건문이 나올 경우, 이것을 switch-case 문으로 바꿔보는건 어떨까?

    static class TestUserService extends UserService {
        private String id;
        public TestUserService(UserDaoV6 userDaoV6, String id) {
            super(userDaoV6);
            this.id = id;
        }

        @Override
        protected void upgradeLevel(User user) {
            if(user.getId().equals(id))
                throw new TestUserServiceException();
            super.upgradeLevel(user);
        }
    }

    static class TestUserServiceException extends RuntimeException {
    }
}