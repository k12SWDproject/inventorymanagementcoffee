package swd.SWDProject.repository.mybatis;


import swd.SWDProject.dto.ReceiptMiniDetailDTO;

import java.util.List;
import java.util.Map;

public interface HouseReceiptMapper {

    List<ReceiptMiniDetailDTO> getHouseReceiptByUsernameAndType(Map param);

}
