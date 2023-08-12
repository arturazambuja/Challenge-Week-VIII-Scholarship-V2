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
            squad.setNumber(i);
            squad.setClassroom(classroom);

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
    public List<Squad> getAllSquads() {
        return squadRepository.findAll();
    }

    public Squad getSquadById(Long squadId) throws SquadNotFoundException {
        return squadRepository.findById(squadId)
                .orElseThrow(() -> new SquadNotFoundException("Squad not found with this id"));
    }
    public Squad updateSquad(Long squadId, SquadRequestDTO requestDTO) throws SquadNotFoundException {
        Squad squad = squadRepository.findById(squadId)
                .orElseThrow(() -> new SquadNotFoundException("Squad not found with this id"));

        squad.setName(requestDTO.getName());
        squad.setNumber(requestDTO.getNumber());
        squadRepository.save(squad);

        return squad;
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
