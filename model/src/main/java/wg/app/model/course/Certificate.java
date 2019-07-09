package wg.app.model.course;


import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Data

public class Certificate
{
    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String description;
    private Certificates certificates;



    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "finalExam_id", unique = true)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private FinalExam finalExam;

    private boolean granted;


}
