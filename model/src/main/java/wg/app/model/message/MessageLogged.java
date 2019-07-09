package wg.app.model.message;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import wg.app.model.user.Student;
import wg.app.model.user.Teacher.Teacher;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class MessageLogged
{
    @Id
    @GeneratedValue
    private long id;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "student_id")
    private Student student;

    private LocalDate receiveDate;
    private LocalDate replyDate;

    private String topic;
    private String messageContent;
    private String replyMessageContent;

    private boolean status;

}
