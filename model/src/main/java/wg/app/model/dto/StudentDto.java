package wg.app.model.dto;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import wg.app.model.course.Certificate;
import wg.app.model.user.Role;
import wg.app.model.user.Teacher.Opinion;

import org.springframework.web.multipart.MultipartFile;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;


@NoArgsConstructor
@Data
public class StudentDto extends UserDto
{


    @Builder
    public StudentDto(long id, String name, String surname, String username, String email, String password, String confirmPassword, LocalDate birthDate, String description, Boolean enabled, Role role, MultipartFile multipartFile, BigDecimal accountBalance, String filename, Set<Opinion> opinions, Set<Certificate> certificates) {
        super(id, name, surname, username, email, password, confirmPassword, birthDate, description, enabled, role, multipartFile, accountBalance, filename);
        this.opinions = opinions;
        this.certificates = certificates;
    }


    private Set<Opinion> opinions;

    private Set<Certificate> certificates;






}

