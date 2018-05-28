package com.example.mall.repository;

import com.example.mall.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Transactional
    Product save(Product product);

    Product findById(long id);


    @Modifying
    @Transactional
    @Query("update Product product set product.name = ?2, product.description = ?3, product.price = ?4 where product.id = ?1")
    int updateById(Long id, String name, String description, Double price);

    List<Product> findByNameAndDescriptionContaining(String name, String description);

    List<Product> findByName(String name);

    List<Product> findAll();

}
