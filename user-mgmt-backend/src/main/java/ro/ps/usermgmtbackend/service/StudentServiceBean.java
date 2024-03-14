package ro.ps.usermgmtbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.ps.usermgmtbackend.dto.StudentRequestDTO;
import ro.ps.usermgmtbackend.dto.StudentResponseDTO;
import ro.ps.usermgmtbackend.mapper.StudentMapper;
import ro.ps.usermgmtbackend.model.StudentEntity;
import ro.ps.usermgmtbackend.repository.StudentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceBean implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapperImpl;

    @Override
    public List<StudentResponseDTO> getStudents() {
        List<StudentEntity> studentEntityList = studentRepository.findAll();

        return studentMapperImpl.studentEntityListToStudentResponseDTOList(studentEntityList);
    }

    @Override
    public List<StudentResponseDTO> getStudentsByName(String name) {
        List<StudentEntity> studentEntityList = studentRepository.findAllByNameContaining(name);

        return studentMapperImpl.studentEntityListToStudentResponseDTOList(studentEntityList);
    }

    @Override
    public StudentResponseDTO saveStudent(StudentRequestDTO studentRequestDTO) {
        StudentEntity studentToBeAdded = studentMapperImpl.studentRequestDTOToStudentEntity(studentRequestDTO);
        StudentEntity studentAdded = studentRepository.save(studentToBeAdded);

        return studentMapperImpl.studentEntityToStudentResponseDTO(studentAdded);
    }
}
