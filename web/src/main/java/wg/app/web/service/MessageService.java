package wg.app.web.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wg.app.model.exceptions.MyException;
import wg.app.model.message.MessageAdmin;
import wg.app.model.message.MessageLogged;
import wg.app.model.user.Student;
import wg.app.model.user.Teacher.Teacher;
import wg.app.model.user.User;
import wg.app.persistence.repositories.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MessageService
{
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final MessageLoggedRepository messageLoggedRepository;
    private final MessageAdminRepository messageAdminRepository;
    private final UserRepository userRepository;



    //----------------------------------TEACHER-------------------------------


    public Optional<MessageLogged> sendMessageToStudent(MessageLogged messageLogged, Long studentId)
    {
        messageLogged.setStudent(studentRepository.findById(studentId).orElseThrow(
                () -> new MyException("THERE IS NO STUDENT WITCH SUCH ID"))
        );


        //teacher na sztywno, potem id zalogowanego teachera;
        messageLogged.setTeacher(teacherRepository.findById(2L).get());

        if (messageLogged == null)
        {
            throw new MyException("MESSAGE TO TEACHER IS NULL");
        }
        else
        {
            return Optional.of(messageLoggedRepository.save(messageLogged));
        }
    }

    public List<MessageLogged> getAllTeacherMessages()
    {
        //id na sztywno, potem pobieramy od zalogowanego teacher

        Optional<Teacher> teacher = teacherRepository.findById(2L);

        if (teacher.isPresent())
        {
            return messageLoggedRepository.getAllByTeacher(teacher.get());
        }
        else throw new MyException("THERE IS NO TEACHER WITH SUCH ID");


    }


    //------------------------------------STUDENT----------------------------------------

    public Optional<MessageLogged> sendMessageToTeacher(MessageLogged messageLogged, Long teacherId)
    {
        messageLogged.setTeacher(teacherRepository.findById(teacherId).orElseThrow(
                () -> new MyException("THERE IS NO TEACHER WITH SUCH ID"))
        );

        //Student na sztywno, potem od zalogowanego pobrać.

        messageLogged.setStudent(studentRepository.findById(4l).get());


        if (messageLogged == null)
        {
            throw new MyException("MESSAGE TO STUDENT IS NULL");
        }
        else
        {
            return Optional.of(messageLoggedRepository.save(messageLogged));
        }
    }

    public List<MessageLogged> getAllStudentMessages()
    {
        //id na sztywno, potem pobieramy od zalogowanego studenta
        Optional<Student> student = studentRepository.findById(4L);
        if (student.isPresent())
        {
            return messageLoggedRepository.getAllByStudent(student.get());
        }
        else throw new MyException("THERE IS NO STUDENT WITH SUCH ID");
    }


    //-----------------------------------STUDENT TEACHER-------------------------------------

    public Optional<MessageAdmin> sendMessageToAdmin(MessageAdmin messageAdmin)
    {
        //na razie na sztywno user, potem z zalogowanego studenta
        Optional<User> user = userRepository.findById(3L);

        if (user.isPresent()) {
            messageAdmin.setUser(user.get());
            return Optional.of(messageAdminRepository.save(messageAdmin));
        }
        else throw new MyException("MESSAGE TO ADMIN IS NULL");
    }
}
