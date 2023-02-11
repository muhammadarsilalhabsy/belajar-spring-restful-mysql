package com.m19y.belajar.spring.boot.controller;

import com.m19y.belajar.spring.boot.entity.Product;
import com.m19y.belajar.spring.boot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1")
public class ProductController {

  private final ProductService productService;

  @Autowired
  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @PostMapping("product")
  public Product addNewProduct(@RequestBody Product product){
    return productService.saveProduct(product);
  }

  @PostMapping("products")
  public List<Product> addNewProduct(@RequestBody List<Product> products){
    return productService.saveProducts(products);
  }

  @GetMapping(path = "product/id/{productId}")
  public Product getProductById(@PathVariable("productId") Long id){
    return productService.getProductById(id);
  }

  @GetMapping("products")
  public List<Product> getAllProduct(){
    return productService.getProducts();
  }

  @GetMapping(path = "product/name/{productName}")
  public Product getProductByName(@PathVariable("productName") String name){
    return productService.getProductByName(name);
  }

  @PutMapping(path = "product/id/{productId}")
  public void updateProduct(
          @PathVariable("productId")Long id,
          @RequestParam(required = false)String name,
          @RequestParam(required = false)Long quantity,
          @RequestParam(required = false)Double price
          ){
    productService.updateProduct(id, name, quantity, price);
  }

  @DeleteMapping(path = "product/id/{productId}")
  public void deleteProduct(@PathVariable("productId") Long id){
    productService.deleteProductById(id);
  }


}
