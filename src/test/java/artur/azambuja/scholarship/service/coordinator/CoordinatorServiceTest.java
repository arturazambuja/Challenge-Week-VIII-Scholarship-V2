package artur.azambuja.scholarship.service.coordinator;

import artur.azambuja.scholarship.dto.coordinator.CoordinatorRequestDTO;
import artur.azambuja.scholarship.dto.coordinator.CoordinatorResponseDTO;
import artur.azambuja.scholarship.exceptions.EmailAlreadyRegistredException;
import artur.azambuja.scholarship.exceptions.coordinator.CoordinatorNotFoundException;
import artur.azambuja.scholarship.model.Classroom;
import artur.azambuja.scholarship.model.Coordinator;
import artur.azambuja.scholarship.repository.classroom.ClassroomRepository;
import artur.azambuja.scholarship.repository.coordinator.CoordinatorRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CoordinatorServiceTest {

    @Spy
    private ModelMapper modelMapper;
    @InjectMocks
    private CoordinatorService coordinatorService;
    @Mock
    private CoordinatorRepository coordinatorRepository;
    @Mock
    private ClassroomRepository classroomRepository;
    @Test
    void convertCoordinatorToResponseDTO() {
        Coordinator coordinator = new Coordinator();
        coordinator.setIdCoordinator(1L);
        coordinator.setFirstName("John Doe");

        ModelMapper modelMapper = new ModelMapper();

        CoordinatorResponseDTO responseDTO = coordinatorService.convertCoordinatorToResponseDTO(coordinator);

        assertEquals(coordinator.getIdCoordinator(), responseDTO.getIdCoordinator());
        assertEquals(coordinator.getFirstName(), responseDTO.getFirstName());
    }

    @Test
    void convertCoordinatorRequestDTOToEntity() {
        CoordinatorRequestDTO requestDTO = new CoordinatorRequestDTO();
        requestDTO.setFirstName("John");
        requestDTO.setEmail("john@example.com");

        Coordinator coordinator = coordinatorService.convertCoordinatorRequestDTOToEntity(requestDTO);

        assertEquals(requestDTO.getFirstName(), coordinator.getFirstName());
        assertEquals(requestDTO.getEmail(), coordinator.getEmail());
    }

    @Test
    void createCoordinator() throws EmailAlreadyRegistredException {
        CoordinatorRequestDTO requestDTO = new CoordinatorRequestDTO();
        requestDTO.setFirstName("John");
        requestDTO.setLastName("Doe");
        requestDTO.setEmail("john@example.com");

        when(coordinatorRepository.existsByEmail(requestDTO.getEmail())).thenReturn(false);
        when(coordinatorRepository.save(any(Coordinator.class))).thenAnswer(invocation -> {
            Coordinator savedCoordinator = invocation.getArgument(0);
            savedCoordinator.setIdCoordinator(1L);
            return savedCoordinator;
        });

        CoordinatorResponseDTO responseDTO = coordinatorService.createCoordinator(requestDTO);

        assertEquals(requestDTO.getFirstName(), responseDTO.getFirstName());
        assertEquals(requestDTO.getLastName(), responseDTO.getLastName());
        assertEquals(requestDTO.getEmail(), responseDTO.getEmail());
    }

    @Test
    void getAllCoordinators() {

        Coordinator coordinator1 = new Coordinator();
        coordinator1.setIdCoordinator(1L);
        coordinator1.setFirstName("John");
        coordinator1.setLastName("Doe");
        coordinator1.setEmail("john@example.com");

        Coordinator coordinator2 = new Coordinator();
        coordinator2.setIdCoordinator(2L);
        coordinator2.setFirstName("Jane");
        coordinator2.setLastName("Smith");
        coordinator2.setEmail("jane@example.com");

        List<Coordinator> mockCoordinators = new ArrayList<>();
        mockCoordinators.add(coordinator1);
        mockCoordinators.add(coordinator2);
        when(coordinatorRepository.findAll()).thenReturn(mockCoordinators);

        List<CoordinatorResponseDTO> responseDTOs = coordinatorService.getAllCoordinators();

        assertEquals(2, responseDTOs.size());

        CoordinatorResponseDTO responseDTO1 = responseDTOs.get(0);
        assertEquals(coordinator1.getIdCoordinator(), responseDTO1.getIdCoordinator());
        assertEquals(coordinator1.getFirstName(), responseDTO1.getFirstName());
        assertEquals(coordinator1.getLastName(), responseDTO1.getLastName());
        assertEquals(coordinator1.getEmail(), responseDTO1.getEmail());
    }

    @Test
    void getCoordinatorById() throws CoordinatorNotFoundException {

        Coordinator coordinator = new Coordinator();
        coordinator.setIdCoordinator(1L);
        coordinator.setFirstName("John");
        coordinator.setLastName("Doe");
        coordinator.setEmail("john@example.com");

        when(coordinatorRepository.findById(1L)).thenReturn(Optional.of(coordinator));

        CoordinatorResponseDTO responseDTO = coordinatorService.getCoordinatorById(1L);

        assertEquals(coordinator.getIdCoordinator(), responseDTO.getIdCoordinator());
        assertEquals(coordinator.getFirstName(), responseDTO.getFirstName());
        assertEquals(coordinator.getLastName(), responseDTO.getLastName());
        assertEquals(coordinator.getEmail(), responseDTO.getEmail());
    }

    @Test
    public void testGetCoordinatorByIdNotFound() {

        when(coordinatorRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(CoordinatorNotFoundException.class, () -> coordinatorService.getCoordinatorById(2L));
    }

    @Test
    void updateCoordinator() throws CoordinatorNotFoundException {

        Coordinator existingCoordinator = new Coordinator();
        existingCoordinator.setIdCoordinator(1L);
        existingCoordinator.setFirstName("John");
        existingCoordinator.setLastName("Doe");
        existingCoordinator.setEmail("john@example.com");

        when(coordinatorRepository.findById(1L)).thenReturn(Optional.of(existingCoordinator));
        when(coordinatorRepository.save(any(Coordinator.class))).thenReturn(existingCoordinator);

        CoordinatorRequestDTO requestDTO = new CoordinatorRequestDTO();
        requestDTO.setFirstName("Jane");
        requestDTO.setLastName("Smith");
        requestDTO.setEmail("jane@example.com");

        CoordinatorResponseDTO responseDTO = coordinatorService.updateCoordinator(1L, requestDTO);

        assertEquals(existingCoordinator.getIdCoordinator(), responseDTO.getIdCoordinator());
        assertEquals(requestDTO.getFirstName(), responseDTO.getFirstName());
        assertEquals(requestDTO.getLastName(), responseDTO.getLastName());
        assertEquals(requestDTO.getEmail(), responseDTO.getEmail());

        verify(coordinatorRepository, times(1)).save(existingCoordinator);
    }

    @Test
    public void testUpdateCoordinatorNotFound() {

        when(coordinatorRepository.findById(2L)).thenReturn(Optional.empty());

        CoordinatorRequestDTO requestDTO = new CoordinatorRequestDTO();
        requestDTO.setFirstName("Jane");
        requestDTO.setLastName("Smith");
        requestDTO.setEmail("jane@example.com");

        assertThrows(CoordinatorNotFoundException.class, () -> coordinatorService.updateCoordinator(2L, requestDTO));

        verify(coordinatorRepository, never()).save(any());
    }

    @Test
    void deleteCoordinator(){}

    @Test
    public void testDeleteCoordinatorNotFound() {

        when(coordinatorRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(CoordinatorNotFoundException.class, () -> coordinatorService.deleteCoordinator(2L));

        verify(coordinatorRepository, never()).delete(any());
    }
}