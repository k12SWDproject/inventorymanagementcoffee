package swd.SWDProject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class HouseReceiptDTO {

    private int houseId;
    private String houseName;
    private List<ReceiptMiniDetailDTO> listPayedReceipt;
    private List<ReceiptMiniDetailDTO> listNotPayedReceipt;

}
