package artur.azambuja.scholarship.service.squad;

import artur.azambuja.scholarship.dto.squad.SquadRequestDTO;
import artur.azambuja.scholarship.dto.squad.SquadResponseDTO;
import artur.azambuja.scholarship.exceptions.squad.SquadNotFoundException;
import artur.azambuja.scholarship.model.Classroom;
import artur.azambuja.scholarship.model.Squad;
import artur.azambuja.scholarship.model.Student;
import artur.azambuja.scholarship.repository.squad.SquadRepository;
import artur.azambuja.scholarship.repository.student.StudentRepository;
import artur.azambuja.scholarship.service.serviceClass;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SquadService extends serviceClass {
    public SquadService(ModelMapper modelMapper, SquadRepository squadRepository, StudentRepository studentRepository){
        super(modelMapper);
        this.squadRepository = squadRepository;
        this.studentRepository = studentRepository;
    }
    public SquadResponseDTO convertSquadToResponseDTO(Squad squad) {
        return modelMapper.map(squad, SquadResponseDTO.class);
    }
    public Squad convertSquadRequestDTOToEntity(SquadRequestDTO dto) {
        return modelMapper.map(dto, Squad.class);
    }
    private final SquadRepository squadRepository;
    private final StudentRepository studentRepository;
    public void createAndDistributeSquads(Classroom classroom, List<Student> students) {
        int maxStudentsPerSquad = 5;
        int numSquads = (int) Math.ceil((double) students.size() / maxStudentsPerSquad);

        for (int i = 1; i <= numSquads; i++) {
            Squad squad = new Squad();
            squad.setName("Squad #" + i);
            squad.setClassroom(classroom);
            squad.setNumber(i);

            squadRepository.save(squad);
        }

        int currentSquad = 1;
        for (Student student : students) {
            Squad squad = squadRepository.findByNumberAndClassroom(currentSquad, classroom);
            student.setSquad(squad);
            studentRepository.save(student);

            currentSquad = (currentSquad % numSquads) + 1;
        }
    }
    public List<SquadResponseDTO> getAllSquads() {
        List<Squad> squads = squadRepository.findAll();
        return squads.stream()
                .map(this::convertSquadToResponseDTO)
                .collect(Collectors.toList());
    }

    public SquadResponseDTO getSquadById(Long squadId) throws SquadNotFoundException {
        Squad squad = squadRepository.findById(squadId)
                .orElseThrow(() -> new SquadNotFoundException("Squad not found with this id"));
        return convertSquadToResponseDTO(squad);
    }
    public SquadResponseDTO updateSquad(Long squadId, SquadRequestDTO requestDTO) throws SquadNotFoundException {
        Squad squad = squadRepository.findById(squadId)
                .orElseThrow(() -> new SquadNotFoundException("Squad not found with this id"));

        squad.setName(requestDTO.getName());
        squad.setNumber(requestDTO.getNumber());
        squadRepository.save(squad);

        return convertSquadToResponseDTO(squad);
    }
    public void deleteSquad(Long squadId) throws SquadNotFoundException {
        Squad squad = squadRepository.findById(squadId)
                .orElseThrow(() -> new SquadNotFoundException("Squad not found with this id"));

        List<Student> studentsInSquad = studentRepository.findBySquad(squad);
        for (Student student : studentsInSquad) {
            student.setSquad(null);
            studentRepository.save(student);
        }

        squadRepository.delete(squad);
    }


}
