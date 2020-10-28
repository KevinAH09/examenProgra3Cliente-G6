/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.examenp3cliente.controllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.una.examenp3cliente.dtos.apiProvincias.CantonDTO;
import org.una.examenp3cliente.dtos.apiProvincias.DistritoDTO;
import org.una.examenp3cliente.entitiesServices.apiProvincias.CantonService;
import org.una.examenp3cliente.entitiesServices.apiProvincias.DistritoService;
import org.una.examenp3cliente.utils.FlowController;
import org.una.examenp3cliente.utils.Mensaje;

/**
 * FXML Controller class
 *
 * @author Bosco
 */
public class MantenimientoDistritosController extends Controller implements Initializable {

    @FXML
    private JFXTextField txtNombreDistrito;
    @FXML
    private JFXTextField txtCodigo;
    @FXML
    private JFXComboBox<CantonDTO> combCantones;
    public List<CantonDTO> cantonList = new ArrayList<CantonDTO>();
    DistritoDTO distrito;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cantonList = CantonService.allCanton();
        combCantones.setItems(FXCollections.observableArrayList(cantonList));
    }    

    @FXML
    private void regresar(ActionEvent event) {
        FlowController.getInstance().goView("menuProvincia/MenuProvincia");
    }

    @FXML
    private void guardar(ActionEvent event) {
        if (!txtNombreDistrito.getText().isEmpty() && !txtCodigo.getText().isEmpty() && !combCantones.getValue().getNombreCanton().isEmpty()) {
            distrito = new DistritoDTO();
            distrito.setNombreDistrito(txtNombreDistrito.getText());
            distrito.setCodigo(txtCodigo.getText());
            distrito.setCantonId(combCantones.getValue());
            if (DistritoService.createDistrito(distrito) !=null) {
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar Distrito", ((Stage) txtNombreDistrito.getScene().getWindow()), "Se guardó correctamente");
            } else {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Error al guardar el Distrito", ((Stage) txtNombreDistrito.getScene().getWindow()), "No se guardó correctamente");
            }
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Error al crear Distrito", ((Stage) txtNombreDistrito.getScene().getWindow()), "Rellene los campos necesarios");
        }
    }

    @Override
    public void initialize() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
