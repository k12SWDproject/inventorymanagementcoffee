package swd.SWDProject.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import swd.SWDProject.dto.HouseReceiptDTO;
import swd.SWDProject.service.model.ReceiptDTO;

public interface ReceiptService {

    HouseReceiptDTO getHouseReceiptByType(String type);

    ReceiptDTO getReceipt(String filter) throws JsonProcessingException;

}
