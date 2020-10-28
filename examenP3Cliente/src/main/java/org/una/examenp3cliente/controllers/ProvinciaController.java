/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.examenp3cliente.controllers;

import com.jfoenix.controls.JFXTreeView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;

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
        TreeItem<String> root = new TreeItem<>("Provincias");
        root.setExpanded(true);
        root.getChildren().add(new TreeItem<>("Cantones"));
        treeView.setRoot(root);
    }    

    @Override
    public void initialize() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
