package swd.SWDProject.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "UtilityService")
public class UtilityService {
    @Id
    private int id;
    private String name;
    private Date createDate;
    private Date lastMotified;
    private BigDecimal price;
    private int type;
    private int utilSrvCatId;
}
