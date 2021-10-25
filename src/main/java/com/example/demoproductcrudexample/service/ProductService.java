package com.example.demoproductcrudexample.service;

import com.example.demoproductcrudexample.entity.Product;
import com.example.demoproductcrudexample.exceptions.ResourceNotFoundException;
import com.example.demoproductcrudexample.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Product saveProduct(Product product){
        return repository.save(product);
    }

    public List<Product> saveProducts(List<Product> products){
        return repository.saveAll(products);
    }

    public List<Product> getProducts(){
        return repository.findAll();
    }

    public List<Product> getProductsWithSorting(String field){
            return repository.findAll(Sort.by(Sort.Direction.ASC, field));
    }

    public Page<Product> getProductsWithPagination(int offset, int pageSize){
        return repository.findAll(PageRequest.of(offset, pageSize));
    }
    public Product getProductById(int id){
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));
    }
    public Product getProductName(String name){
        Optional<Product> optional = repository.findByName(name);
        return optional.orElseThrow(()-> new ResourceNotFoundException("Resource Not Found Exception"));
    }

    public String deleteProduct(int id){
        repository.deleteById(id);
        return "product removed "+id;
    }

    public Product updateProduct(Product product){
        Product existingProduct = repository.findById(product.getId()).orElseThrow(()->new ResourceNotFoundException("Resource not found"));
        existingProduct.setName(product.getName());
        existingProduct.setQuantity(product.getQuantity());
        existingProduct.setPrice(product.getPrice());
        return repository.save(existingProduct);
    }

}
