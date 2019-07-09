package wg.app.model.message;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import wg.app.model.user.User;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Data

public class MessageAdmin
{
    @Id
    @GeneratedValue
    private long id;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.PERSIST)
    private User user;

    private LocalDate receiveDate;
    private LocalDate returnDate;

    private String topic;
    private String messageContent;
    private String replyMessage;

    private boolean status;


}
