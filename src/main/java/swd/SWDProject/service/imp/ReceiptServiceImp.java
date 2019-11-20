package swd.SWDProject.service.imp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swd.SWDProject.config.sercurity.JWTVerifier;
import swd.SWDProject.constant.StringRS;
import swd.SWDProject.dto.HouseReceiptDTO;
import swd.SWDProject.dto.ReceiptMiniDetailDTO;
import swd.SWDProject.entity.User;
import swd.SWDProject.repository.mybatis.HouseReceiptMapper;
import swd.SWDProject.service.ReceiptService;
import swd.SWDProject.service.UserService;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class ReceiptServiceImp implements ReceiptService{

    @Autowired
    UserService userService;

    @Autowired
    HouseReceiptMapper houseReceiptMapper;

    @Override
    public HouseReceiptDTO getHouseReceiptByType(String type) {
        try{
            log.info(StringRS.BEGIN_SERVICE+ "getHouseReceipt");
            HouseReceiptDTO rs = new HouseReceiptDTO();

            User user = userService.getUserByUserName(JWTVerifier.USERNAME);

            rs.setHouseId(user.getHouse().getId());
            rs.setHouseName(user.getHouse().getHouseName());

            int typeInt = 0;

            switch (type) {
                case "ELECTRIC_TYPE": typeInt = StringRS.ELECTRIC_TYPE; break;
                case "WATER_TYPE": typeInt = StringRS.WATER_TYPE; break;
                case "ORTHER_TYPE": typeInt = StringRS.ORTHER_TYPE; break;
                case "INTERNET_TYPE": typeInt = StringRS.INTERNET_TYPE; break;

            }


            Map param = new HashMap();
            param.put("username", JWTVerifier.USERNAME);
            param.put("type", typeInt);

            List<ReceiptMiniDetailDTO> listReceipt = houseReceiptMapper.getHouseReceiptByUsernameAndType(param);

            List<ReceiptMiniDetailDTO> listPayReceipt = listReceipt.stream().filter(item->
                item.getStatus() == 0
            ).collect(Collectors.toList());

            List<ReceiptMiniDetailDTO> listNotPayReceipt = listReceipt.stream().filter(item->
                    item.getStatus() == 1
            ).collect(Collectors.toList());

            rs.setListNotPayedReceipt(listNotPayReceipt);
            rs.setListPayedReceipt(listPayReceipt);

            return rs;
        }finally {
            log.info(StringRS.END_SERVICE+ "getHouseReceipt");
        }
    }
}
