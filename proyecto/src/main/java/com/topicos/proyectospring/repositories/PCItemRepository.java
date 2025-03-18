package com.topicos.proyectospring.repositories;

import com.topicos.proyectospring.models.PCItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PCItemRepository extends JpaRepository<PCItem, Long> {
}
