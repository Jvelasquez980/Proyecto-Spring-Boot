package com.topicos.proyectospring.controllers;

import com.topicos.proyectospring.models.Client;
import com.topicos.proyectospring.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/clients")
    public String index(Model model) {
        List<Client> clients = clientRepository.findAll();
        model.addAttribute("title", "Clients - Online Store");
        model.addAttribute("subtitle", "List of clients");
        model.addAttribute("clients", clients);
        return "client/index";
    }

    @GetMapping("/clients/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        Client client = clientRepository.findById(id).orElse(null);
        if (client == null){
            return "redirect:/clients/error";
        }
        model.addAttribute("title", client.getName() + " - Online Store");
        model.addAttribute("subtitle", client.getName() + " - Client information");
        model.addAttribute("client", client);
        return "client/show";
    }

    @GetMapping("/clients/create")
    public String createClientForm(Model model) {
        model.addAttribute("client", new Client());
        return "client/create";
    }

    @PostMapping("/clients")
    public String save(Client client) {
        // Validaciones mínimas
        if (client.getName() == null || client.getEmail().isEmpty() ||
         client.getLastName() == null || client.getLastName().isEmpty() ||
         client.getPassword() == null || client.getPassword().isEmpty() ||
         client.getPhone() == null || client.getEmail() == null ||
         client.getEmail().isEmpty() )  {
            throw new RuntimeException("All the information is requiered");
        }
        clientRepository.save(client);
        return "redirect:/clients/created";
    }
    @PostMapping("/clients/{id}/delete") // No encontré la manera de hacerlo con el metodo delete, me decia que no se permitia esta accion
    public String delete(@PathVariable("id") Long id, Model model) {
        clientRepository.findById(id).orElseThrow(() -> new RuntimeException("Client not found"));
        clientRepository.deleteById(id);
       
        return "redirect:/clients";
    }
    @GetMapping("/clients/created")
    public String created(Model model) {
        model.addAttribute("title", "Client created!");
        return "client/created";
    }
    @GetMapping("/clients/error")
    public String clientError(Model model) {
        model.addAttribute("title", "Client not found");
        return "client/noclient";
    }
}
