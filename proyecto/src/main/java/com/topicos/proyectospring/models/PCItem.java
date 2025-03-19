package com.topicos.proyectospring.models;

import com.fasterxml.jackson.databind.JsonNode;
import com.topicos.proyectospring.converters.JsonNodeConverter;
import jakarta.persistence.*;

@Entity
@Table(name = "pc_item")
public class PCItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String category;
    private String brand;
    private Double price;
    private int stock;

    @Convert(converter = JsonNodeConverter.class) // 🟢 Usa el convertidor de JsonNode
    @Column(columnDefinition = "json")
    private JsonNode performance;

    public PCItem() {}

    public PCItem(String name, String category, String brand, Double price, int stock, JsonNode performance) {
        this.name = name;
        this.category = category;
        this.brand = brand;
        this.price = price;
        this.stock = stock;
        this.performance = performance;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public JsonNode getPerformance() { return performance; }
    public void setPerformance(JsonNode performance) { this.performance = performance; }
}
