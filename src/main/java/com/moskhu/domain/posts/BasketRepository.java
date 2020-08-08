package com.moskhu.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface BasketRepository extends JpaRepository<Basket, Long>{

    @Query("SELECT b FROM Basket b ORDER BY b.id DESC")
    List<Basket> findAllDesc();
}
