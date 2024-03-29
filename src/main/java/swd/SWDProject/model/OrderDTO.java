package swd.SWDProject.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import swd.SWDProject.entity.Order;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class OrderDTO {

    private Order order;
    private List<OrderDetailDTO> orderDetail;
}
