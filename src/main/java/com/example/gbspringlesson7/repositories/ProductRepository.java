package com.example.gbspringlesson7.repositories;

import com.example.gbspringlesson7.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByCostBetween(Integer min, Integer max);

    @Query("select p from Product p where p.cost < :border")
    List<Product> findChipperThen(Integer border);

    @Query("select p from Product p where p.cost > :border")
    List<Product> findMoreExpensiveThen(Integer border);

    @Transactional
    @Modifying
    @Query("update Product p set p.cost = p.cost + ?1 where p.id = ?2")
    void changeCost(Integer delta, Long id);
}
