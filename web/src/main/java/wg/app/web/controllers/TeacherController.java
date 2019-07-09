package wg.app.web.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wg.app.model.course.IndividualCourse;
import wg.app.model.course.Lesson;
import wg.app.model.dto.TeacherDto;
import wg.app.model.message.Invitation;
import wg.app.model.message.MessageAdmin;
import wg.app.model.message.MessageLogged;
import wg.app.web.service.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeacherController
{
    private final TeacherService teacherService;
    private final InvitationService invitationService;
    private final CourseService courseService;
    private final MessageService messageService;
    private final UserRegistrationService userRegistrationService;


    @GetMapping("/teacher")
    public String student()
    {
        return "TEACHER";
    }

    @PostMapping
    public ResponseEntity<TeacherDto> addTeacher(RequestEntity<TeacherDto> request)
    {
        return ResponseEntity.ok(userRegistrationService.registerTeacher(request.getBody()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDto> getTeacher(@PathVariable Long id)
    {
        return teacherService.getOne(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<TeacherDto>> getAllTeachers()
    {
        List<TeacherDto> teachers = teacherService.getAllTeachers();
        if (teachers == null)
        {
            return ResponseEntity.notFound().build();
        }
        else return ResponseEntity.ok(teachers);
    }


    @DeleteMapping("/{id}")
    public void deleteTeacherById(@PathVariable Long id)
    {
        teacherService.removeTeacher(id);
    }


    @PutMapping
    public ResponseEntity<TeacherDto> modifyTeacher(@RequestBody TeacherDto teacherDto) {

        return teacherService
                .update(teacherDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/invitations")
    public ResponseEntity<List<Invitation>> getAllInvitations()
    {
        List<Invitation> invitations = invitationService.getAllInvitations();

        if (invitations == null)
        {
            return ResponseEntity.notFound().build();
        }
        else return ResponseEntity.ok(invitations);
    }

    @PostMapping("/messageToAdmin")
    public ResponseEntity<MessageAdmin> sendMessageToAdmin(ResponseEntity<MessageAdmin> request)
    {
        return messageService
                .sendMessageToAdmin(request.getBody())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/messageToStudent/{studentId}")
    public ResponseEntity<MessageLogged> sendMessageToTeacher(RequestEntity<MessageLogged> request
            , @PathVariable Long studentId)
    {
        return messageService
                .sendMessageToStudent(request.getBody(),studentId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/messages")
    public ResponseEntity<List<MessageLogged>> getAllMessages()
    {
        List<MessageLogged> messages = messageService.getAllTeacherMessages();
        if (messages == null)
        {
            return ResponseEntity.notFound().build();
        }
        else return ResponseEntity.ok(messages);
    }


    @PostMapping("/newCourse/{studentId}")
    public ResponseEntity<IndividualCourse> addCourse(RequestEntity<IndividualCourse> request, @PathVariable Long studentId)
    {
        return courseService.addOrUpdateCourse(request.getBody(),studentId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping("newLesson/{courseId}")
    public ResponseEntity<Lesson> addOrUpdateLesson(RequestEntity<Lesson> request, @PathVariable Long courseId)
    {
        return courseService.addOrUpdateLesson(request.getBody(), courseId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
