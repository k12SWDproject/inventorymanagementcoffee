package swd.SWDProject.repository.mybatis;


import swd.SWDProject.model.OrderDetailDTO;
import swd.SWDProject.model.ServiceDTO;

import java.util.List;
import java.util.Map;

public interface OrderMapper {

    List<OrderDetailDTO> getOrderDetail(Map param);

}
