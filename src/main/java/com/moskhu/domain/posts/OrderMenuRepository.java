package com.moskhu.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface OrderMenuRepository extends JpaRepository<OrderMenu, Long>{

    @Query("SELECT o FROM OrderMenu o ORDER BY o.id DESC")
    List<OrderMenu> findAllDesc();
}
