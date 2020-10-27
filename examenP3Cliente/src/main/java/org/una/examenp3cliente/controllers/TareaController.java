/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.examenp3cliente.controllers;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.AnchorPane;
import org.una.examenp3cliente.dtos.ProyectoDTO;
import org.una.examenp3cliente.dtos.TareaDTO;

/**
 * FXML Controller class
 *
 * @author colo7
 */
public class TareaController extends Controller implements Initializable {

    @FXML
    private AnchorPane id;
    @FXML
    private TreeTableView<ProyectoDTO> treeTable;
    @FXML
    private TreeTableColumn<ProyectoDTO, String> treeColunm;
    
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        treeColunm.setCellValueFactory((TreeTableColumn.CellDataFeatures<ProyectoDTO,String> param) -> new ReadOnlyStringWrapper(param.getValue().getValue().getDescripcion()));
    }    

    @Override
    public void initialize() {
       
    }

    
}
