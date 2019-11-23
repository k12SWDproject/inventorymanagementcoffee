package swd.SWDProject.entity;


import lombok.*;

import javax.persistence.Column;
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
public class ReceiptDetail {
    @Id
    private int Id;
    private BigDecimal total;
    private int unitPrice;
    private int quantity;
    private int utilityServiceId;
    private Date createDate;
    private Date lastModified;
    @Column(name = "RECEIPT_ID")
    private  int receiptId;
}
