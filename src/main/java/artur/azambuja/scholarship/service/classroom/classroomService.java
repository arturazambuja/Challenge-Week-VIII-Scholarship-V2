package artur.azambuja.scholarship.service.classroom;

import artur.azambuja.scholarship.dto.classroom.classroomRequestDTO;
import artur.azambuja.scholarship.dto.classroom.classroomResponseDTO;
import artur.azambuja.scholarship.model.Classroom;
import artur.azambuja.scholarship.model.Coordinator;
import artur.azambuja.scholarship.model.Instructor;
import artur.azambuja.scholarship.model.ScrumMaster;
import artur.azambuja.scholarship.service.serviceClass;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.InsufficientResourcesException;
import java.util.List;

@Service
public class classroomService extends serviceClass {
    public classroomService(ModelMapper modelMapper){
        super(modelMapper);
    }
    public classroomResponseDTO convertClassroomToResponseDTO(Classroom classroom) {
        return modelMapper.map(classroom, classroomResponseDTO.class);
    }
    public Classroom convertClassroomRequestDTOToEntity(classroomRequestDTO dto) {
        return modelMapper.map(dto, Classroom.class);
    }
}
