package com.topicos.proyectospring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.topicos.proyectospring.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByUsername(String username);
}
