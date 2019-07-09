package wg.app.model.user.Teacher.TeacherQuiz;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import wg.app.model.course.Subject;
import wg.app.model.user.Teacher.Teacher;

import javax.persistence.*;
import java.util.List;

@Data

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Quiz
{

    @Id
    @GeneratedValue
    private Long id;


    @ManyToOne(cascade = CascadeType.PERSIST)
    private Teacher teacher;

    private String name;

    private boolean onlyForFriends;

    private Subject subject;

    @OneToMany(mappedBy = "quiz")
    private List<Question> questions;


}
