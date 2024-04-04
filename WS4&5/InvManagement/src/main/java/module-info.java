module org.example.invmanagement {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires jakarta.xml.bind;
    requires java.logging;
    requires java.sql;

    opens org.example.invmanagement to javafx.fxml, jakarta.xml.bind;
    exports org.example.invmanagement;
}