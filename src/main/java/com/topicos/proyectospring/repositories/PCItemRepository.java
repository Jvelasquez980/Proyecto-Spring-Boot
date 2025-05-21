package com.topicos.proyectospring.repositories;

import com.topicos.proyectospring.models.PCItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// PCItemRepository.java
import java.util.List;

@Repository
public interface PCItemRepository extends JpaRepository<PCItem, Long> {
    List<PCItem> findByStockGreaterThan(int stock); // trae productos con stock > 0
}
