package org.example;

import java.io.FileWriter;
import java.util.List;

public class HTMLReport implements Report {
    @Override
    public void generateReport(String filePath, Object data) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("<html><body><ul>");
            List<String> list = (List<String>) data;
            for (String item : list) {
                writer.write("<li>" + item + "</li>");
            }
            writer.write("</ul></body></html>");
            System.out.println("HTML generado en: " + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
