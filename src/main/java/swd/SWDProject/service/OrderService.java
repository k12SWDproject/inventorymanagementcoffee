package swd.SWDProject.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import swd.SWDProject.entity.Order;
import swd.SWDProject.entity.Product;
import swd.SWDProject.model.OrderDTO;

import java.util.List;

public interface OrderService {

    List<OrderDTO> getOrders(String filter) throws JsonProcessingException;

    List<OrderDTO> getMyOrders(String filter) throws JsonProcessingException;

}
