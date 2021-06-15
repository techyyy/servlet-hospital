package dbOperationsTest;

import com.hospital.Hospital.db.ConnectionPool;
import com.hospital.Hospital.db.impl.UserDAO;
import com.hospital.Hospital.web.constants.StringConstants;
import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class UserDAOTest {
    private static final UserDAO userDAO = new UserDAO();

    @BeforeClass
    public static void beforeTest() throws ClassNotFoundException {
        MysqlConnectionPoolDataSource dataSource = new MysqlConnectionPoolDataSource();
        dataSource.setURL("jdbc:mysql://127.0.0.1:3306/hospital?user=root&password=admin");
        ConnectionPool.setDataSource(dataSource);
    }

    @Test
    public void findAllDoctorsTest() {
        assertFalse(userDAO.findAllDoctors().isEmpty());
    }

    @Test
    public void findSortedDoctorsTest() {
        assertFalse(userDAO.getSortedDoctors(StringConstants.SQL_SORT_BY_POSITION).isEmpty());
    }

    @Test
    public void getUserByLoginTest() {
        assertNotNull(userDAO.getUserByLogin("doctor"));
    }

    @Test
    public void getDoctorByIdTest() {
        assertNotNull(userDAO.getDoctorById(1));
    }

    @Test
    public void getDoctorByLoginIdTest() {
        assertNotNull(userDAO.getDoctorByLoginId(1));
    }

    @Test
    public void getHospitalCardTest() {
        assertNotNull(userDAO.getHospitalCard(1));
    }
}
