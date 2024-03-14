package ro.ps.usermgmtbackend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ro.ps.usermgmtbackend.dto.StudentRequestDTO;
import ro.ps.usermgmtbackend.dto.StudentResponseDTO;
import ro.ps.usermgmtbackend.model.StudentEntity;

import java.util.List;

@Mapper
public interface StudentMapper {

    @Mapping(target = "cnp2", source = "cnp")
    StudentResponseDTO studentEntityToStudentResponseDTO(StudentEntity studentEntity);

    List<StudentResponseDTO> studentEntityListToStudentResponseDTOList(List<StudentEntity> studentEntityList);

    StudentEntity studentRequestDTOToStudentEntity(StudentRequestDTO studentRequestDTO);
}
