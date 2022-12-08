package miage.IF.repository;

import miage.IF.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentEntityRepository extends JpaRepository<Student,Integer> {
     List<Student> findAll();
     Optional<Student> findById(Integer id);

    Optional<Student> findByEmail(String email);

}
