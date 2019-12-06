package swd.SWDProject.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import swd.SWDProject.constant.StringRS;
import swd.SWDProject.entity.Order;
import swd.SWDProject.filter.OrderFilter;
import swd.SWDProject.model.OrderDTO;
import swd.SWDProject.model.OrderDetailRequestDTO;
import swd.SWDProject.model.OrderRequestDTO;
import swd.SWDProject.service.HouseService;
import swd.SWDProject.service.OrderService;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping
    public ResponseEntity getOrders(String filter) {
        try {
            log.info(StringRS.BEGIN_CONTROLLER + "Get Ordes");

            List<OrderDTO> ordes = orderService.getOrders(filter);

            return ResponseEntity.ok(ordes);
        } catch (JsonProcessingException e) {
            return ResponseEntity.badRequest().build();
        } finally {
            log.info(StringRS.END_CONTROLLER + "Get Ordes");
        }
    }

    @GetMapping(value = "/my-order")
    public ResponseEntity getMyOrders(String filter) {
        try {
            log.info(StringRS.BEGIN_CONTROLLER + "Get Ordes");

            List<OrderDTO> ordes = orderService.getMyOrders(filter);

            return ResponseEntity.ok(ordes);
        } catch (JsonProcessingException e) {
            return ResponseEntity.badRequest().build();
        } finally {
            log.info(StringRS.END_CONTROLLER + "Get Ordes");
        }
    }

    @PostMapping
    public ResponseEntity paymentOrders(@RequestBody List<OrderRequestDTO> orderRequest) {
        try {
            log.info(StringRS.BEGIN_CONTROLLER + "paymentOrders");
            OrderDTO order = orderService.paymentOrders(orderRequest);
            return ResponseEntity.ok(order);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        } finally {
            log.info(StringRS.BEGIN_CONTROLLER + "paymentOrders");
        }
    }
}
