package com.topicos.proyectospring.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.topicos.proyectospring.models.PCItem;
import com.topicos.proyectospring.services.PCItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/items")
public class PCItemController {
    private final PCItemService pcItemService;
    private final ObjectMapper objectMapper;

    public PCItemController(PCItemService pcItemService, ObjectMapper objectMapper) {
        this.pcItemService = pcItemService;
        this.objectMapper = objectMapper;
    }

    // Página principal: listar ítems
    @GetMapping
    public String listItems(Model model) {
        model.addAttribute("items", pcItemService.getAllItems());
        return "items/items";
    }

    // Formulario para nuevo ítem
    @GetMapping("/new")
    public String newItemForm(Model model) {
        model.addAttribute("item", new PCItem());
        return "items/item-form";
    }

    // Guardar ítem nuevo o actualizado
    @PostMapping
    public String saveItem(@ModelAttribute PCItem item, @RequestParam("performanceData") String performanceData) {
        try {
            JsonNode performanceJson = objectMapper.readTree(performanceData);
            item.setPerformance(performanceJson);
        } catch (Exception e) {
            throw new RuntimeException("Error al convertir performanceData a JSON", e);
        }

        pcItemService.saveItem(item);
        return "redirect:/items";
    }

    // Eliminar ítem
    @PostMapping("/delete/{id}")
    public String deleteItem(@PathVariable Long id) {
        pcItemService.deleteById(id);
        return "redirect:/items";
    }

    // Ver detalle del ítem
    @GetMapping("/{id}")
    public String viewItem(@PathVariable Long id, Model model) {
        Optional<PCItem> itemOptional = pcItemService.getItemById(id);

        if (itemOptional.isPresent()) {
            PCItem item = itemOptional.get();
            model.addAttribute("item", item);

            if (item.getPerformance() != null) {
                Map<String, Object> performanceMap = objectMapper.convertValue(item.getPerformance(), Map.class);
                model.addAttribute("performance", performanceMap);
            } else {
                model.addAttribute("performance", null);
            }

            return "items/show";
        }

        return "redirect:/items";
    }

    // Formulario para comparar dos ítems
    @GetMapping("/compare")
    public String compareForm(Model model) {
        model.addAttribute("items", pcItemService.getAllItems());
        return "items/compare-form";
    }

    // Procesar comparación
    @PostMapping("/compare")
    public String compareItems(@RequestParam Long item1, @RequestParam Long item2, Model model) {
        try {
            PCItem bestItem = pcItemService.compareItems(item1, item2);
            model.addAttribute("bestItem", bestItem);
            return "items/comparison-result";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "items/compare-form";
        }
    }

    // 🔥 NUEVO: Endpoint JSON con productos en stock
    @GetMapping("/api/stock")
    @ResponseBody
    public List<Map<String, Object>> getAvailableItemsJson() {
        List<PCItem> items = pcItemService.getAllItems()
                .stream()
                .filter(item -> item.getStock() > 0)
                .toList();

        return items.stream().map(item -> Map.of(
                "id", item.getId(),
                "name", item.getName(),
                "category", item.getCategory(),
                "brand", item.getBrand(),
                "price", item.getPrice(),
                "stock", item.getStock(),
                "performance", item.getPerformance(),
                "viewUrl", "http://localhost:8080/items/" + item.getId()
        )).toList();
    }
}
