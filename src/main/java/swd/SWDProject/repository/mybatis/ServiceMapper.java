package swd.SWDProject.repository.mybatis;


import swd.SWDProject.dto.ReceiptMiniDetailDTO;
import swd.SWDProject.model.ServiceDTO;

import java.util.List;
import java.util.Map;

public interface ServiceMapper {

    List<ServiceDTO> getServices(Map param);

}
