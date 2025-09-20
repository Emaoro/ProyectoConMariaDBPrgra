package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportClient {

    public static void main(String[] args) {
        // Tipo de reporte
        String reportType = "HTML"; // Por ahora solo HTML
        String filePath = "reporte.html";

        // Obtener datos desde MariaDB
        List<String> data = getDataFromDatabase();

        // Crear la fábrica de reportes HTML
        ReportFactory factory = new HTMLReportFactory();
        Report report = factory.createReport();

        // Generar reporte
        report.generateReport(filePath, data);
    }

    // Método para obtener datos desde MariaDB
    private static List<String> getDataFromDatabase() {
        List<String> data = new ArrayList<>();
        String url = "jdbc:mariadb://localhost:3306/prueba3"; // nombre de tu DB
        String user = "root";  // tu usuario
        String password = "Ema"; // tu contraseña

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM sales_data");

            while (rs.next()) {
                // Aquí puedes ajustar la columna que quieras mostrar
                int id = rs.getInt("id");
                String producto = rs.getString("producto");
                int cantidad = rs.getInt("cantidad");
                double precio = rs.getDouble("precio");

                // Crear un String representando la fila
                String row = id + " - " + producto + " - Cantidad: " + cantidad + " - Precio: " + precio;
                data.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }
}
