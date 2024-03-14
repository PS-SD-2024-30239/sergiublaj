package ro.ps.usermgmtbackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ps.usermgmtbackend.dto.StudentRequestDTO;
import ro.ps.usermgmtbackend.dto.StudentResponseDTO;
import ro.ps.usermgmtbackend.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/students/v1")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentServiceBean;

    @GetMapping("/all")
    public ResponseEntity<List<StudentResponseDTO>> getAll() {
        return new ResponseEntity<>(
                studentServiceBean.getStudents(),
                HttpStatus.OK
        );
    }

    @GetMapping("/all2/{name}")
    public ResponseEntity<List<StudentResponseDTO>> getAllByName1(@PathVariable("name") String name) {
        return new ResponseEntity<>(
                studentServiceBean.getStudentsByName(name),
                HttpStatus.OK
        );
    }

    @GetMapping("/all3")
    public ResponseEntity<List<StudentResponseDTO>> getAllByName2(@RequestParam("name") String name) {
        return new ResponseEntity<>(
                studentServiceBean.getStudentsByName(name),
                HttpStatus.OK
        );
    }

    @PostMapping("/all")
    public ResponseEntity<StudentResponseDTO> saveStudent(
            @RequestBody StudentRequestDTO studentRequestDTO
    ) {
        return new ResponseEntity<>(
                studentServiceBean.saveStudent(studentRequestDTO),
                HttpStatus.OK
        );
    }
}
