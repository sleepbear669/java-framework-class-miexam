package kr.ac.jejunu.userdao;

import org.junit.Test;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;

public class UserDaoTest {
    @Test
    public void testGetUser() throws SQLException, ClassNotFoundException {
        UserDao userDao = new UserDao();
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
        final UserDao userDao = new UserDao();
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
