package swd.SWDProject.service;

import swd.SWDProject.dto.HouseReceiptDTO;
import swd.SWDProject.dto.ReceiptMiniDetailDTO;

import java.util.List;

public interface ReceiptService {

    HouseReceiptDTO getHouseReceiptByType(String type);

}
