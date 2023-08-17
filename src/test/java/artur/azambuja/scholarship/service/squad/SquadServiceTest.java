package artur.azambuja.scholarship.service.squad;

import artur.azambuja.scholarship.dto.squad.SquadRequestDTO;
import artur.azambuja.scholarship.model.Classroom;
import artur.azambuja.scholarship.model.Squad;
import artur.azambuja.scholarship.model.Student;
import artur.azambuja.scholarship.repository.squad.SquadRepository;
import artur.azambuja.scholarship.repository.student.StudentRepository;
import artur.azambuja.scholarship.service.scrumMaster.ScrumMasterService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class SquadServiceTest {
    @InjectMocks
    private ScrumMasterService scrumMasterService;
    @Mock
    private SquadService squadService;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private SquadRepository squadRepository;
    @Mock
    private StudentRepository studentRepository;


    @Test
    void convertSquadToResponseDTO() {
    }

    @Test
    void convertSquadRequestDTOToEntity() {
    }

    @Test
    void createAndDistributeSquads() {
    }

    @Test
    void getAllSquads() {
    }

    @Test
    void getSquadById() {
    }

    @Test
    void updateSquad() {
    }

    @Test
    void deleteSquad() {
    }
}