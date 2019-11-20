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
@Table(name = "ReceiptDetail")
public class ReciptDetail {
    @Id
    private int Id;
    private BigDecimal total;
    private int unitPrice;
    private int quantity;
    private int utilityServiceId;
    private Date createDate;
    private Date lastMotified;
    private int receiptId;
}
