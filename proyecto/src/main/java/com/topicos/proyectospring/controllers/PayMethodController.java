package com.topicos.proyectospring.controllers;

import com.topicos.proyectospring.models.PayMethod;
import com.topicos.proyectospring.models.Client;
import com.topicos.proyectospring.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pay-methods")
@SessionAttributes("client") // Guarda el cliente autenticado en la sesión
public class PayMethodController {

    @Autowired
    private ClientService clientService;

    // ✅ Mostrar solo los métodos de pago del usuario autenticado
    @GetMapping
    public String showPayMethods(Model model, @SessionAttribute("client") Client client) {
        List<PayMethod> payMethods = clientService.getPayMethods(client.getId());
        model.addAttribute("payMethods", payMethods);
        return "payMethod/pay-methods";
    }

    // ✅ Mostrar formulario para agregar método de pago
    @GetMapping("/new")
    public String showPayMethodForm(Model model) {
        model.addAttribute("payMethod", new PayMethod());
        return "payMethod/pay-method-form";
    }

    // ✅ Guardar método de pago asociado al usuario autenticado
    @PostMapping
    public String addPayMethod(@ModelAttribute PayMethod payMethod, @SessionAttribute("client") Client client) {
        clientService.addPayMethod(client.getId(), payMethod);
        return "redirect:/pay-methods";
    }

    // ✅ Eliminar un método de pago (solo si pertenece al usuario autenticado)
    @GetMapping("/delete/{id}")
    public String deletePayMethod(@PathVariable Long id, @SessionAttribute("client") Client client) {
        boolean ownsMethod = clientService.getPayMethods(client.getId()).stream()
                .anyMatch(method -> method.getId().equals(id));

        if (ownsMethod) {
            clientService.deletePayMethod(id);
        }

        return "redirect:/pay-methods";
    }
}
