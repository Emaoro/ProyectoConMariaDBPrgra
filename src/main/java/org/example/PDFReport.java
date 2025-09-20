package org.example;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.util.List;

public class PDFReport implements Report {
    @Override
    public void generateReport(String filePath, Object data) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();
            List<String> list = (List<String>) data;
            for (String item : list) {
                document.add(new Paragraph(item));
            }
            document.close();
            System.out.println("PDF generado en: " + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
