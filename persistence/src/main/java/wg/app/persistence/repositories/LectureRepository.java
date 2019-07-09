package wg.app.persistence.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wg.app.model.lecture.Lecture;

@Repository
public interface LectureRepository extends JpaRepository<Lecture,Long>
{

}
