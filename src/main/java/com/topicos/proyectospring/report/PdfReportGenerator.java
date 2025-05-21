package com.topicos.proyectospring.report;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import com.topicos.proyectospring.models.PCItem;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

@Component("pdfReport")
public class PdfReportGenerator implements com.topicos.proyectospring.report.ReportGenerator {

    @Override
    public void generate(List<PCItem> items) {
        try {
            // Crear carpeta /reports si no existe
            File reportsDir = new File("reports");
            if (!reportsDir.exists()) {
                reportsDir.mkdirs(); // 🔥 crearla
                System.out.println("📂 Carpeta 'reports' creada.");
            }

            String outputPath = "reports/products.pdf";
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(outputPath));

            document.open();

            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            document.add(new Paragraph("📋 Reporte de Productos", titleFont));
            document.add(new Paragraph(" ")); // Espacio

            for (PCItem item : items) {
                String info = String.format("Producto: %s | Marca: %s | Precio: $%.2f | Stock: %d",
                        item.getName(), item.getBrand(), item.getPrice(), item.getStock());
                document.add(new Paragraph(info));
            }

            document.close();
            System.out.println("✅ PDF generado exitosamente en: " + outputPath);

        } catch (Exception e) {
            System.err.println("❌ Error al generar el PDF: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
