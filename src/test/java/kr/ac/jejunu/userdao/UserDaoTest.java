package kr.ac.jejunu.userdao;

import org.junit.Test;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;

public class UserDaoTest {
    @Test
    public void testJejuGetUser() throws SQLException, ClassNotFoundException {
        UserDao userDao = new JejuUserDao();
        Long id = 1L;
        String name = "허윤호";
        String password = "1234";

        User user = userDao.get(id);
        assertEquals(id, user.getId());
        assertEquals(name, user.getName());
        assertEquals(password, user.getPassword());
    }

    @Test
    public void testJejuAddUser() throws Exception {
        // Given
        final UserDao userDao = new JejuUserDao();
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
    public void testHallaGetUser() throws SQLException, ClassNotFoundException {
        UserDao userDao = new HallaUserDao();
        Long id = 1L;
        String name = "허윤호";
        String password = "1234";

        User user = userDao.get(id);
        assertEquals(id, user.getId());
        assertEquals(name, user.getName());
        assertEquals(password, user.getPassword());
    }

    @Test
    public void testHallaAddUser() throws Exception {
        // Given
        final UserDao userDao = new HallaUserDao();
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


}
