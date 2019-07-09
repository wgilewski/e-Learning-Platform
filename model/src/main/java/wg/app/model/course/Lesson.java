package wg.app.model.course;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder

public class Lesson
{
    @Id
    @GeneratedValue
    private long id;

    private LocalDateTime localDateTime;
    private BigDecimal price;
    private String name;
    private boolean paymentStatus;
    private int duration;

    private String notes;
    private String homework;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private IndividualCourse individualCourse;





}
