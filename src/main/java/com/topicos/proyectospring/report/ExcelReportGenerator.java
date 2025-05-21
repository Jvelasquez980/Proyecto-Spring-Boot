// src/main/java/com/topicos/proyectospring/report/ExcelReportGenerator.java
package com.topicos.proyectospring.report;

import com.topicos.proyectospring.models.PCItem;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("excelReport")
public class ExcelReportGenerator implements ReportGenerator {

    @Override
    public void generate(List<PCItem> items) {
        System.out.println("📊 Generando reporte en Excel...");
        for (PCItem item : items) {
            System.out.printf("Producto: %s, Precio: %.2f%n", item.getName(), item.getPrice());
        }
    }
}
