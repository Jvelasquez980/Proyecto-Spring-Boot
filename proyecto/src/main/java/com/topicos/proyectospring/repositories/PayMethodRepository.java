package com.topicos.proyectospring.repositories;

import com.topicos.proyectospring.models.PayMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayMethodRepository extends JpaRepository<PayMethod, Long> {
}
