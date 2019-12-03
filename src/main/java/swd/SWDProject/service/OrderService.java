package swd.SWDProject.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import swd.SWDProject.model.OrderDTO;
import swd.SWDProject.model.OrderRequestDTO;

import java.util.List;

public interface OrderService {

    List<OrderDTO> getOrders(String filter) throws JsonProcessingException;

    List<OrderDTO> getMyOrders(String filter) throws JsonProcessingException;

    OrderDTO paymentOrders(List<OrderRequestDTO> orderRequestDTOS) throws Exception;

}
