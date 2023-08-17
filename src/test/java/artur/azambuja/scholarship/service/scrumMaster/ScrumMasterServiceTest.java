package artur.azambuja.scholarship.service.scrumMaster;

import artur.azambuja.scholarship.dto.scrumMaster.ScrumMasterRequestDTO;
import artur.azambuja.scholarship.dto.scrumMaster.ScrumMasterResponseDTO;
import artur.azambuja.scholarship.exceptions.EmailAlreadyRegistredException;
import artur.azambuja.scholarship.exceptions.scrumMaster.ScrumMasterNotFoundException;
import artur.azambuja.scholarship.model.Classroom;
import artur.azambuja.scholarship.model.ScrumMaster;
import artur.azambuja.scholarship.repository.classroom.ClassroomRepository;
import artur.azambuja.scholarship.repository.scrumMaster.ScrumMasterRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ScrumMasterServiceTest {
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private ScrumMasterService scrumMasterService;
    @Mock
    private ScrumMasterRepository scrumMasterRepository;
    @Mock
    private ClassroomRepository classroomRepository;

    @Test
    void convertScrumMasterToResponseDTO() {
        ScrumMaster scrumMaster = new ScrumMaster();
        ScrumMasterResponseDTO expectedResponseDTO = new ScrumMasterResponseDTO();
        when(modelMapper.map(scrumMaster, ScrumMasterResponseDTO.class)).thenReturn(expectedResponseDTO);

        ScrumMasterResponseDTO actualResponseDTO = scrumMasterService.convertScrumMasterToResponseDTO(scrumMaster);

        verify(modelMapper).map(scrumMaster, ScrumMasterResponseDTO.class);

        assertEquals(expectedResponseDTO, actualResponseDTO);
    }

    @Test
    void convertScrumMasterRequestDTOToEntity() {

        ScrumMasterRequestDTO requestDTO = new ScrumMasterRequestDTO();
        ScrumMaster expectedEntity = new ScrumMaster();
        when(modelMapper.map(requestDTO, ScrumMaster.class)).thenReturn(expectedEntity);

        ScrumMaster actualEntity = scrumMasterService.convertScrumMasterRequestDTOToEntity(requestDTO);

        verify(modelMapper).map(requestDTO, ScrumMaster.class);

        assertEquals(expectedEntity, actualEntity);
    }

    @Test
    void createScrumMaster() throws EmailAlreadyRegistredException {

        ScrumMasterRequestDTO requestDTO = new ScrumMasterRequestDTO();
        requestDTO.setFirstName("John");
        requestDTO.setLastName("Doe");
        requestDTO.setEmail("john.doe@example.com");

        ScrumMaster expectedScrumMaster = new ScrumMaster();
        expectedScrumMaster.setFirstName("John");
        expectedScrumMaster.setLastName("Doe");
        expectedScrumMaster.setEmail("john.doe@example.com");

        ScrumMasterResponseDTO expectedResponseDTO = new ScrumMasterResponseDTO();
        expectedResponseDTO.setFirstName("John");
        expectedResponseDTO.setLastName("Doe");
        expectedResponseDTO.setEmail("john.doe@example.com");

        when(scrumMasterRepository.existsByEmail("john.doe@example.com")).thenReturn(false);
        when(modelMapper.map(requestDTO, ScrumMaster.class)).thenReturn(expectedScrumMaster);
        when(scrumMasterRepository.save(expectedScrumMaster)).thenReturn(expectedScrumMaster);
        when(modelMapper.map(expectedScrumMaster, ScrumMasterResponseDTO.class)).thenReturn(expectedResponseDTO);

        ScrumMasterResponseDTO actualResponseDTO = scrumMasterService.createScrumMaster(requestDTO);
    }

    @Test
    void getAllScrumMasters() {
        List<ScrumMaster> mockScrumMasters = new ArrayList<>();

        when(scrumMasterRepository.findAll()).thenReturn(mockScrumMasters);

        when(modelMapper.map(any(ScrumMaster.class), eq(ScrumMasterResponseDTO.class)))
                .thenReturn(new ScrumMasterResponseDTO());

        List<ScrumMasterResponseDTO> responseDTOs = scrumMasterService.getAllScrumMasters();

        verify(scrumMasterRepository).findAll();

        verify(modelMapper, times(mockScrumMasters.size()))
                .map(any(ScrumMaster.class), eq(ScrumMasterResponseDTO.class));

        assertNotNull(responseDTOs);
        assertEquals(mockScrumMasters.size(), responseDTOs.size());
    }

    @Test
    void getScrumMasterById() throws ScrumMasterNotFoundException {

        ScrumMaster mockScrumMaster = new ScrumMaster();
        mockScrumMaster.setIdScrumMaster(1L);

        when(scrumMasterRepository.findById(1L)).thenReturn(Optional.of(mockScrumMaster));

        when(modelMapper.map(mockScrumMaster, ScrumMasterResponseDTO.class))
                .thenReturn(new ScrumMasterResponseDTO());

        ScrumMasterResponseDTO responseDTO = scrumMasterService.getScrumMasterById(1L);

        verify(scrumMasterRepository).findById(1L);

        verify(modelMapper).map(mockScrumMaster, ScrumMasterResponseDTO.class);

        assertNotNull(responseDTO);
    }

    @Test
    void testGetScrumMasterByIdNotFound() {

        when(scrumMasterRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ScrumMasterNotFoundException.class, () -> scrumMasterService.getScrumMasterById(1L));

        verify(scrumMasterRepository).findById(1L);
    }

    @Test
    void updateScrumMaster() throws ScrumMasterNotFoundException {

        ScrumMaster mockScrumMaster = new ScrumMaster();
        mockScrumMaster.setIdScrumMaster(1L);

        ScrumMasterRequestDTO requestDTO = new ScrumMasterRequestDTO();
        requestDTO.setFirstName("John");
        requestDTO.setLastName("Doe");
        requestDTO.setEmail("john.doe@example.com");

        when(scrumMasterRepository.findById(1L)).thenReturn(Optional.of(mockScrumMaster));

        when(scrumMasterRepository.save(any(ScrumMaster.class))).thenReturn(mockScrumMaster);

        when(modelMapper.map(mockScrumMaster, ScrumMasterResponseDTO.class))
                .thenReturn(new ScrumMasterResponseDTO());

        ScrumMasterResponseDTO responseDTO = scrumMasterService.updateScrumMaster(1L, requestDTO);

        verify(scrumMasterRepository).findById(1L);
        verify(scrumMasterRepository).save(mockScrumMaster);

        verify(modelMapper).map(mockScrumMaster, ScrumMasterResponseDTO.class);

        assertNotNull(responseDTO);
    }

    @Test
    void testUpdateScrumMasterNotFound() {

        ScrumMasterRequestDTO requestDTO = new ScrumMasterRequestDTO();
        requestDTO.setFirstName("John");
        requestDTO.setLastName("Doe");
        requestDTO.setEmail("john.doe@example.com");

        when(scrumMasterRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ScrumMasterNotFoundException.class, () -> scrumMasterService.updateScrumMaster(1L, requestDTO));

        verify(scrumMasterRepository).findById(1L);
        verify(scrumMasterRepository, never()).save(any(ScrumMaster.class));
    }

    @Test
    void deleteScrumMaster() throws ScrumMasterNotFoundException {

        ScrumMaster mockScrumMaster = new ScrumMaster();
        mockScrumMaster.setIdScrumMaster(1L);

        when(scrumMasterRepository.findById(1L)).thenReturn(Optional.of(mockScrumMaster));

        when(classroomRepository.findByScrumMasters(mockScrumMaster)).thenReturn(Collections.emptyList());

        assertDoesNotThrow(() -> scrumMasterService.deleteScrumMaster(1L));

        verify(scrumMasterRepository).findById(1L);
        verify(scrumMasterRepository).delete(mockScrumMaster);
    }

    @Test
    void testDeleteScrumMasterWithClassrooms() {
    }

    @Test
    void testDeleteScrumMasterNotFound() {

        when(scrumMasterRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ScrumMasterNotFoundException.class, () -> scrumMasterService.deleteScrumMaster(1L));

        verify(scrumMasterRepository).findById(1L);
        verify(scrumMasterRepository, never()).delete(any(ScrumMaster.class));
    }
}