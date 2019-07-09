package wg.app.model.advertisement;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import wg.app.model.user.Teacher.Teacher;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Advertisement
{

    @Id
    @GeneratedValue
    private long id;

    @OneToOne(mappedBy = "advertisement")
    private Teacher teacher;

    private String content;
    private boolean premium;
    private LocalDate premiumEnds;
    private LocalDate dateOfAddingAdvertisement;

}
