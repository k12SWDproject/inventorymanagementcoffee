package swd.SWDProject.mapper;

import org.mapstruct.Mapper;
import swd.SWDProject.entity.Order;
import swd.SWDProject.model.OrderDTO;

@Mapper
public interface OrderMapper {

    OrderDTO toOrderDTO(Order order);

}
