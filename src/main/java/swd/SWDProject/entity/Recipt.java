package swd.SWDProject.entity;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "Receipt")
public class Recipt {
    @Id
    private int id;
    private String title;
    private String type;
    private Date pulishDate;
    private Date paymentDate;
    private int status;
    private int houseId;
    private String description;

}
