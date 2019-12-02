package swd.SWDProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swd.SWDProject.entity.Product;
import swd.SWDProject.entity.Receipt;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {


}
