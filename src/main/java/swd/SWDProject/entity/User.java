package swd.SWDProject.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "[USER]")
public class User {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "PASSWORD")
    private String password;
    @ManyToOne(fetch = FetchType.EAGER)
    private Role role;
    @ManyToOne(fetch = FetchType.EAGER)
    private House house;
    private Integer creator;
    private String profileImage;
    private Date dateOfBirth;
    private String idNumber;
    private Integer gender;
    private String fullName;
    private Date createDate;
    private Date lastModified;
    private Integer status;
    private BigDecimal money;
    private String email;
    private int familyLevel;
    @Column(name = "USER_GG_ID")
    private String userGGId;
}
