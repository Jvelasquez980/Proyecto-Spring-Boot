// src/main/java/com/topicos/proyectospring/report/ReportGenerator.java
package com.topicos.proyectospring.report;

import com.topicos.proyectospring.models.PCItem;

import java.util.List;

public interface ReportGenerator {
    void generate(List<PCItem> items);
}
