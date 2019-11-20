package swd.SWDProject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ReceiptDetailDTO {

    private String receiptId;
    private String receiptName;
    private BigInteger total;
    private int quantity;
    private String date;
    private String unitPrice;
    private String houseName;

}
