package wg.app.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import wg.app.model.user.Role;

import java.math.BigDecimal;
import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto
{
    private long id;
    private String name;
    private String surname;
    private String username;
    private String email;
    private String password;
    private String confirmPassword;
    private LocalDate birthDate;
    private String description;
    private Boolean enabled;
    private Role role;
    private MultipartFile multipartFile;
    private BigDecimal accountBalance;
    private String filename;



}
