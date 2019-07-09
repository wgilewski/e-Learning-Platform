package wg.app.model.user;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import wg.app.model.message.MessageAdmin;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User
{
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String surname;
    private String username;
    private String email;
    private String password;
    private LocalDate birthDate;
    private String description;
    private BigDecimal accountBalance;
    private Boolean enabled;
    private String filename;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Transient
    private MultipartFile multipartFile;

    @OneToMany(mappedBy = "user")
    private List<MessageAdmin> messageToAdmin;




}
