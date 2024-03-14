package ro.ps.usermgmtbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.ps.usermgmtbackend.model.StudentEntity;

import java.util.List;
import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, UUID> {

    List<StudentEntity> findAll();

    List<StudentEntity> findAllByNameContaining(String name);
}
