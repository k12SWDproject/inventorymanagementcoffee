package swd.SWDProject.service.imp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import swd.SWDProject.constant.StringRS;
import swd.SWDProject.entity.Product;
import swd.SWDProject.entity.Product_;
import swd.SWDProject.entity.SpecificationBuilder;
import swd.SWDProject.filter.ProductFilter;
import swd.SWDProject.repository.ProductRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
            List<Specification<Product>> specs = new ArrayList<>();
            if(filter != null) {
                ProductFilter productFilter = new ObjectMapper().readValue(filter, ProductFilter.class);

                if(productFilter.getName() != null) {
                    specs.add((root, cq, cb) -> {
                        return  cb.like(cb.upper(root.get(Product_.NAME)), "%" + productFilter.getName() + "%" ,'\\');
                    });
                }
            }

            products = productRepository.findAll(SpecificationBuilder.build(specs));

            return products;
        }finally {
            log.info(StringRS.END_SERVICE + "getProducts");
        }
    }

    @Override
    public Product updateProduct(Product product) throws Exception {
        Optional<Product> rs = productRepository.findById(product.getId());
        if(!rs.isPresent()) {
            throw new Exception();
        }
        product = productRepository.save(product);
        return product;
    }

    @Override
    public Product deleteProduct(Integer id) throws Exception {
        Optional<Product> rs = productRepository.findById(id);
        if(!rs.isPresent()) {
            throw new Exception();
        }
       productRepository.deleteById(id);

        return null;
    }

    @Override
    public Product addProduct(Product product) throws Exception {
        Optional<Product> rs = productRepository.findById(product.getId());
        if(!rs.isPresent()) {
            throw new Exception();
        }
        product = productRepository.save(product);
        return product;
    }
}
