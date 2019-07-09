package wg.app.web.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wg.app.model.message.Invitation;
import wg.app.persistence.repositories.InvitationRepository;
import wg.app.persistence.repositories.StudentRepository;
import wg.app.persistence.repositories.TeacherRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class InvitationService
{
    private final InvitationRepository invitationRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;


    //---------------------------------------TEACHER--------------------------------------------------------------

    public List<Invitation> getAllInvitations()
    {

      //teacher id ustwaiamy na poczatku na sztywno, dopiero potem jak bedzie web, ustawimy id zalogowanego teachera.

        System.out.println(invitationRepository.findAllByTeacherId(Long.valueOf(3)));

        return invitationRepository.findAll()
                .stream()
                .filter(invitation -> invitation.getTeacher().getId() == Long.valueOf(3))
                .collect(Collectors.toList()
        );
    }


    //-----------------------------------------STUDENT---------------------------------------------------------

    public Optional<Invitation> sendInvitation(Long teacherId)
    {

        //student id ustwaiamy na poczatku na sztywno, dopiero potem jak bedzie web, ustawimy id zalogowanego studenta.

        return Optional.of(
        invitationRepository.save(Invitation
                .builder()
                .acceptanceStatus(false)
                .teacher(teacherRepository.findById(teacherId).get())
                .student(studentRepository.findById(Long.valueOf(2)).get())
                .build()
        ));
    }




}
