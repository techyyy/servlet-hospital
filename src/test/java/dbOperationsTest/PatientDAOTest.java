package dbOperationsTest;

import com.hospital.Hospital.db.ConnectionPool;
import com.hospital.Hospital.db.impl.PatientDAO;
import com.hospital.Hospital.model.Patient;
import com.hospital.Hospital.model.PatientAssignment;
import com.hospital.Hospital.web.constants.StringConstants;
import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;

import static junit.framework.TestCase.*;

public class PatientDAOTest {

    private static final PatientDAO patientDAO = new PatientDAO();

    @BeforeClass
    public static void beforeTest() throws ClassNotFoundException {
        MysqlConnectionPoolDataSource dataSource = new MysqlConnectionPoolDataSource();
        dataSource.setURL("jdbc:mysql://127.0.0.1:3306/hospital?user=root&password=admin");
        ConnectionPool.setDataSource(dataSource);
    }

    @Test
    public void getPatientsTest() {
        assertFalse(patientDAO.findAllPatients().isEmpty());
    }

    @Test
    public void getPatientsForDoctorTest() {
        assertFalse(patientDAO.findPatientsForDoctor(1).isEmpty());
    }

    @Test
    public void getSortedPatientsForDoctorTest() {
        assertFalse(patientDAO.findSortedPatientsForDoctor(1, "birthDate").isEmpty());
    }

    @Test
    public void updatePatientTest() {
        Patient patient = new Patient.PatientBuilder("test", "test")
                .id(7)
                .birthDate(LocalDate.parse("1999-09-09"))
                .diagnosis("test")
                .build();
        assertTrue(patientDAO.updatePatient(patient));
    }

    @Test
    public void dischargePatientTest() {
        assertTrue(patientDAO.dischargePatient(7));
    }

    @Test
    public void updateTreatmentTest() {
        PatientAssignment pa = new PatientAssignment.PatientAssignmentBuilder(1, 1)
                .treatment("test")
                .build();
        assertTrue(patientDAO.updateTreatment(pa));
    }

    @Test
    public void getPatientByIdTest() {
        assertNotNull(patientDAO.getPatientById(7));
    }

    @Test
    public void getSortedPatientsTest() {
        assertFalse(patientDAO.getSortedPatients(StringConstants.SQL_SORT_BY_ALPHABET).isEmpty());
    }
}
