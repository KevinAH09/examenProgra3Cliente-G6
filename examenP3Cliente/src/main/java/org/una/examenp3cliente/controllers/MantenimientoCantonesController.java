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
import org.una.examenp3cliente.dtos.apiProvincias.ProvinciaDTO;
import org.una.examenp3cliente.entitiesServices.apiProvincias.ProvinciaService;

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
    }

    @Override
    public void initialize() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
