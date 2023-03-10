package springEx.springEx.v6;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.mail.MailSender;
import org.springframework.transaction.PlatformTransactionManager;
import springEx.springEx.Factory;
import springEx.springEx.domain.Level;
import springEx.springEx.domain.User;

import javax.sql.DataSource;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    TestUserServiceImpl userService;
    UserServiceTx userServiceTx;
    UserDaoV6 daoV6;
    List<User> users;
    MailSender mailSender;
    PlatformTransactionManager manager;
    @BeforeEach
    public void init() {
        ApplicationContext context = new AnnotationConfigApplicationContext(Factory.class);
        daoV6 = context.getBean("userDaoV6", UserDaoV6.class);
        userService = new TestUserServiceImpl("b");
        mailSender = context.getBean("mailSender", MailSender.class);
        manager = context.getBean("transactionManager", PlatformTransactionManager.class);
        User user = new User("a", "a", "a", 50, 1, Level.BASIC, "coguddlf1000@gmail.com");
        User user1 = new User("b", "b", "b", 50, 30, Level.SILVER, "coguddlf@gmail.com");
        User user2 = new User("c", "c", "c", 50, 50, Level.SILVER, "coguddlf1000@naver.com");
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
        userService.setMailSender(mailSender);
        userService.setUserDaoV6(daoV6);

        userServiceTx = new UserServiceTx(userService, manager);

        daoV6.deleteAll();
        for (User user : users) {
            daoV6.add(user);
        }
        try {
            userServiceTx.upgradeLevels();
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<User> all = daoV6.getAll();
        Assertions.assertThat(all.get(0).getLevel()).isEqualTo(Level.BASIC);
    }

    @Test
    public void addTest() {
        daoV6.deleteAll();

        userService.add(users.get(0));

        User findUser = daoV6.get(users.get(0).getId());
        assertThat(findUser.getLevel()).isEqualTo(Level.BASIC);
    } // 두개의 조건문이 나올 경우, 이것을 switch-case 문으로 바꿔보는건 어떨까?

    @Test
    void mockUpgradeLevels() {
        UserServiceImpl userServiceImpl = new UserServiceImpl();

        UserDaoV6 mockUserDao = mock(UserDaoV6.class);
        when(mockUserDao.getAll()).thenReturn(this.users);
        userServiceImpl.setUserDaoV6(mockUserDao);

        MailSender mockMailSender = mock(MailSender.class);
        userServiceImpl.setMailSender(mockMailSender);
        userServiceImpl.upgradeLevels();

        verify(mockUserDao, times(3)).update(any(User.class));
    }

    @Test
    void reflectTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String name = "Spring";

        assertThat(name.length()).isEqualTo(6);

        Method method = name.getClass().getMethod("length");

        System.out.println(method.invoke(name));
    }

    static class TestUserServiceImpl extends UserServiceImpl {
        private String id;
        public TestUserServiceImpl(String id) {
            super();
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