package swd.SWDProject.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "Role")
public class Role {
    @Id
    @Column(name = "ID")
    private Integer id;
    private String roleName;
}
