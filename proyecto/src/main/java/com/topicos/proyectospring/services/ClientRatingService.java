package com.topicos.proyectospring.services;

import com.topicos.proyectospring.models.ClientRating;
import com.topicos.proyectospring.repositories.ClientRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientRatingService {

    @Autowired
    private ClientRatingRepository clientRatingRepository;

    public List<ClientRating> getAllRatings() {
        return clientRatingRepository.findAll();
    }

    public Optional<ClientRating> getRatingById(Long ratingId) {
        return clientRatingRepository.findById(ratingId);
    }

    public List<ClientRating> getRatingsByClient(Long clientId) {
        return clientRatingRepository.findByClientId(clientId);
    }

    public List<ClientRating> getRatingsByItem(Long itemId) {
        return clientRatingRepository.findByItemId(itemId);
    }

    public ClientRating saveRating(ClientRating rating) {
        return clientRatingRepository.save(rating);
    }

    public void deleteRating(Long ratingId) {
        clientRatingRepository.deleteById(ratingId);
    }
}
