package wg.app.model.course;


import lombok.*;
import wg.app.model.user.Student;
import wg.app.model.user.Teacher.Teacher;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data

public class IndividualCourse
{
    @Id
    @GeneratedValue
    private long id;

    private String name;
    private String description;
    private Subject subject;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "certificate_id", unique = true)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Certificate certificate;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Teacher owner;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Student student;

    @OneToMany(mappedBy = "individualCourse")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Lesson> lessons;



}
