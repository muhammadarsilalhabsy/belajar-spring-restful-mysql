package com.m19y.belajar.spring.boot.repository;

import com.m19y.belajar.spring.boot.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

  Product findByName(String name);
}
