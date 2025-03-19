package com.topicos.proyectospring.services;

import com.topicos.proyectospring.models.PCItem;
import com.topicos.proyectospring.repositories.PCItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PCItemService {
    private final PCItemRepository pcItemRepository;

    public PCItemService(PCItemRepository pcItemRepository) {
        this.pcItemRepository = pcItemRepository;
    }

    public List<PCItem> getAllItems() {
        return pcItemRepository.findAll();
    }

    public void saveItem(PCItem pcItem) {
        pcItemRepository.save(pcItem);
    }

    public void deleteById(Long id) {
        pcItemRepository.deleteById(id);
    }

    public Optional<PCItem> getItemById(Long id) {
        return pcItemRepository.findById(id);
    }

}