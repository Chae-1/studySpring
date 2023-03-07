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

import static org.assertj.core.api.Assertions.assertThat;

class UserServiceTest {

    UserService userService;
    UserDaoV6 daoV6;
    @BeforeEach
    public void init() {
        ApplicationContext context = new AnnotationConfigApplicationContext(Factory.class);
        userService = context.getBean("userService", UserService.class);
        daoV6 = context.getBean("userDaoV6", UserDaoV6.class);
    }

    @AfterEach
    public void deleteAll() {

    }

    @Test
    public void addTest() {
        User user = new User("a", "a", "a", 1, 1, null);
        User user1 = new User("b", "a", "a", 50, 20, Level.SILVER);
        userService.add(user);

        User findUser = daoV6.get(user.getId());
        assertThat(findUser.getLevel()).isEqualTo(Level.BASIC);
    } // 두개의 조건문이 나올 경우, 이것을 switch-case 문으로 바꿔보는건 어떨까?
}