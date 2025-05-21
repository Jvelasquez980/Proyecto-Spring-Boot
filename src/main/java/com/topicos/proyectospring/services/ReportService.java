// src/main/java/com/topicos/proyectospring/services/ReportService.java
package com.topicos.proyectospring.services;

import com.topicos.proyectospring.models.PCItem;
import com.topicos.proyectospring.report.ReportGenerator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    private final ReportGenerator reportGenerator;

    public ReportService(@Qualifier("pdfReport") ReportGenerator reportGenerator) {
        this.reportGenerator = reportGenerator;
    }

    public void generateReport(List<PCItem> items) {
        reportGenerator.generate(items);
    }
}
