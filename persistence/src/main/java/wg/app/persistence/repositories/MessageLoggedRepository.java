package wg.app.persistence.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wg.app.model.message.MessageLogged;
import wg.app.model.user.Student;
import wg.app.model.user.Teacher.Teacher;

import java.util.List;

@Repository
public interface MessageLoggedRepository extends JpaRepository<MessageLogged, Long>
{
    List<MessageLogged> getAllByStudent(Student student);
    List<MessageLogged> getAllByTeacher(Teacher teacher);
}
