package kr.ac.jejunu.userdao;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertEquals;

public class UserDaoTest {

    private UserDao userDao;

    @Before
    public void setUp() throws Exception {
//        ApplicationContext context =new AnnotationConfigApplicationContext(DaoFactory.class);
        ApplicationContext context = new GenericXmlApplicationContext("DaoFactory.xml");
        userDao = (UserDao) context.getBean("userDao");
//        userDao = new DaoFactory().userDao();
    }

    @Test
    public void testGetUser() throws SQLException, ClassNotFoundException {
        Long id = 1L;
        String name = "허윤호";
        String password = "1234";

        User user = userDao.get(id);
        assertEquals(id, user.getId());
        assertEquals(name, user.getName());
        assertEquals(password, user.getPassword());
    }

    @Test
    public void testAddUser() throws Exception {
        // Given
        final String name = "keuroo";
        final String password = "gom";
        final User user = new User();
        user.setName(name);
        user.setPassword(password);
        // When
        long lastInsertId = userDao
                .add(user);
        final User fetchedUser = userDao.get(lastInsertId);
        // Then
        assertThat(fetchedUser.getId(), is(lastInsertId));
        assertThat(fetchedUser.getName(), is(name));
        assertThat(fetchedUser.getPassword(), is(password));
    }

    @Test
    public void testUpdateUser() throws Exception {
        // Given
        final String name = "keuroo";
        final String password = "gom";
        final String updaName = "updatekeuroo";
        final String updatePassword = "updateGom";
        final User user = new User();
        user.setName(name);
        user.setPassword(password);
        // When
        long lastInsertId = userDao.add(user);
        final User fetchedUser = userDao.get(lastInsertId);

        fetchedUser.setName(updaName);
        fetchedUser.setPassword(updatePassword);

        userDao.update(fetchedUser);
        // Then
        assertThat(fetchedUser.getId(), is(lastInsertId));
        assertThat(fetchedUser.getName(), is(updaName));
        assertThat(fetchedUser.getPassword(), is(updatePassword));

    }

    @Test
    public void testDeleteUser() throws Exception {
        // Given
        final String name = "keuroo";
        final String password = "gom";
        final User user = new User();
        user.setName(name);
        user.setPassword(password);
        // When
        long lastInsertId = userDao.add(user);
        userDao.delete(lastInsertId);
        final User deletedUser = userDao.get(lastInsertId);
        // Then
        assertThat(deletedUser, nullValue());
    }
}
