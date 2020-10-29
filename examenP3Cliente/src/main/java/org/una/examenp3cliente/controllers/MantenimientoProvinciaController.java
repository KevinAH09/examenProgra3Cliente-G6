/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.examenp3cliente.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.una.examenp3cliente.dtos.apiProvincias.ProvinciaDTO;
import org.una.examenp3cliente.entitiesServices.apiProvincias.ProvinciaService;
import org.una.examenp3cliente.utils.FlowController;
import org.una.examenp3cliente.utils.Mensaje;

/**
 * FXML Controller class
 *
 * @author Bosco
 */
public class MantenimientoProvinciaController extends Controller implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private JFXTextField txtNombreProvincia;
    @FXML
    private JFXTextField txtCodigo;
    @FXML
    private JFXButton btnGuardar;

    ProvinciaDTO provincia;
    @FXML
    private TableView<ProvinciaDTO> tableView;
    public List<ProvinciaDTO> provinciaList = new ArrayList<ProvinciaDTO>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        actionAvionClick();
        llenarProvincia();
    }

    private void llenarProvincia() {
        tableView.getItems().clear();
        tableView.getColumns().clear();
        TableColumn<ProvinciaDTO, String> colNombre = new TableColumn("Nombre de la Provincia");
        colNombre.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getNombreProvincia()));
        TableColumn<ProvinciaDTO, String> colCodigo = new TableColumn("Código");
        colCodigo.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getCodigo()));
        tableView.getColumns().addAll(colNombre, colCodigo);

        provinciaList = ProvinciaService.estado(true);
        if (provinciaList != null && !provinciaList.isEmpty()) {
            tableView.setItems(FXCollections.observableArrayList(provinciaList));
        } else {
            new Mensaje().showModal(Alert.AlertType.INFORMATION, "Error en Provincias", ((Stage) txtNombreProvincia.getScene().getWindow()), "No existen Provincias");

        }
    }

    private void actionAvionClick() {
        tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 2 && tableView.selectionModelProperty().get().getSelectedItem() != null) {
                    provincia = (ProvinciaDTO) tableView.selectionModelProperty().get().getSelectedItem();
                    txtNombreProvincia.setText(provincia.getNombreProvincia());
                    txtCodigo.setText(provincia.getCodigo());

                }
            }
        });
    }

    @FXML
    private void guardar(ActionEvent event) {

        if (provincia == null) {
            if (!txtNombreProvincia.getText().isEmpty() && !txtCodigo.getText().isEmpty()) {
                provincia = new ProvinciaDTO();
                provincia.setNombreProvincia(txtNombreProvincia.getText());
                provincia.setEstado(true);
                provincia.setCodigo(txtCodigo.getText());
                if (ProvinciaService.createProvincias(provincia) != null) {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar Provincia", ((Stage) txtNombreProvincia.getScene().getWindow()), "Se guardó correctamente");
                    txtNombreProvincia.setText("");
                    txtCodigo.setText("");
                    tableView.getItems().clear();
                    llenarProvincia();
                } else {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Error al guardar la Provincia", ((Stage) txtNombreProvincia.getScene().getWindow()), "No se guardó correctamente");
                }
            } else {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Error al crear Provincia", ((Stage) txtNombreProvincia.getScene().getWindow()), "Rellene los campos necesarios");
            }
        } else {
            if (!txtNombreProvincia.getText().isEmpty() && !txtCodigo.getText().isEmpty()) {
                provincia.setNombreProvincia(txtNombreProvincia.getText());
                provincia.setCodigo(txtCodigo.getText());
                if (ProvinciaService.updateProvincia(provincia) == 200) {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Editar Provincia", ((Stage) txtNombreProvincia.getScene().getWindow()), "Se editó correctamente");
                    tableView.getItems().clear();
                    provincia = null;
                    llenarProvincia();
                    txtNombreProvincia.setText("");
                    txtCodigo.setText("");
                } else {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Error al editar la Provincia", ((Stage) txtNombreProvincia.getScene().getWindow()), "No se editó correctamente");
                }
            } else {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Error al editar la Provincia", ((Stage) txtNombreProvincia.getScene().getWindow()), "Rellene los campos necesarios");
            }
        }
    }

    @Override
    public void initialize() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void regresar(ActionEvent event) {
        FlowController.getInstance().goView("menuProvincia/MenuProvincia");
    }

    @FXML
    private void eliminar(ActionEvent event) {
        if (provincia.getId() != null) {
            if (new Mensaje().showConfirmation("Eliminar Provincia", (Stage) txtNombreProvincia.getScene().getWindow(), "Desea eliminar la Provincia ")) {
               provincia.setEstado(false);
                if (ProvinciaService.updateProvincia(provincia) == 200) {                  
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Eliminar Provincia", (Stage) txtNombreProvincia.getScene().getWindow(), "Provincia eliminada con exito");
                    llenarProvincia();
                }

            }
            
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Error al eliminar la Provincia", ((Stage) txtNombreProvincia.getScene().getWindow()), "Elija en el tableView una provincia");
        }
    }

}
