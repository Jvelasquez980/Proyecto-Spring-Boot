package com.topicos.proyectospring.models;

import jakarta.persistence.*;

@Entity
@Table(name = "client_rating")
public class ClientRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ratingId;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private PCItem item;

    @Column(nullable = false)
    private int rating;

    public ClientRating() {}

    public ClientRating(Client client, PCItem item, int rating) {
        this.client = client;
        this.item = item;
        setRating(rating); // Validación en el setter
    }

    public Long getRatingId() {
        return ratingId;
    }

    public void setRatingId(Long ratingId) {
        this.ratingId = ratingId;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public PCItem getItem() {
        return item;
    }

    public void setItem(PCItem item) {
        this.item = item;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("El rating debe estar entre 1 y 5");
        }
        this.rating = rating;
    }
}
