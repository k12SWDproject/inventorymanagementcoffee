package swd.SWDProject.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import swd.SWDProject.dto.HouseReceiptDTO;
import swd.SWDProject.entity.Receipt;
import swd.SWDProject.service.model.ReceiptDTO;

public interface HouseService {

    void addMemberToHouse(String username) throws Exception;

    void deleteMemberToHouse(String username) throws Exception;

}
