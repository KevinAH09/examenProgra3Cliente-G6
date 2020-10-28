module org.una.examenp3cliente {
    
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires com.google.gson;
    requires java.base;
    requires java.xml.bind;
   

    opens org.una.examenp3cliente.controllers to javafx.fxml,javafx.controls, com.jfoenix;
    exports org.una.examenp3cliente to javafx.graphics;
    exports org.una.examenp3cliente.controllers;
    
    
}
