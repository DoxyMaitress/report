package org.example;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportViewer {

    public static void main(String[] args) {
        try {
            // Compilar el archivo principal jrxml y generar el archivo principal jasper
            JasperCompileManager.compileReportToFile("src/main/resources/Blank_A4_2.jrxml", "src/main/resources/Blank_A4.jasper");

            // Crear datos para el informe principal
            List<Map<String, Object>> dataSource = new ArrayList<>();
            dataSource.add(crearMiembro("Gomez", "Adams", "10/01/1960", "Transilvania", "000 Spooky Lane", "Westfield", "Transilvania", "555-5678", "555-1234"));
            dataSource.add(crearMiembro("Morticia", "Adams", "15/05/1965", "Transilvania", "000 Spooky Lane", "Westfield", "Transilvania", "555-4321", "555-1234"));
            dataSource.add(crearMiembro("Wednesday", "Adams", "13/07/1989", "Transilvania", "000 Spooky Lane", "Westfield", "Transilvania", "555-8765", "--"));
            dataSource.add(crearMiembro("Pugsley", "Adams", "20/09/1992", "Transilvania", "000 Spooky Lane", "Westfield", "Transilvania", "555-1234", "--"));
            dataSource.add(crearMiembro("Uncle Fester", "Adams", "05/03/1950", "Transilvania", "000 Spooky Lane", "Westfield", "Transilvania", "555-9876", "--"));

            // Llenar el informe utilizando el archivo principal .jasper generado
            JasperPrint jasperPrint = JasperFillManager.fillReport("src/main/resources/Blank_A4.jasper", null, new JRBeanCollectionDataSource(dataSource));

            // Exportar el informe a PDF
            JRPdfExporter pdfExporter = new JRPdfExporter();
            pdfExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            pdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput("Blank_A4_2.pdf"));
            SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
            pdfExporter.setConfiguration(configuration);
            pdfExporter.exportReport();

            System.out.println("Informe exportado exitosamente a Blank_A4_2.pdf");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Map<String, Object> crearMiembro(String apellido, String nombre, String fechaNacimiento, String lugarNacimiento, String direccion, String municipio, String provincia, String telefono, String movil) {
        Map<String, Object> miembro = new HashMap<>();
        miembro.put("apellido", apellido);
        miembro.put("nombre", nombre);
        miembro.put("fechaNacimiento", fechaNacimiento);
        miembro.put("lugarNacimiento", lugarNacimiento);
        miembro.put("direccion", direccion);
        miembro.put("municipio", municipio);
        miembro.put("provincia", provincia);
        miembro.put("telefono", telefono);
        miembro.put("movil", movil);
        return miembro;
    }
}
