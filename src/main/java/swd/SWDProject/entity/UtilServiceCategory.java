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
@Table(name = "UtilServiceCate ry")
public class UtilServiceCategory {
    @Id
    private int id;
    private String name;
    private Date createDate;
    private Date lastModified;
    private int status;
}
