module org.una.examenp3cliente {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires com.google.gson;
    requires java.base;

    opens org.una.examenp3cliente to javafx.fxml;
    opens org.una.examenp3cliente.controllers to javafx.fxml;
    exports org.una.examenp3cliente.controllers;
    exports org.una.examenp3cliente;
}
