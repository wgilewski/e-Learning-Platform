package wg.app.model.user.Teacher;



import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;
import wg.app.model.advertisement.Advertisement;
import wg.app.model.course.Certificates;
import wg.app.model.course.IndividualCourse;
import wg.app.model.course.Subject;
import wg.app.model.lecture.Lecture;
import wg.app.model.message.MessageAdmin;
import wg.app.model.message.MessageLogged;
import wg.app.model.user.Role;
import wg.app.model.user.User;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Teachers")
public class Teacher extends User
{

    @Builder
    public Teacher(Long id, String name, String surname, String username, String email, String password, LocalDate birthDate, String description, BigDecimal accountBalance, Boolean enabled, String filename, Role role, MultipartFile multipartFile, List<MessageAdmin> messageToAdmin, Set<Certificates> certificates, Set<IndividualCourse> courses, Dashboard dashboard, Advertisement advertisement, Set<Opinion> opinions, Set<Subject> subjects, List<MessageLogged> messages, List<Lecture> lectures) {
        super(id, name, surname, username, email, password, birthDate, description, accountBalance, enabled, filename, role, multipartFile, messageToAdmin);
        this.certificates = certificates;
        this.courses = courses;
        this.dashboard = dashboard;
        this.advertisement = advertisement;
        this.opinions = opinions;
        this.subjects = subjects;
        this.messages = messages;
        this.lectures = lectures;
    }


    @ElementCollection
    @CollectionTable(
            name = "certificates",
            joinColumns = @JoinColumn(name = "teacher_id")
    )
    @Column(name = "certificates")
    @Enumerated(EnumType.STRING)
    private Set<Certificates> certificates;

    @JsonIgnore
    @OneToMany(mappedBy = "owner")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<IndividualCourse> courses;


    @JsonIgnore
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "dashboard_id", unique = true)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Dashboard dashboard;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "advertisement_id", unique = true)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Advertisement advertisement;

    @OneToMany(mappedBy = "teacher")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Opinion> opinions;


    @ElementCollection
    @CollectionTable(
            name = "subjects",
            joinColumns = @JoinColumn(name = "teacher_id")
    )
    @Column(name = "subjects")
    @Enumerated(EnumType.STRING)
    private Set<Subject> subjects;

    @JsonIgnore
    @OneToMany(mappedBy = "teacher")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<MessageLogged> messages;


    @JsonIgnore
    @OneToMany(mappedBy = "teacher")
    private List<Lecture> lectures;

}
