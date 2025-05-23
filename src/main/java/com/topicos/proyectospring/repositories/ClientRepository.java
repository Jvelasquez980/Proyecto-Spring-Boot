package com.topicos.proyectospring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.topicos.proyectospring.models.Client;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

}
