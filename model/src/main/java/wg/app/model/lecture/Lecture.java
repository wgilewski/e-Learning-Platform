package wg.app.model.lecture;


import lombok.*;
import wg.app.model.user.Student;
import wg.app.model.user.Teacher.Teacher;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Lecture
{
    @Id
    @GeneratedValue
    private Long id;


    private LocalDateTime localDateTime;
    private boolean status;
    private boolean presenceStatus;
    private BigDecimal price;
    private String topic;
    private String description;


    @ManyToOne(cascade = CascadeType.PERSIST)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Teacher teacher;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Student> students;


}
