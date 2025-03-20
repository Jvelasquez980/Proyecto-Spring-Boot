package com.topicos.proyectospring.services;

import com.fasterxml.jackson.databind.JsonNode;
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

    public PCItem compareItems(Long itemId1, Long itemId2) {
        PCItem item1 = pcItemRepository.findById(itemId1)
                .orElseThrow(() -> new RuntimeException("Item 1 no encontrado"));
        PCItem item2 = pcItemRepository.findById(itemId2)
                .orElseThrow(() -> new RuntimeException("Item 2 no encontrado"));

        if (!item1.getCategory().equals(item2.getCategory())) {
            throw new RuntimeException("Los ítems deben ser de la misma categoría");
        }

        JsonNode performance1 = item1.getPerformance();
        JsonNode performance2 = item2.getPerformance();

        int score1 = evaluatePerformance(performance1, item1.getCategory());
        int score2 = evaluatePerformance(performance2, item2.getCategory());

        return score1 >= score2 ? item1 : item2;
    }

    private int evaluatePerformance(JsonNode performance, String category) {
        int score = 0;
        if (performance == null)
            return 0;

        if (category.equals("CPU")) {
            score -= performance.get("rankPopularity").asInt(0);
            score += performance.get("costEfficiency").asInt(0);
            score += performance.get("energyEfficiency").asInt(0);
            score += performance.get("threads").asInt(0);
            score += performance.get("cores").asInt(0);
            score += performance.get("baseFrequency").asInt(0);
            score += performance.get("maxFrequency").asInt(0);
        } else if (category.equals("GPU")) {
            score -= performance.get("rankPerformance").asInt(0);
            score += performance.get("rankPopularity").asInt(0);
            score += performance.get("costEfficiency").asInt(0);
            score += performance.get("energyEfficiency").asInt(0);
            score += performance.get("coreFrequency").asInt(0);
            score += performance.get("boostFrequency").asInt(0);
            score += performance.get("tdp").asInt(0);
        }

        return score;
    }
}
