package com.topicos.proyectospring.repositories;

import com.topicos.proyectospring.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
