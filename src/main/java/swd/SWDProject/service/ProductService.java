package swd.SWDProject.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import swd.SWDProject.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProducts(String filter) throws JsonProcessingException;

}
