package wg.app.persistence.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wg.app.model.user.Teacher.Teacher;

import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Long>
{
    Optional<Teacher> getDistinctByAdvertisement_Id(Long id);
    Optional<Teacher> findByUsername(String username);
    Optional<Teacher> findByEmail(String email);
}
