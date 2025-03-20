package com.topicos.proyectospring.controllers;


import com.topicos.proyectospring.models.Client;
import com.topicos.proyectospring.models.Wishlist;
import com.topicos.proyectospring.services.WishlistService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;


@Controller
@RequestMapping("/wishlist")
public class WishlistController {
    @Autowired
    private WishlistService wishlistService;


    @GetMapping
    public String viewWishlist(HttpSession session, Model model) {
        Client client = (Client) session.getAttribute("client");
        Wishlist wishlist = wishlistService.getOrCreateWishlist(client);
        model.addAttribute("wishlist", wishlist);
        return "wishlist/wishlist";
    }

    @PostMapping("/add/{itemId}")
    public String addToWishlist(HttpSession session, @PathVariable Long itemId, HttpServletRequest request) {
        Client client = (Client) session.getAttribute("client");

        if (client == null) {
            return "redirect:/login"; // Si el usuario no está autenticado, lo redirige al login
        }

        wishlistService.addItemToWishlist(client.getId(), itemId);

        // Redirigir a la misma página desde donde se envió la petición
        String referer = request.getHeader("Referer");
        return "redirect:" + (referer != null ? referer : "/items");
    }


    @PostMapping("/remove/{itemId}")
    public String removeFromWishlist(HttpSession session, @PathVariable Long itemId) {
        Client client = (Client) session.getAttribute("client");
        wishlistService.removeItemFromWishlist(client.getId(), itemId);
        return "redirect:/wishlist";
    }
}
