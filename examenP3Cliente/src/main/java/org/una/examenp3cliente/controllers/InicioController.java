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
 * @author colo7
 */
public class InicioController extends Controller implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }

    @Override
    public void initialize() {
    }

    @FXML
    private void actionTareas(ActionEvent event) {
        FlowController.getInstance().goView("tarea/Tarea");
    }

    @FXML
    private void actionProvincias(ActionEvent event) {
        FlowController.getInstance().goView("Provincia/Provincia");
    }

    @FXML
    private void actionCobros(ActionEvent event) {
    }

}
