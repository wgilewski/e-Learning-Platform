package wg.app.model.message;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import wg.app.model.user.Student;
import wg.app.model.user.Teacher.Teacher;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data

public class Invitation
{
    @Id
    @GeneratedValue
    private long id;

    @OneToOne
    private Student student;

    @OneToOne
    private Teacher teacher;

    private boolean acceptanceStatus;

}
