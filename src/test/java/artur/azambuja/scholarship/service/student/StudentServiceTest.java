package artur.azambuja.scholarship.service.student;

import artur.azambuja.scholarship.dto.scrumMaster.ScrumMasterResponseDTO;
import artur.azambuja.scholarship.dto.student.StudentResponseDTO;
import artur.azambuja.scholarship.model.Student;
import artur.azambuja.scholarship.service.scrumMaster.ScrumMasterService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class StudentServiceTest {

    @InjectMocks
    private ScrumMasterService scrumMasterService;

    @Test
    void convertStudentToResponseDTO() {
    }

    @Test
    void convertStudentRequestDTOToEntity() {
    }

    @Test
    void createStudent() {
    }

    @Test
    void getStudentsByClassroomId() {
    }

    @Test
    void getAllStudents() {
    }

    @Test
    void getStudentById() {
    }

    @Test
    void updateStudent() {
    }

    @Test
    void deleteStudent() {
    }

    @Test
    void findAvailableStudentsForClassroom() {
    }
}