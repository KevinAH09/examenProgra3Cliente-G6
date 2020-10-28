/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.examenp3cliente.controllers;

import com.jfoenix.controls.JFXTreeView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseEvent;
import org.una.examenp3cliente.utils.FlowController;

/**
 * FXML Controller class
 *
 * @author Bosco
 */
public class ProvinciaController extends Controller implements Initializable {

    @FXML
    private JFXTreeView<String> treeView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        actionAerolineaClick
                ();
        TreeItem<String> root = new TreeItem<>("Provincias");
        root.setExpanded(true);
        root.getChildren().add(new TreeItem<>("Cantones"));
        treeView.setRoot(root);
    }    
    
    private void actionAerolineaClick() {

        treeView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                
                    if (mouseEvent.getClickCount() == 2 && treeView.selectionModelProperty().get().getSelectedItem() != null) {
                        TreeItem<String> aerolinea =  treeView.selectionModelProperty().get().getSelectedItem();
                        FlowController.getInstance().goView("inicio/Inicio");
                    }

                
            }
        });
    }
    
    @Override
    public void initialize() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
