// src/main/java/com/topicos/proyectospring/report/PdfReportGenerator.java
package com.topicos.proyectospring.report;

import com.topicos.proyectospring.models.PCItem;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("pdfReport")
public class PdfReportGenerator implements ReportGenerator {

    @Override
    public void generate(List<PCItem> items) {
        System.out.println("📄 Generando reporte en PDF...");
        for (PCItem item : items) {
            System.out.printf("Producto: %s, Precio: %.2f%n", item.getName(), item.getPrice());
        }
    }
}
