package com.topicos.proyectospring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.topicos.proyectospring.services.ChuckNorrisService;
import com.topicos.proyectospring.models.ChuckNorrisJoke;
import jakarta.servlet.http.HttpSession;

@Controller

public class HomeController {

    private final ChuckNorrisService chuckNorrisService;

    @Autowired
    public HomeController(ChuckNorrisService chuckNorrisService) {
        this.chuckNorrisService = chuckNorrisService;
    }

    @GetMapping("/")
    public String index(Model model, HttpSession session) {
        if (session.getAttribute("client") == null) {
            return "redirect:/login";
        }

        ChuckNorrisJoke joke = chuckNorrisService.getRandomJoke().block();

        model.addAttribute("title", "Welcome to Spring Boot");
        model.addAttribute("subtitle", "A Spring Boot Eafit App");
        model.addAttribute("joke", joke);
        model.addAttribute("client", session.getAttribute("client"));

        return "home/index";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "About Us - Online Store");
        model.addAttribute("subtitle", "About Us");
        model.addAttribute("description", "This is an about page ...");
        model.addAttribute("author", "Developed by: Your Name");
        return "home/about";
    }

}