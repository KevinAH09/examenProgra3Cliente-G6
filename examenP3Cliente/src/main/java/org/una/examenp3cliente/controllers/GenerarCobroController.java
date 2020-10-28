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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import org.una.examenp3cliente.dtos.apiCobros.ClienteDTO;

/**
 * FXML Controller class
 *
 * @author cfugu
 */
public class GenerarCobroController implements Initializable {

    @FXML
    private JFXTextField txtBusqueda;
    @FXML
    private JFXComboBox<String> cmbBusqueda;
    @FXML
    private JFXButton btnFiltrar;
    @FXML
    private TableView<ClienteDTO> tableView;
    @FXML
    private JFXTextField txtId;
    @FXML
    private JFXTextField txtIdentificacion;
    @FXML
    private JFXTextField txtNombre;
    @FXML
    private JFXTextField txtTelefono;
    @FXML
    private JFXTextField txtDescripcion;
    @FXML
    private JFXTextField txtPeridiocidad;
    @FXML
    private JFXTextField txtMonto;
    @FXML
    private JFXComboBox<String> cmbTipoServicio;
    @FXML
    private JFXButton btnVolver;
    @FXML
    private JFXButton btnCobro;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onActionFiltrar(ActionEvent event) {
    }

    @FXML
    private void onActionVolver(ActionEvent event) {
    }

    @FXML
    private void onActionGenerar(ActionEvent event) {
    }
    
}
