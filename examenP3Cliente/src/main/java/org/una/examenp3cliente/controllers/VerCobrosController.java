/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.examenp3cliente.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTreeView;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.HBox;
import org.una.examenp3cliente.dtos.apiCobros.ClienteDTO;
import org.una.examenp3cliente.entitiesServices.apiCobros.ClienteService;

/**
 * FXML Controller class
 *
 * @author cfugu
 */
public class VerCobrosController extends Controller implements Initializable {

    @FXML
    private HBox txtBusqueda;
    @FXML
    private JFXComboBox<String> cmbBusqueda;
    @FXML
    private JFXButton btnFiltrar;
    @FXML
    private JFXTreeView<String> treeview;
    public List<ClienteDTO> clientesList = new ArrayList<ClienteDTO>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        llenarTree();
    }

    @FXML
    private void onActionFiltrar(ActionEvent event) {
    }

    private void llenarTree() {
        TreeItem<String> root = new TreeItem<>();
        treeview.setRoot(root);
        TreeItem<String> inicio = new TreeItem<>("Cliente");
        root.getChildren().add(inicio);
        clientesList = ClienteService.allCliente();
        for (ClienteDTO clienteDTO : clientesList) {
            String title = clienteDTO.getNombre();
            TreeItem<String> item = new TreeItem<>(title);
            inicio.getChildren().add(item);

            TreeItem<String> item2 = new TreeItem<>("Información");
            item.getChildren().add(item2);

            item2.getChildren().add(new TreeItem("Identificación: " + clienteDTO.getIdentificacion()));
            item2.getChildren().add(new TreeItem("Teléfono: " + clienteDTO.getTelefono()));
    
            
            TreeItem<String> item3 = new TreeItem<>("Membresías");
            item.getChildren().add(item3);
            
            
            TreeItem<String> item4 = new TreeItem<>("Cobros Pendientes");
            item.getChildren().add(item4);
            
            treeview.getSelectionModel().select(item);
        }
//        for (int i = 0; i < variacionList2.size(); i++) {
//            String title = variacionList2.get(i).getDescripcion();
//            VariacionDTO v1 = variacionList2.get(i);
//            TreeItem<String> item = new TreeItem<>(title);
//            inicio.getChildren().add(item);
//            for (RequisitoDTO requisitoDTO : requisitosList2) {
//                if (requisitoDTO.getVariacion().getId() == v1.getId()) {
//                    TreeItem<String> item2 = new TreeItem<>(requisitoDTO.getDescripcion());
//                    item.getChildren().add(item2);
//                }
//            }
//            treeVar.getSelectionModel().select(item);
//        }
//        treeVar.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//
//                if (mouseEvent.getClickCount() == 2) {
//                    TreeItem<String> item = (TreeItem<String>) treeVar.getSelectionModel()
//                            .getSelectedItem();
//                    for (VariacionDTO variacionDTO : variacionList2) {
//                        if (variacionDTO.getDescripcion().equals(item.getValue())) {
//                            System.out.println(item.getValue());
//                            AppContext.getInstance().set("variacion", variacionDTO);
//                            llenarRequisitos();
//                            actionRequisitosClick();
//                        }
//                    }
//
//                }
//
//            }
//        });
    }

    @Override
    public void initialize() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
