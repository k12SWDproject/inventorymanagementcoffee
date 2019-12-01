package swd.SWDProject.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ServiceDTO {
    private String name;
    private BigDecimal price;
    private String type;
    private String catogeryName;
    private String image;
}
