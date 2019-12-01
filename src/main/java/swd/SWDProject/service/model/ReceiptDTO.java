package swd.SWDProject.service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import swd.SWDProject.entity.Receipt;
import swd.SWDProject.entity.ReceiptDetail;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReceiptDTO{

    Receipt receipt;
    List<ReceiptDetail> receiptDetailList;

}
