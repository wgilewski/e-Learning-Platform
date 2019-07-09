package wg.app.model.user.Teacher.TeacherQuiz;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Question
{
    @Id
    @GeneratedValue
    private Long id;

    private String question;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Quiz quiz;
}
