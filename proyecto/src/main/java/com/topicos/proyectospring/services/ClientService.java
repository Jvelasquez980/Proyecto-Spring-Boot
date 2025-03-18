package com.topicos.proyectospring.services;

import com.topicos.proyectospring.models.Client;
import com.topicos.proyectospring.repositories.ClientRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.topicos.proyectospring.models.PayMethod;
import com.topicos.proyectospring.repositories.PayMethodRepository;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final PayMethodRepository payMethodRepository;
    private final PasswordEncoder passwordEncoder;

    public ClientService(ClientRepository clientRepository, PayMethodRepository payMethodRepository, PasswordEncoder passwordEncoder) {
        this.clientRepository = clientRepository;
        this.payMethodRepository = payMethodRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Registrar usuario
    public Client registerUser(String username, String email, String name, String lastName, String password, Long phone, String role) {
        String encodedPassword = passwordEncoder.encode(password);
        Client client = new Client(username, email, name, lastName, encodedPassword, phone, role);
        return clientRepository.save(client);
    }

    // Obtener métodos de pago de un cliente
    public List<PayMethod> getPayMethods(Long clientId) {
        Optional<Client> client = clientRepository.findById(clientId);
        return client.map(Client::getPayMethods).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }

    // Agregar un método de pago a un cliente
    public PayMethod addPayMethod(Long clientId, PayMethod payMethod) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        payMethod.setClient(client);
        return payMethodRepository.save(payMethod);
    }

    // Eliminar un método de pago
    public void deletePayMethod(Long payMethodId) {
        if (!payMethodRepository.existsById(payMethodId)) {
            throw new RuntimeException("Método de pago no encontrado");
        }
        payMethodRepository.deleteById(payMethodId);
    }
}
