package swd.SWDProject.service.imp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swd.SWDProject.config.sercurity.JWTVerifier;
import swd.SWDProject.constant.StringRS;
import swd.SWDProject.entity.Product;
import swd.SWDProject.entity.User;
import swd.SWDProject.filter.ProductFilter;
import swd.SWDProject.repository.ProductRepository;
import swd.SWDProject.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@Transactional
public class ProductServiceImp implements swd.SWDProject.service.ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> getProducts(String filter) throws JsonProcessingException {
        try{
            log.info(StringRS.BEGIN_SERVICE + "getProducts");
            List<Product> products = null;
            if(filter != null) {
                ProductFilter productFilter = new ObjectMapper().readValue(filter, ProductFilter.class);

            }

            products = productRepository.findAll();

            return products;
        }finally {
            log.info(StringRS.END_SERVICE + "getProducts");
        }
    }
}
