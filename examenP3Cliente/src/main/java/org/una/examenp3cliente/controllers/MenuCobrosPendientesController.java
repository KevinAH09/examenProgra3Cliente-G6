/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.examenp3cliente.controllers;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.una.examenp3cliente.utils.FlowController;

/**
 * FXML Controller class
 *
 * @author cfugu
 */
public class MenuCobrosPendientesController implements Initializable {

    @FXML
    private JFXButton btnVisualizar;
    @FXML
    private JFXButton btnGenerar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void actionGenerarCobros(ActionEvent event) {
        FlowController.getInstance().goView("tarea/Tarea");
    }

    @FXML
    private void actionVisualizarCobros(ActionEvent event) {
        FlowController.getInstance().goView("tarea/Tarea");
    }
    
}
