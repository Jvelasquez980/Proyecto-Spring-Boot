package com.topicos.proyectospring.repositories;

import com.topicos.proyectospring.models.Client;
import com.topicos.proyectospring.models.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    Optional<Wishlist> findByClient(Client client);
    
}
