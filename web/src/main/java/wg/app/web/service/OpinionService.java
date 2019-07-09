package wg.app.web.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wg.app.model.exceptions.MyException;
import wg.app.model.user.Student;
import wg.app.model.user.Teacher.Opinion;
import wg.app.model.user.Teacher.Teacher;
import wg.app.persistence.repositories.OpinionRepository;
import wg.app.persistence.repositories.StudentRepository;
import wg.app.persistence.repositories.TeacherRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class OpinionService
{
    private final OpinionRepository opinionRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;


    public Optional<Opinion> addOrUpdateOpinion(Opinion opinion, Long teacherId)
    {
        //student na rzie na sztywno, potem od zalogowanego

        Optional<Student> student = studentRepository.findById(4l);
        Optional<Teacher> teacher = teacherRepository.findById(teacherId);

        opinion.setApprovedByAdmin(false);
        opinion.setDate(LocalDate.now());
        if (student.isPresent() && teacher.isPresent())
        {
            opinion.setStudent(student.get());
            opinion.setTeacher(teacher.get());
            return Optional.of(opinionRepository.save(opinion));
        }
        else throw new MyException("THERE IS NO STUDENT OR TEACHER WITCH SUCH ID IN DATABASE" + "S id" + 4l + "T id" + teacherId);

    }


    public void deleteOpinion(Long id)
    {
        opinionRepository.deleteById(id);
    }


    public Optional<List<Opinion>> getAllOpinionsByTeacherId(Long id)
    {
        return Optional.of(
                opinionRepository.findAll()
                        .stream()
                        .filter(opinion -> opinion.getTeacher().getId() == id && opinion.isApprovedByAdmin() == true)
                        .collect(Collectors.toList())
        );
    }
}
