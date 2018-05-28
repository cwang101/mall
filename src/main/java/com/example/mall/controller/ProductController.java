package com.example.mall.controller;

import com.example.mall.entity.Product;
import com.example.mall.repository.InventoryRepository;
import com.example.mall.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    InventoryRepository inventoryRepository;

    @RequestMapping(method= RequestMethod.POST)
    public ResponseEntity<?> saveProduct(@RequestBody Product product) {

        long id = productRepository.save(product).getId();
        HttpHeaders responseHeaders = setResponseHeader(id);
        inventoryRepository.saveInventoryInfo(id);
        return new ResponseEntity<>(productRepository.findById(id), responseHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}",method= RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProduct(@PathVariable Long id, @RequestBody Product product)  {
        productRepository.updateById(id, product.getName(), product.getDescription(), product.getPrice());
    }

    @RequestMapping(value="/{id}",method= RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Product getProduct(@PathVariable Long id)  {
        return productRepository.findById(id).get();
    }

    @RequestMapping(method= RequestMethod.GET)
    public List<Product> getProducts(@RequestParam(value = "name", required = false, defaultValue = "") String name, @RequestParam(value = "description", required = false, defaultValue = "") String description) {
        if (!name.isEmpty() && !description.isEmpty()) {
            return productRepository.findByNameAndDescriptionContaining(name, description);
        }
         if (!name.isEmpty()) {
            return productRepository.findByName(name);
        }

        return productRepository.findAll();

    }
    private HttpHeaders setResponseHeader(long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("http://172.20.2.208:8083/products/" + id));
        return headers;

    }
}
