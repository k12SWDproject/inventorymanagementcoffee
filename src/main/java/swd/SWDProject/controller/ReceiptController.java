package swd.SWDProject.controller;


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
import swd.SWDProject.service.ReceiptService;

@Slf4j
@RestController
@RequestMapping("/api/receipts")
public class ReceiptController {

    @Autowired
    ReceiptService receiptService;

    @GetMapping("/{type}")
    public ResponseEntity<HouseReceiptDTO> getListReceiptOfUser(@PathVariable String type) {
        try{
            log.info(StringRS.BEGIN_CONTROLLER+"getListReceiptOfUser");
            HouseReceiptDTO houseReceiptDTO = receiptService.getHouseReceiptByType(type);
            return new ResponseEntity<>(houseReceiptDTO,HttpStatus.OK);
        }finally {
            log.info(StringRS.END_CONTROLLER+"getListReceiptOfUser");
        }
    }

}
