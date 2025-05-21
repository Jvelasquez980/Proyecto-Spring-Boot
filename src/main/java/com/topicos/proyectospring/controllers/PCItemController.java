package com.topicos.proyectospring.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.topicos.proyectospring.models.PCItem;
import com.topicos.proyectospring.services.PCItemService;
import com.topicos.proyectospring.services.ReportService;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.File;
import java.util.*;

@Controller
@RequestMapping("/items")
public class PCItemController {

    private final PCItemService pcItemService;
    private final ObjectMapper objectMapper;

    @Autowired
    private ReportService reportService; // 👈 Inyectamos el servicio que genera PDF

    public PCItemController(PCItemService pcItemService, ObjectMapper objectMapper) {
        this.pcItemService = pcItemService;
        this.objectMapper = objectMapper;
    }

    @GetMapping
    public String listItems(Model model) {
        model.addAttribute("items", pcItemService.getAllItems());
        return "items/items";
    }

    @GetMapping("/new")
    public String newItemForm(Model model) {
        model.addAttribute("item", new PCItem());
        return "items/item-form";
    }

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

    @GetMapping("/compare")
    public String compareForm(Model model) {
        model.addAttribute("items", pcItemService.getAllItems());
        return "items/compare-form";
    }

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

    // 🔥 Generar y descargar PDF
    @GetMapping("/report/pdf")
    public void downloadPdfReport(HttpServletResponse response) {
        try {
            // 🔄 Generar el PDF antes de servirlo
            List<PCItem> items = pcItemService.getAllItems();
            reportService.generateReport(items);

            File file = new File("reports/products.pdf");

            if (!file.exists()) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("El archivo PDF no existe.");
                return;
            }

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=products.pdf");
            response.setContentLength((int) file.length());

            FileInputStream inputStream = new FileInputStream(file);
            ServletOutputStream outputStream = response.getOutputStream();

            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            inputStream.close();
            outputStream.flush();
            outputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
            try {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("Error al generar o descargar el PDF.");
            } catch (Exception ignored) {}
        }
    }

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
