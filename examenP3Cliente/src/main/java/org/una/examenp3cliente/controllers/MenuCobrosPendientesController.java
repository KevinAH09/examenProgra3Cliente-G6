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
public class MenuCobrosPendientesController extends Controller implements Initializable {

    @FXML
    private JFXButton btnVisualizar;
    @FXML
    private JFXButton btnGenerar;
    @FXML
    private JFXButton btnRegresar;

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

    @FXML
    private void actionVolverMenu(ActionEvent event) {
        FlowController.getInstance().goView("inicio/Inicio");
    }

    @Override
    public void initialize() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
