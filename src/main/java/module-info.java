module com.example.ticketsbus {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.jfoenix;
    requires org.controlsfx.controls;
    requires barcodes;
    requires kernel;
    requires layout;
    requires java.desktop;
    requires PdfInvoiceCreator;
    requires lombok;
    requires io;
    requires javafx.media;
    requires de.jensd.fx.glyphs.fontawesome;
    requires jfxtras.controls;
    requires javafx.web;
    requires org.jsoup;
    requires org.apache.pdfbox;


    opens com.example.ticketsbus to javafx.fxml;
    exports com.example.ticketsbus;
}