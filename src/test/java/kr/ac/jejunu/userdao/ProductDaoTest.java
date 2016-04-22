package kr.ac.jejunu.userdao;

import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;

/**
 * Created by sleepbear on 2016. 4. 22..
 */
public class ProductDaoTest {

    private ProductDao productDao;

    @Before
    public void setUp() throws Exception {
        productDao = new DaoFactory().productDao();
    }

    @Test
    public void testGetProduct() throws SQLException, ClassNotFoundException {
        Long id = 1L;
        String name = "허윤호";
        String password = "1234";

        User user = productDao.get(id);
        assertEquals(id, user.getId());
        assertEquals(name, user.getName());
        assertEquals(password, user.getPassword());
    }

    @Test
    public void testAddProduct() throws Exception {
        // Given

        final String name = "keuroo";
        final String password = "gom";
        final User user = new User();
        user.setName(name);
        user.setPassword(password);
        // When
        long lastInsertId = productDao
                .add(user);
        final User fetchedUser = productDao.get(lastInsertId);
        // Then
        assertThat(fetchedUser.getId(), is(lastInsertId));
        assertThat(fetchedUser.getName(), is(name));
        assertThat(fetchedUser.getPassword(), is(password));
    }
}