package com.topicos.proyectospring.services;

import com.topicos.proyectospring.models.Client;
import com.topicos.proyectospring.repositories.ClientRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    public ClientService(ClientRepository clientRepository, PasswordEncoder passwordEncoder) {
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Client registerUser(String username, String email, String name, String lastName, String password, Long phone, String role) {
        String encodedPassword = passwordEncoder.encode(password);
        Client client = new Client(username, email, name, lastName, encodedPassword, phone, role);
        return clientRepository.save(client);
    }
}
