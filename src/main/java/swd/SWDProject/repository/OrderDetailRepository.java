package swd.SWDProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import swd.SWDProject.entity.Order;
import swd.SWDProject.entity.ProductOrder;

@Repository
public interface OrderDetailRepository extends JpaRepository<ProductOrder, Integer>, JpaSpecificationExecutor<ProductOrder> {


}
