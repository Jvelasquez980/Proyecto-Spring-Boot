// src/test/java/com/topicos/proyectospring/models/PCItemTest.java
package com.topicos.proyectospring.models;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PCItemTest {

    @Test
    public void testPCItemCreation() throws Exception {
        // Preparar datos de prueba
        String name = "Ryzen 7 5800X";
        String category = "CPU";
        String brand = "AMD";
        Double price = 329.99;
        int stock = 10;

        // Crear un JSON de rendimiento
        String jsonString = "{ \"single_core\": 1500, \"multi_core\": 10000 }";
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode performanceNode = objectMapper.readTree(jsonString);

        // Crear objeto PCItem
        PCItem item = new PCItem(name, category, brand, price, stock, performanceNode);

        // Verificaciones
        assertNull(item.getId()); // No se ha asignado ID aún
        assertEquals(name, item.getName());
        assertEquals(category, item.getCategory());
        assertEquals(brand, item.getBrand());
        assertEquals(price, item.getPrice());
        assertEquals(stock, item.getStock());

        assertNotNull(item.getPerformance());
        assertEquals(1500, item.getPerformance().get("single_core").asInt());
        assertEquals(10000, item.getPerformance().get("multi_core").asInt());
    }
}
