/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.arena.services;

import edu.arena.entities.Product;
import edu.arena.utils.DataBase;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 *
 * @author Foura
 */
public class OrderSrvc {

    private Connection con;
    private Statement ste;

    public OrderSrvc() {
    }

    public void exportTable() throws SQLException, FileNotFoundException {
        try {
            Connection con = DataBase.getInstance().getConnection();
            Statement stmt = con.createStatement();

            ResultSet query_set = stmt.executeQuery("SELECT * from orders");
            Document my_pdf_report = new Document();
            int min = 10000;
            int max = 99999;
            int random_id = (int) Math.floor(Math.random() * (max - min + 1) + min);
            PdfWriter.getInstance(my_pdf_report, new FileOutputStream("C:/Users/Foura/Documents/NetBeansProjects/arena-desktop/src/edu/arena/uploads/reports/orders/report" + random_id + ".pdf"));

            my_pdf_report.open();
            my_pdf_report.add(new Paragraph("                                      Orders report", FontFactory.getFont("Arial", 20)));
            my_pdf_report.add(new Paragraph("  "));
            my_pdf_report.add(new Paragraph("  "));

            //we have four columns in our table
            PdfPTable my_report_table = new PdfPTable(4);

            //create a cell object
            PdfPCell table_cell;
            table_cell = new PdfPCell(new Phrase("Product"));
            my_report_table.addCell(table_cell);
            table_cell = new PdfPCell(new Phrase("User"));
            my_report_table.addCell(table_cell);
            table_cell = new PdfPCell(new Phrase("Quantity"));
            my_report_table.addCell(table_cell);
            table_cell = new PdfPCell(new Phrase("Date"));
            my_report_table.addCell(table_cell);

            while (query_set.next()) {
                ProductCRUD pcrud = new ProductCRUD();
                Product prod = pcrud.showProduct(query_set.getInt("idProduct"));
                String prodName = prod.getName();
                table_cell = new PdfPCell(new Phrase(prodName));
                my_report_table.addCell(table_cell);
                String user = "fourat";
                table_cell = new PdfPCell(new Phrase(user));
                my_report_table.addCell(table_cell);
                String qty = query_set.getString("productQty");
                table_cell = new PdfPCell(new Phrase(qty));
                my_report_table.addCell(table_cell);
                String date = query_set.getString("createdAt");
                table_cell = new PdfPCell(new Phrase(date));
                my_report_table.addCell(table_cell);
            }
            Notifications notifications = Notifications.create();
            notifications.text("Report generated");
            notifications.title("Notification");
            notifications.hideAfter(Duration.seconds(4));
            notifications.darkStyle();
            notifications.show();
            my_pdf_report.addTitle("Orders report");
            my_pdf_report.add(my_report_table);

            my_pdf_report.close();

        } catch (DocumentException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
