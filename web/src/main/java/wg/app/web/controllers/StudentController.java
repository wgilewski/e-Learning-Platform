package wg.app.web.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wg.app.model.course.Certificate;
import wg.app.model.dto.StudentDto;
import wg.app.model.message.Invitation;
import wg.app.model.message.MessageAdmin;
import wg.app.model.message.MessageLogged;
import wg.app.model.user.Teacher.Opinion;
import wg.app.web.service.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController
{
    private final StudentService studentService;
    private final InvitationService invitationService;
    private final MessageService messageService;
    private final CourseService courseService;
    private final OpinionService opinionService;
    private final UserRegistrationService userRegistrationService;

    @GetMapping("/student")

    public String teacher() {
        return "STUDENT";
    }


    @PostMapping
    public ResponseEntity<StudentDto> addStudent(RequestEntity<StudentDto> request)
    {
        return ResponseEntity.ok(userRegistrationService.registerStudent(request.getBody()));
    }


    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudent(@PathVariable Long id)
    {
        return studentService.getOne(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllStudents()
    {
        List<StudentDto> students = studentService.getAll();
        if (students == null)
        {
            return ResponseEntity.notFound().build();
        }
        else return ResponseEntity.ok(students);
    }

    @DeleteMapping("/{id}")
    public void deleteStudentById(@PathVariable Long id)
    {
        studentService.remove(id);
    }

    @PutMapping
    public ResponseEntity<StudentDto> modifyStudent(@RequestBody StudentDto studentDto) {
        return studentService
                .update(studentDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping("/sendInvitation/{teacherId}")
    public ResponseEntity<Invitation> sendInvitation(@PathVariable Long teacherId)
    {
        return invitationService.sendInvitation(teacherId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping("/messageToAdmin")
    public ResponseEntity<MessageAdmin> sendMessageToAdmin(RequestEntity<MessageAdmin> request)
    {
        return messageService
                .sendMessageToAdmin(request.getBody())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/messageToTeacher/{teacherId}")
    public ResponseEntity<MessageLogged> sendMessageToTeacher(RequestEntity<MessageLogged> request
            , @PathVariable Long teacherId)
    {
        return messageService
                .sendMessageToTeacher(request.getBody(),teacherId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/messages")
    public ResponseEntity<List<MessageLogged>> getAllMessages()
    {
        List<MessageLogged> messages = messageService.getAllStudentMessages();
        if (messages == null)
        {
            return ResponseEntity.notFound().build();
        }
        else return ResponseEntity.ok(messages);
    }

    @GetMapping("/certificates/{studentId}")
    public ResponseEntity<List<Certificate>> getAllCertificatesByStudentId(@PathVariable Long studentId)
    {
        List<Certificate> certificates = courseService.getAllCertificatesByStudentId(studentId);

        if (certificates == null)
        {
            return ResponseEntity.notFound().build();
        }
        else return ResponseEntity.ok(certificates);
    }

    @PostMapping("/addOpinion/{teacherId}")
    public ResponseEntity<Opinion> addOpinion(RequestEntity<Opinion> request, @PathVariable Long teacherId)
    {
        return opinionService.addOrUpdateOpinion(request.getBody(), teacherId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


}
