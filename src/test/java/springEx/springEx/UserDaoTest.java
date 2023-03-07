package springEx.springEx;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import springEx.springEx.domain.Level;
import springEx.springEx.domain.User;
import springEx.springEx.v6.UserDaoV6;
import springEx.springEx.v6.UserService;

import javax.sql.DataSource;
import java.io.*;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static springEx.springEx.domain.Level.BASIC;

@ContextConfiguration(classes = Factory.class)
public class UserDaoTest {

    DataSource dataSource;
    UserDaoV6 userDao;
    List<User> list;
    @Autowired
    UserService service;

    @Test
    void isExist() {
        Assertions.assertNotNull(service);
    }
    @BeforeEach
    public void beforeEach() {
        ApplicationContext context = new AnnotationConfigApplicationContext(Factory.class);
        dataSource = context.getBean("dataSource", DataSource.class);
        userDao = context.getBean("userDaoV6", UserDaoV6.class);
        service = context.getBean("userService", UserService.class);
        list = Arrays.asList(
                new User("g", "g", "g", 0, 0, BASIC),
                new User("c", "c", "c", 100, 30, Level.SILVER),
                new User("a", "a", "a", 50, 100, Level.GOLD)
        );
    }

    @AfterEach
    public void afterEach() throws SQLException {
        userDao.deleteAll();
    }

    @Test
    public void testAdd() {
        userDao.add(new User("y", "y", "y", 0, 0, BASIC));
        User cc = userDao.get("y");
        Assertions.assertTrue(cc.getId().equals("y"));
        assertEquals(cc.getLevel(), BASIC);
    }

    @Test
    public void getAll() {
        userDao.add(list.get(0));
        List<User> listA = userDao.getAll();
        assertEquals(listA.size(), 1);

        userDao.add(list.get(1));
        List<User> listB = userDao.getAll();
        assertEquals(listB.size(), 2);


    }

    @Test
    public void update() {
        User user = new User("hyeongil", "didi", "dad", 10, 10, BASIC);
        userDao.add(user);

        user.setName("hyeongil");
        user.setLogin(12);
        userDao.update(user);

        User findUser = userDao.get(user.getId());
        assertEquals(user.getName(), findUser.getName());
    }

    @Test
    public void testUpgrade() {
        for (User user : list) {
            userDao.add(user);
        }
        service.upgradeLevels();
        checkLevel(list.get(1), Level.GOLD);
    }

    public void checkLevel(User user1, Level expectedLevel) {
        User userUpdate = userDao.get(user1.getId());
        assertEquals(userUpdate.getLevel(), expectedLevel);
    }


//    @Test
    public void existFile() throws IOException {
        File file = new File("C:\\Users\\cogud\\Downloads\\springEx\\springEx\\src\\main\\resources\\beans.xml");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = null;
        while((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }


}
