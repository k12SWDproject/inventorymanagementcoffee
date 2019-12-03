package swd.SWDProject.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderDetailDTO {
    private int id;
    private String name;
    private String image;
    private int quantity;
    private BigInteger price;
}
