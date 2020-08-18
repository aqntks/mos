package com.moskhu.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface StatusRepository extends JpaRepository<Status, Long>{

}
