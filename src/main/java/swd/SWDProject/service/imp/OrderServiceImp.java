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
import swd.SWDProject.entity.SpecificationBuilder;
import swd.SWDProject.entity.User;
import swd.SWDProject.filter.OrderFilter;
import swd.SWDProject.model.OrderDTO;
import swd.SWDProject.model.OrderDetailDTO;
import swd.SWDProject.repository.OrderRepository;
import swd.SWDProject.repository.UserRepository;
import swd.SWDProject.repository.mybatis.OrderMapper;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

}
