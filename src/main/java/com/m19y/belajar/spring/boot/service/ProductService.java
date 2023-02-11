package com.m19y.belajar.spring.boot.service;

import com.m19y.belajar.spring.boot.entity.Product;
import com.m19y.belajar.spring.boot.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ProductService {

  private final ProductRepository productRepository;

  @Autowired
  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public Product saveProduct(Product product) {
    return productRepository.save(product);
  }

  public List<Product> saveProducts(List<Product> products) {
    return productRepository.saveAll(products);
  }

  public List<Product> getProducts() {
    return productRepository.findAll();
  }

  public Product getProductById(Long id) {
    return productRepository.findById(id).orElseThrow(() -> {
      throw new IllegalArgumentException("User with id " + id + " doesn't exists!");
    });
  }

  public Product getProductByName(String name) {
    return productRepository.findByName(name);
  }

  public void deleteProductById(Long id){
    boolean exists = productRepository.existsById(id);

    if(!exists){
      throw new IllegalStateException("Product with id " + id + " doesn't exists!");
    }

    productRepository.deleteById(id);
  }

  @Transactional
  public void updateProduct(Long id, String name, Long quantity, Double price){
    Product existsProduct = productRepository.findById(id).orElseThrow(()-> {
      throw new IllegalStateException("Product with id " + id + " doesn't exists!");
    });

    if(name != null && name.length() > 0 && !Objects.equals(name, existsProduct.getName())){
      existsProduct.setName(name);
    }

    Long dataQuantity = quantity > 3 ? quantity : 3L;
    existsProduct.setQuantity(dataQuantity);

    Double dataPrice = price > 100 ? price : 100;
    existsProduct.setPrice(dataPrice);

    productRepository.save(existsProduct);

  }


}
