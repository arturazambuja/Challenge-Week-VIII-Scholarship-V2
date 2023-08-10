package artur.azambuja.scholarship.service.classroom;

import artur.azambuja.scholarship.dto.classroom.classroomRequestDTO;
import artur.azambuja.scholarship.dto.classroom.classroomResponseDTO;
import artur.azambuja.scholarship.model.Classroom;
import artur.azambuja.scholarship.service.serviceClass;
import org.modelmapper.ModelMapper;

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
