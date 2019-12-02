package swd.SWDProject.service.imp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swd.SWDProject.config.sercurity.JWTVerifier;
import swd.SWDProject.constant.StringRS;
import swd.SWDProject.dto.HouseReceiptDTO;
import swd.SWDProject.dto.ReceiptMiniDetailDTO;
import swd.SWDProject.entity.Receipt;
import swd.SWDProject.entity.ReceiptDetail;
import swd.SWDProject.entity.User;
import swd.SWDProject.filter.ReciptFilter;
import swd.SWDProject.repository.ReceiptDetailRepository;
import swd.SWDProject.repository.ReceiptRepository;
import swd.SWDProject.repository.mybatis.HouseReceiptMapper;
import swd.SWDProject.service.ReceiptService;
import swd.SWDProject.service.UserService;
import swd.SWDProject.service.model.ReceiptDTO;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class ReceiptServiceImp implements ReceiptService {

    @Autowired
    UserService userService;

    @Autowired
    ReceiptRepository receiptRepository;
    @Autowired
    ReceiptDetailRepository receiptDetailRepository;
    @Autowired
    HouseReceiptMapper houseReceiptMapper;

    @Override
    public HouseReceiptDTO getHouseReceiptByType(String type) {
        try {
            log.info(StringRS.BEGIN_SERVICE + "getHouseReceipt");
            HouseReceiptDTO rs = new HouseReceiptDTO();

            User user = userService.getUserByUserName(JWTVerifier.USERNAME);

            rs.setHouseId(user.getHouse().getId());
            rs.setHouseName(user.getHouse().getHouseName());

            int typeInt = 0;

            switch (type) {
                case "ELECTRIC_TYPE":
                    typeInt = StringRS.ELECTRIC_TYPE;
                    break;
                case "WATER_TYPE":
                    typeInt = StringRS.WATER_TYPE;
                    break;
                case "ORTHER_TYPE":
                    typeInt = StringRS.ORTHER_TYPE;
                    break;
                case "INTERNET_TYPE":
                    typeInt = StringRS.INTERNET_TYPE;
                    break;

            }


            Map param = new HashMap();
            param.put("username", JWTVerifier.USERNAME);
            param.put("type", typeInt);

            List<ReceiptMiniDetailDTO> listReceipt = houseReceiptMapper.getHouseReceiptByUsernameAndType(param);

            List<ReceiptMiniDetailDTO> listPayReceipt = listReceipt.stream().filter(item ->
                    item.getStatus() == 0
            ).collect(Collectors.toList());

            List<ReceiptMiniDetailDTO> listNotPayReceipt = listReceipt.stream().filter(item ->
                    item.getStatus() == 1
            ).collect(Collectors.toList());

            rs.setListNotPayedReceipt(listNotPayReceipt);
            rs.setListPayedReceipt(listPayReceipt);

            return rs;
        } finally {
            log.info(StringRS.END_SERVICE + "getHouseReceipt");
        }
    }

    @Override
    public ReceiptDTO getReceipt(String filter) throws JsonProcessingException {
        try {
            log.info(StringRS.BEGIN_SERVICE + "getReceipt");
            ObjectMapper objectMapper = new ObjectMapper();
            ReciptFilter reciptFilter = objectMapper.readValue(filter, ReciptFilter.class);
            Receipt receipt = receiptRepository.findReceiptById(reciptFilter.getId());
            List<ReceiptDetail> receiptDetailList = receiptDetailRepository.findReceiptDetailsByReceiptId(reciptFilter.getId());
            return new ReceiptDTO(receipt, receiptDetailList);

        } finally {
            log.info(StringRS.END_SERVICE + "getReceipt");
        }
    }

    @Override
    public Receipt paymentReceipt(int id) throws Exception {
        try {
            log.info(StringRS.BEGIN_SERVICE + "paymentReceipt");
            Receipt receipt = null;
            receipt = receiptRepository.getOne(id);

            if (receipt == null) {
                throw new Exception("Receipt is not exits");
            }

            receipt.setStatus(0);
            receipt.setPaymentDate(new Date());
            receipt = receiptRepository.save(receipt);

            return receipt;
        } finally {
            log.info(StringRS.END_SERVICE + "paymentReceipt");
        }
    }
}
