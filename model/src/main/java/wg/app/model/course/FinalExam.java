package wg.app.model.course;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class FinalExam
{
    @Id
    @GeneratedValue
    private Long id;

    private boolean passed;
    private double points;
    private int time;
    private LocalDateTime localDateTime;
}
