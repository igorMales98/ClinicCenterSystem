package ClinicCenterSystem.repository;

import com.clinicCenter.App;
import com.clinicCenter.model.Doctor;
import com.clinicCenter.model.User;
import com.clinicCenter.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collection;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@TestPropertySource(locations = {"/test.properties"})
@ContextConfiguration(classes = App.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testDoctorsThatCanDoExam(){

        Collection<User> doctors = userRepository.getDoctorsThatCanDoExam(1L, 1L, "2020-02-14 00:00:00.000000");
        assertEquals(1, doctors.size());
    }

    @Test
    public void testDoctorsThatCanDoExam1(){
        Collection<User> doctors = userRepository.getDoctorsThatCanDoExam(1L, 4L, "2020-02-14 00:00:00.000000");
        assertEquals(3, doctors.size());
    }

    @Test
    public void testDoctors(){
        ArrayList<User> users = userRepository.getDoctors();
        assertEquals(7, users.size());
    }


}


