package swd.SWDProject.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import swd.SWDProject.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProducts(String filter) throws JsonProcessingException;

    Product updateProduct(Product product) throws Exception;

    Product deleteProduct(Integer id) throws  Exception;

    Product addProduct(Product product) throws Exception;
}
