package com.example.demoproductcrudexample.controller;

import com.example.demoproductcrudexample.entity.Product;
import com.example.demoproductcrudexample.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

//import javax.validation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
public class ProductController {

    private ProductService service;

    @Autowired
    public ProductController(ProductService service){
        this.service = service;
    }


        @PostMapping("/addProduct")
    public Product addProduct(@RequestBody Product product){

            /*ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<Product>> violations = validator.validate(product);
            for(ConstraintViolation<Product> voilation : violations){
                System.out.println(voilation.getMessage());
            }*/
            return service.saveProduct(product);
    }

    @PostMapping("/addProducts")
    public List<Product> addProducts(@Valid  @RequestBody List<Product> products){
        return service.saveProducts(products);
    }

    @GetMapping("/products")
    public List<Product> findAllProducts(){
        return service.getProducts();
    }

    @GetMapping("/productswithsorting/{field}")
    public List<Product> findAllProductsWithSorting(@PathVariable String field){
        return service.getProductsWithSorting(field);
    }

    @GetMapping("/productswithpagination/{offset}/{pageSize}")
    public Page<Product> findAllProductsWithSorting(@PathVariable int offset, @PathVariable int pageSize){
        return service.getProductsWithPagination(offset, pageSize);
    }

    @GetMapping("/productById/{id}")
    public Product findProductById(@PathVariable int id){
        return service.getProductById(id);
    }

    @GetMapping("/productByName/{name}")
    public Product findProductByName(@PathVariable String name){
        return service.getProductName(name);
    }

    @PutMapping("/updateProducts")
    public Product updateProduct(@RequestBody Product product){
        return service.updateProduct(product);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable int id){
        return service.deleteProduct(id);
    }

}
