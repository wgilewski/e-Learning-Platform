package wg.app.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import wg.app.model.advertisement.Advertisement;
import wg.app.model.course.IndividualCourse;
import wg.app.model.course.Subject;
import wg.app.model.user.Role;
import wg.app.model.user.Teacher.Dashboard;
import wg.app.model.user.Teacher.Opinion;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDto extends UserDto {


    @Builder
    public TeacherDto(long id, String name, String surname, String username, String email, String password, String confirmPassword, LocalDate birthDate, String description, Boolean enabled, Role role, MultipartFile multipartFile, BigDecimal accountBalance, String filename, Dashboard dashboard, Advertisement advertisement, Set<Opinion> opinions, Set<IndividualCourse> courses, Set<Subject> subjects) {
        super(id, name, surname, username, email, password, confirmPassword, birthDate, description, enabled, role, multipartFile, accountBalance, filename);
        this.dashboard = dashboard;
        this.advertisement = advertisement;
        this.opinions = opinions;
        this.courses = courses;
        this.subjects = subjects;
    }


    private Dashboard dashboard;

    private Advertisement advertisement;

    private Set<Opinion> opinions;

    private Set<IndividualCourse> courses;

    private Set<Subject> subjects;


}
