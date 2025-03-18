package com.topicos.proyectospring.repositories;

import com.topicos.proyectospring.models.ClientRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRatingRepository extends JpaRepository<ClientRating, Long> {
    List<ClientRating> findByClientId(Long clientId);
    List<ClientRating> findByItemId(Long itemId);
}
