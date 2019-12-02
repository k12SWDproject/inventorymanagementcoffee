package swd.SWDProject.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import swd.SWDProject.constant.StringRS;
import swd.SWDProject.entity.Product;
import swd.SWDProject.service.HouseService;
import swd.SWDProject.service.ProductService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    public ResponseEntity getProducts(String filter) {
        try {
            log.info(StringRS.BEGIN_CONTROLLER+ "getProduct");
            List<Product> products = productService.getProducts(filter);

            return  ResponseEntity.ok(products);
        } catch (JsonProcessingException e) {
            return  ResponseEntity.badRequest().build();
        } finally {
            log.info(StringRS.END_CONTROLLER+ "getProduct");
        }
    }


}
