package com.topicos.proyectospring.services;

import com.topicos.proyectospring.models.PayMethod;
import com.topicos.proyectospring.repositories.PayMethodRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PayMethodService {
    private final PayMethodRepository payMethodRepository;

    public PayMethodService(PayMethodRepository payMethodRepository) {
        this.payMethodRepository = payMethodRepository;
    }

    public List<PayMethod> getPayMethodsByClient(Long clientId) {
        return payMethodRepository.findByClientId(clientId);
    }
}
