package com.topicos.proyectospring.controllers;

import com.topicos.proyectospring.models.Client;
import com.topicos.proyectospring.models.PayMethod;
import com.topicos.proyectospring.services.ClientService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pay-methods")
public class PayMethodController {


    private final ClientService clientService;

    public PayMethodController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public String showPayMethods(HttpSession session, Model model) {
        Client client = (Client) session.getAttribute("client");

        if (client == null) {
            return "redirect:/login";
        }

        List<PayMethod> payMethods = clientService.getPayMethods(client.getId());
        model.addAttribute("payMethods", payMethods);
        return "payMethod/pay-methods";
    }

    @GetMapping("/new")
    public String showPayMethodForm(Model model) {
        model.addAttribute("payMethod", new PayMethod());
        return "payMethod/pay-method-form";
    }

    @PostMapping
    public String addPayMethod(HttpSession session, @ModelAttribute PayMethod payMethod) {
        Client client = (Client) session.getAttribute("client");
    
        if (client == null) {
            return "redirect:/login";
        }
    
    
        payMethod.setClient(client);
        clientService.addPayMethod(client.getId(), payMethod);
    
        return "redirect:/pay-methods";
    }

    @GetMapping("/delete/{id}")
    public String deletePayMethod(@PathVariable Long id, HttpSession session) {
        Client client = (Client) session.getAttribute("client");

        if (client == null) {
            return "redirect:/login";
        }

        clientService.deletePayMethod(id);
        return "redirect:/pay-methods";
    }
}
