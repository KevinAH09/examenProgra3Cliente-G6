/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.examenp3cliente.controllers;

import com.jfoenix.controls.JFXButton;
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
import org.una.examenp3cliente.dtos.apiProvincias.ProvinciaDTO;
import org.una.examenp3cliente.entitiesServices.apiProvincias.CantonService;
import org.una.examenp3cliente.entitiesServices.apiProvincias.ProvinciaService;
import org.una.examenp3cliente.utils.FlowController;
import org.una.examenp3cliente.utils.Mensaje;

/**
 * FXML Controller class
 *
 * @author Bosco
 */
public class MantenimientoCantonesController extends Controller implements Initializable {

    @FXML
    private JFXTextField txtNombreCanton;
    @FXML
    private JFXTextField txtCodigo;
    @FXML
    private JFXComboBox<ProvinciaDTO> combProvincia;
    @FXML
    private JFXButton btnGuardar;
    public List<ProvinciaDTO> provinciaList = new ArrayList<ProvinciaDTO>();
    CantonDTO canton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        provinciaList = ProvinciaService.allProvincia();
        combProvincia.setItems(FXCollections.observableArrayList(provinciaList));
        
        
    }    

    @FXML
    private void guardar(ActionEvent event) {
        
        if (!txtNombreCanton.getText().isEmpty() && !txtCodigo.getText().isEmpty() && !combProvincia.getValue().getNombreProvincia().isEmpty()) {
            canton = new CantonDTO();
            canton.setNombreCanton(txtNombreCanton.getText());
            canton.setCodigo(txtCodigo.getText());
            canton.setProvinciaId(combProvincia.getValue());
            if (CantonService.createCantones(canton) !=null) {
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar Cantón", ((Stage) txtNombreCanton.getScene().getWindow()), "Se guardó correctamente");
            } else {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Error al guardar el cantón", ((Stage) txtNombreCanton.getScene().getWindow()), "No se guardó correctamente");
            }
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Error al crear cantón", ((Stage) txtNombreCanton.getScene().getWindow()), "Rellene los campos necesarios");
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
