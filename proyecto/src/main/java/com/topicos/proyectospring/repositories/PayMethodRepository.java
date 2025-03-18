package com.topicos.proyectospring.repositories;

import com.topicos.proyectospring.models.PayMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PayMethodRepository extends JpaRepository<PayMethod, Long> {
    List<PayMethod> findByClientId(Long clientId); // Obtener métodos de pago de un cliente específico
}
