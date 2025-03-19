package com.topicos.proyectospring.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.topicos.proyectospring.models.PCItem;
import com.topicos.proyectospring.services.PCItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.Optional;


import java.util.Map;


@Controller
@RequestMapping("/items")
public class PCItemController {
    private final PCItemService pcItemService;
    private final ObjectMapper objectMapper; // 🟢 Para convertir String a JsonNode

    public PCItemController(PCItemService pcItemService, ObjectMapper objectMapper) {
        this.pcItemService = pcItemService;
        this.objectMapper = objectMapper;
    }

    @GetMapping
    public String listItems(Model model) {
        model.addAttribute("items", pcItemService.getAllItems());
        return "/items/items";
    }

    @GetMapping("/new")
    public String newItemForm(Model model) {
        model.addAttribute("item", new PCItem());
        return "/items/item-form";
    }

    @PostMapping
    public String saveItem(@ModelAttribute PCItem item, @RequestParam("performanceData") String performanceData) {
        try {
            JsonNode performanceJson = objectMapper.readTree(performanceData); // 🔥 Convertir String a JsonNode
            item.setPerformance(performanceJson);
        } catch (Exception e) {
            throw new RuntimeException("Error al convertir performanceData a JSON", e);
        }

        pcItemService.saveItem(item);
        return "redirect:/items";
    }

    @PostMapping("/delete/{id}")
    public String deleteItem(@PathVariable Long id) {
        pcItemService.deleteById(id);
        return "redirect:/items";
    }

    @GetMapping("/{id}")
    public String viewItem(@PathVariable Long id, Model model) {
        Optional<PCItem> itemOptional = pcItemService.getItemById(id);
    
        if (itemOptional.isPresent()) {
            PCItem item = itemOptional.get();
            model.addAttribute("item", item);
    
            // 🔥 Convertir performance a un Map accesible para Thymeleaf
            if (item.getPerformance() != null) {
                ObjectMapper objectMapper = new ObjectMapper();
                Map<String, Object> performanceMap = objectMapper.convertValue(item.getPerformance(), Map.class);
                model.addAttribute("performance", performanceMap);
            } else {
                model.addAttribute("performance", null);
            }
    
            return "/items/show";
        }
    
        return "redirect:/items";
    }
}
