module com.example.esalaf {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    requires java.sql;

    opens  com.exemple.model;

   // opens com.example.model ;
    exports com.example.esalaf;
    opens com.example.esalaf;
    exports com.exemple.model;
}