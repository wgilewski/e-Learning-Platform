package wg.app.model.user;


import lombok.*;
import org.springframework.web.multipart.MultipartFile;
import wg.app.model.course.IndividualCourse;
import wg.app.model.lecture.Lecture;
import wg.app.model.message.MessageAdmin;
import wg.app.model.message.MessageLogged;
import wg.app.model.user.Teacher.Opinion;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;


@Entity
@NoArgsConstructor
@Data
@Table(name = "Students")
public class Student extends User
{

    @Builder
    public Student(Long id, String name, String surname, String username, String email, String password, LocalDate birthDate, String description, BigDecimal accountBalance, Boolean enabled, String filename, Role role, MultipartFile multipartFile, List<MessageAdmin> messageToAdmin, Set<Opinion> opinions, Set<IndividualCourse> courses, List<MessageLogged> messages, List<Lecture> lectures) {
        super(id, name, surname, username, email, password, birthDate, description, accountBalance, enabled, filename, role, multipartFile, messageToAdmin);
        this.opinions = opinions;
        this.courses = courses;
        this.messages = messages;
        this.lectures = lectures;
    }



    @OneToMany(mappedBy = "student")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Opinion> opinions;

    @OneToMany(mappedBy = "student")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<IndividualCourse> courses;

    @OneToMany(mappedBy = "student")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<MessageLogged> messages;

    @ManyToMany(mappedBy = "students")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Lecture> lectures;



}
