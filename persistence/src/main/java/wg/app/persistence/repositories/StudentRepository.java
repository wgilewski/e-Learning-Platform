package wg.app.persistence.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wg.app.model.user.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
}
