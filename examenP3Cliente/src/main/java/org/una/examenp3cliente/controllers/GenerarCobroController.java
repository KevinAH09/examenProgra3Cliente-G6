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
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
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
    private void llenarZonas() {
 
        TableColumn<ClienteDTO, String> colNombre = new TableColumn("Nombre");
        colNombre.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getNombre()));
        TableColumn<ClienteDTO, String> colIdentificacion = new TableColumn("Identificación");
        colIdentificacion.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getIdentificacion()));
        TableColumn<ClienteDTO, String> colTelefono = new TableColumn("Telefono");
        colTelefono.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getTelefono()));
        tableView.getColumns().addAll(colNombre, colIdentificacion, colTelefono);
        notificar(1);
    }
    
     private void notificar(int num) {
        tableView.getItems().clear();
        if (num == 1) {
            ImageView imageView = new ImageView(new Image("org/una/examenp3cliente/views/shared/info.png"));
            Text lab = new Text("Para mostrar datos en este apartado debe realizar el filtro correspondiente");
            lab.setFill(Color.web("#0076a3"));
            VBox box = new VBox();
            box.setAlignment(Pos.CENTER);
            box.getChildren().add(imageView);
            box.getChildren().add(lab);
            tableView.setPlaceholder(box);
        } else {
            ImageView imageView2 = new ImageView(new Image("org/una/examenp3cliente/views/shared/warning.png"));
            Text lab = new Text("No se encontro coincidencias");
            lab.setFill(Color.web("#0076a3"));
            VBox box = new VBox();
            box.setAlignment(Pos.CENTER);
            box.getChildren().add(imageView2);
            box.getChildren().add(lab);
            tableView.setPlaceholder(box);
        }
    }
}
