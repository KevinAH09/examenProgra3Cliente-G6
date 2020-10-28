/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.examenp3cliente.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.una.examenp3cliente.dtos.apiProvincias.ProvinciaDTO;
import org.una.examenp3cliente.entitiesServices.apiProvincias.ProvinciaService;
import org.una.examenp3cliente.utils.FlowController;
import org.una.examenp3cliente.utils.Mensaje;

/**
 * FXML Controller class
 *
 * @author Bosco
 */
public class MantenimientoProvinciaController extends Controller implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private JFXTextField txtNombreProvincia;
    @FXML
    private JFXTextField txtCodigo;
    @FXML
    private JFXButton btnGuardar;
    
    ProvinciaDTO provincia;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void guardar(ActionEvent event) {

        if (!txtNombreProvincia.getText().isEmpty() && !txtCodigo.getText().isEmpty()) {
            provincia = new ProvinciaDTO();
            provincia.setNombreProvincia(txtNombreProvincia.getText());
            provincia.setCodigo(txtCodigo.getText());
            if (ProvinciaService.createProvincias(provincia) !=null) {
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar Provincia", ((Stage) txtNombreProvincia.getScene().getWindow()), "Se guardó correctamente");
            } else {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Error al guardar la Provincia", ((Stage) txtNombreProvincia.getScene().getWindow()), "No se guardó correctamente");
            }
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Error al crear Provincia", ((Stage) txtNombreProvincia.getScene().getWindow()), "Rellene los campos necesarios");
        }
    }

    @Override
    public void initialize() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void regresar(ActionEvent event) {
        FlowController.getInstance().goView("menuProvincia/MenuProvincia");
    }

}
