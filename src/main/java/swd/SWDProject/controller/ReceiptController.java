package swd.SWDProject.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import swd.SWDProject.constant.StringRS;
import swd.SWDProject.dto.HouseReceiptDTO;
import swd.SWDProject.entity.Receipt;
import swd.SWDProject.service.ReceiptService;
import swd.SWDProject.service.model.ReceiptDTO;

@Slf4j
@RestController
@RequestMapping("/api/receipts")
public class ReceiptController {

    @Autowired
    ReceiptService receiptService;

    @GetMapping("/user/{type}")
    public ResponseEntity<HouseReceiptDTO> getListReceiptOfUser(@PathVariable String type) {
        try{
            log.info(StringRS.BEGIN_CONTROLLER+"getListReceiptOfUser");
            HouseReceiptDTO houseReceiptDTO = receiptService.getHouseReceiptByType(type);
            return new ResponseEntity<>(houseReceiptDTO,HttpStatus.OK);
        }finally {
            log.info(StringRS.END_CONTROLLER+"getListReceiptOfUser");
        }
    }
    @GetMapping
    public ResponseEntity<ReceiptDTO> getReceipt(String filter) throws JsonProcessingException {
        try{
            log.info(StringRS.BEGIN_CONTROLLER+"getListReceipt");
            ReceiptDTO receipt = receiptService.getReceipt(filter);
            return new ResponseEntity<>(receipt,HttpStatus.OK);
        }finally {
            log.info(StringRS.END_CONTROLLER+"getListReceipt");
        }
    }

}
