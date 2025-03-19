package com.topicos.proyectospring.services;


import com.topicos.proyectospring.models.Client;
import com.topicos.proyectospring.models.PCItem;
import com.topicos.proyectospring.models.Wishlist;
import com.topicos.proyectospring.repositories.ClientRepository;
import com.topicos.proyectospring.repositories.PCItemRepository;
import com.topicos.proyectospring.repositories.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class WishlistService {
    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PCItemRepository pcItemRepository;

    public Wishlist getOrCreateWishlist(Client client) {
        return wishlistRepository.findByClient(client).orElseGet(() -> {
            Wishlist wishlist = new Wishlist();
            wishlist.setClient(client);
            return wishlistRepository.save(wishlist);
        });
    }

    public void addItemToWishlist(Long clientId, Long itemId) {
        Client client = clientRepository.findById(clientId).orElseThrow();
        PCItem item = pcItemRepository.findById(itemId).orElseThrow();
        Wishlist wishlist = getOrCreateWishlist(client);
        wishlist.getItems().add(item);
        wishlistRepository.save(wishlist);
    }

    public void removeItemFromWishlist(Long clientId, Long itemId) {
        Client client = clientRepository.findById(clientId).orElseThrow();
        Wishlist wishlist = getOrCreateWishlist(client);
        wishlist.getItems().removeIf(i -> i.getId().equals(itemId));
        wishlistRepository.save(wishlist);
    }
}
