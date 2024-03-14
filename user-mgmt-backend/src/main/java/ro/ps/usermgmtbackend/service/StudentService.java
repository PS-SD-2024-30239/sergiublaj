package ro.ps.usermgmtbackend.service;

import ro.ps.usermgmtbackend.dto.StudentRequestDTO;
import ro.ps.usermgmtbackend.dto.StudentResponseDTO;

import java.util.List;

public interface StudentService {

    List<StudentResponseDTO> getStudents();

    List<StudentResponseDTO> getStudentsByName(String name);

    StudentResponseDTO saveStudent(StudentRequestDTO studentRequestDTO);
}
