package com.topicos.proyectospring.controllers;

import com.topicos.proyectospring.models.Client;
import com.topicos.proyectospring.services.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


import jakarta.validation.Valid;

@Controller
public class AuthController {

    private final ClientService clientService;

    public AuthController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("client", new Client());
        return "auth/register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid Client client, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "auth/register";
        }
        clientService.registerUser(client.getUsername(), client.getEmail(), client.getName(),
                client.getLastName(), client.getPassword(), client.getPhone(), "ROLE_USER");
        return "redirect:/login";
    }
}
