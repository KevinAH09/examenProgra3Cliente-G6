/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.examenp3cliente.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.una.examenp3cliente.utils.FlowController;

/**
 * FXML Controller class
 *
 * @author Bosco
 */
public class MenuProvinciaController extends Controller implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void irProvincia(ActionEvent event) {
        FlowController.getInstance().goView("mantenimientoProvincia/MantenimientoProvincia");
    }

    @FXML
    private void irCantone(ActionEvent event) {
        FlowController.getInstance().goView("Cantones/MantenimientoCantones");
    }

    @FXML
    private void irDistritos(ActionEvent event) {
    }

    @FXML
    private void irUnidades(ActionEvent event) {
    }

    @FXML
    private void irMostrar(ActionEvent event) {
    }

    @FXML
    private void irMenuPrincipal(ActionEvent event) {
    }

    @Override
    public void initialize() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
