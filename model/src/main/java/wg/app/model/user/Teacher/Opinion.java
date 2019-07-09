package wg.app.model.user.Teacher;




import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import wg.app.model.user.Student;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data

public class Opinion
{
    @Id
    @GeneratedValue
    private long id;
    private String opinion;
    private int rate;
    private LocalDate date;

    private boolean approvedByAdmin;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Teacher teacher;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Student student;



}
