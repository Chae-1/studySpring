package springEx.springEx;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springEx.springEx.domain.User;
import springEx.springEx.v3.UserDaoV3;
import springEx.springEx.v4.UserDaoV4;
import springEx.springEx.v5.UserDaoV5;
import springEx.springEx.v6.UserDaoV6;

import java.io.*;
import java.sql.SQLException;
import java.util.List;

public class UserDaoTest {

    UserDaoV6 userDao;

    @BeforeEach
    public void beforeEach() {
        ApplicationContext context = new AnnotationConfigApplicationContext(Factory.class);
        userDao = context.getBean("userDaoV6", UserDaoV6.class);
    }

    @AfterEach
    public void afterEach() throws SQLException {
        userDao.deleteAll();
    }

    @Test
    public void testAdd() throws SQLException {
        userDao.add(new User("ccd", "cc", "cc"));
        User cc = userDao.get("ccd");
        Assertions.assertTrue(cc.getId().equals("ccd"));
    }

    @Test
    public void getAll() {
        User userA = new User("a", "a", "a");
        userDao.add(userA);
        List<User> listA = userDao.getAll();
        Assertions.assertEquals(listA.size(), 1);

        User userB = new User("b", "b", "b");
        userDao.add(userA);
        List<User> listB = userDao.getAll();
        Assertions.assertEquals(listA.size(), 2);


    }

    @Test
    public void existFile() throws IOException {
        File file = new File("C:\\Users\\cogud\\Downloads\\springEx\\springEx\\src\\main\\resources\\beans.xml");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = null;
        while((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }


}