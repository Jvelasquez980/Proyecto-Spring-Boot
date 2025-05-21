// src/main/java/com/topicos/proyectospring/StartupRunner.java
package com.topicos.proyectospring;

import com.topicos.proyectospring.models.PCItem;
import com.topicos.proyectospring.report.ReportGenerator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StartupRunner implements CommandLineRunner {

    private final ReportGenerator reportGenerator;

    public StartupRunner(@Qualifier("pdfReport") ReportGenerator reportGenerator) {
        this.reportGenerator = reportGenerator;
    }

    @Override
    public void run(String... args) {
        // Simular algunos productos
        List<PCItem> items = List.of(
                new PCItem("Ryzen 5", "CPU", "AMD", 200.0, 10, null),
                new PCItem("RTX 3080", "GPU", "NVIDIA", 699.0, 5, null)
        );

        // Ejecutar la generación de reporte
        reportGenerator.generate(items);
    }
}
