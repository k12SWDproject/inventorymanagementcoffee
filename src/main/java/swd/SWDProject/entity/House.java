package swd.SWDProject.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "House")
public class House {
    @Id
    private Integer id;
    private String houseName;
    private String Description;
    private Integer ownerId;
    private String profileImage;
    private Integer status;
    private Integer waterMeter;
}
