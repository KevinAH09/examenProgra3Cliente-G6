module org.una.examenp3cliente {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.una.examenp3cliente to javafx.fxml;
    exports org.una.examenp3cliente;
}
