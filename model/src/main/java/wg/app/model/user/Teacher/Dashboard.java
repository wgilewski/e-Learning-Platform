package wg.app.model.user.Teacher;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dashboard
{
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(mappedBy = "dashboard")
    private Teacher teacher;

    private int visitorMale;
    private int visitorFemale;

    private int daily;
    private int monthly;

    @ElementCollection
    @CollectionTable
    private List<Integer> visitorsAge;
}
