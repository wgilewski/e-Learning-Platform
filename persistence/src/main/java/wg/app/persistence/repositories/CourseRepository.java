package wg.app.persistence.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wg.app.model.course.IndividualCourse;

@Repository
public interface CourseRepository extends JpaRepository<IndividualCourse,Long>
{

}
