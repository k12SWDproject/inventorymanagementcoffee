package swd.SWDProject.service.imp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import swd.SWDProject.config.sercurity.JWTVerifier;
import swd.SWDProject.constant.StringRS;
import swd.SWDProject.entity.Order;
import swd.SWDProject.entity.Order_;
import swd.SWDProject.entity.Product;
import swd.SWDProject.entity.ProductOrder;
import swd.SWDProject.entity.SpecificationBuilder;
import swd.SWDProject.entity.User;
import swd.SWDProject.filter.OrderFilter;
import swd.SWDProject.model.OrderDTO;
import swd.SWDProject.model.OrderDetailDTO;
import swd.SWDProject.model.OrderRequestDTO;
import swd.SWDProject.repository.OrderRepository;
import swd.SWDProject.repository.ProductRepository;
import swd.SWDProject.repository.UserRepository;
import swd.SWDProject.repository.mybatis.OrderMapper;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class OrderServiceImp implements swd.SWDProject.service.OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<OrderDTO> getOrders(String filter) throws JsonProcessingException {
        try {
            log.info(StringRS.BEGIN_SERVICE + "getOrders");
            List<OrderDTO> rs = new ArrayList<>();
            List<Specification<Order>> specs = new ArrayList<>();

            if (filter != null) {
                OrderFilter orderFilter = new ObjectMapper().readValue(filter, OrderFilter.class);

                if (orderFilter.getId() != 0) {
                    specs.add(((root, criteriaQuery, criteriaBuilder) -> {
                        return criteriaBuilder.equal(root.get(Order_.ID), orderFilter.getId());
                    }));
                }

            }

            List<Order> orders = orderRepository.findAll(SpecificationBuilder.build(specs));


            orders.stream().forEach(order -> {
                Map param = new HashMap();
                param.put("id", order.getId());
                List<OrderDetailDTO> detailList = orderMapper.getOrderDetail(param);
                rs.add(new OrderDTO(order, detailList));
            });

            return rs;
        } finally {
            log.info(StringRS.END_SERVICE + "getOrders");
        }
    }

    @Override
    public List<OrderDTO> getMyOrders(String filter) throws JsonProcessingException {
        try {
            log.info(StringRS.BEGIN_SERVICE + "getOrders");
            List<OrderDTO> rs = new ArrayList<>();
            List<Specification<Order>> specs = new ArrayList<>();

            User user = userRepository.findUserByUsername(JWTVerifier.USERNAME);

            specs.add(((root, criteriaQuery, criteriaBuilder) -> {
                return criteriaBuilder.equal(root.get(Order_.USER_ID), user.getId());
            }));

            if (filter != null) {
                OrderFilter orderFilter = new ObjectMapper().readValue(filter, OrderFilter.class);

                if (orderFilter.getId() != 0) {
                    specs.add(((root, criteriaQuery, criteriaBuilder) -> {
                        return criteriaBuilder.equal(root.get(Order_.ID), orderFilter.getId());
                    }));
                }

            }

            List<Order> orders = orderRepository.findAll(SpecificationBuilder.build(specs));

            orders.stream().forEach(order -> {
                Map param = new HashMap();
                param.put("id", order.getId());
                List<OrderDetailDTO> detailList = orderMapper.getOrderDetail(param);
                rs.add(new OrderDTO(order, detailList));
            });

            return rs;
        } finally {
            log.info(StringRS.END_SERVICE + "getOrders");
        }
    }

    @Override
    public OrderDTO paymentOrders(List<OrderRequestDTO> orderRequest) throws Exception{
        try {
            log.info(StringRS.BEGIN_SERVICE + "paymentOrders");
            OrderDTO rs = null;
            List<Product> products = new ArrayList<>();
            List<OrderDetailDTO> orderDetailDTOS = new ArrayList<>();
            // search product and += sum
            int sum = 0;
            for (OrderRequestDTO rq: orderRequest) {

                Product product = productRepository.getOne(rq.getId());

                if (product== null) {
                    throw  new Exception();
                }

                products.add(product);
                sum += product.getPrice() * rq.getQuantity();
                orderDetailDTOS.add(OrderDetailDTO.builder().id(rq.getId()).image(product.getImage())
                        .price(new BigInteger(product.getPrice()+""))
                        .name(product.getName()).quantity(rq.getQuantity()).build());
            }
            //check sum < account user
            User user = userRepository.findUserByUsername(JWTVerifier.USERNAME);
            if (user.getMoney().compareTo(new BigDecimal(sum+"")) == -1) {
                throw new Exception();
            }
            //create order

            Order newOrder = Order.builder().userId(user.getId()).total(new BigInteger(sum+""))
                    .createDate(new Date()).build();
            newOrder = orderRepository.save(newOrder);
            //create order detail

            Order finalNewOrder = newOrder;
            List<ProductOrder> productOrders = orderRequest.stream().map(item -> {
                return ProductOrder.builder().orderId(finalNewOrder.getId()).productId(item.getId())
                        .quantity(item.getQuantity()).build();
            }).collect(Collectors.toList());

            //update user account
            user.setMoney(user.getMoney().subtract(new BigDecimal(sum+"")));
            userRepository.save(user);

            rs = OrderDTO.builder().order(finalNewOrder).orderDetail(orderDetailDTOS).build();
            return rs;
        } finally {
            log.info(StringRS.END_SERVICE + "paymentOrders");
        }
    }

}
