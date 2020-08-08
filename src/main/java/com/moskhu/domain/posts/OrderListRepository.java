package com.moskhu.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface OrderListRepository extends JpaRepository<OrderList, Long>{

    @Query("SELECT o FROM OrderList o ORDER BY o.id DESC")
    List<OrderList> findAllDesc();
}
