package wg.app.model.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Admin
{
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String email;
    private String password;
    private BigDecimal accountBalance;
    private Role role;

}
