package wg.app.persistence.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wg.app.model.course.Lesson;

@Repository
public interface LessonRepository extends JpaRepository<Lesson,Long> {
}
