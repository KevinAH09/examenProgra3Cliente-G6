/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.examenp3cliente.controllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
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
import javafx.stage.Stage;
import org.una.examenp3cliente.dtos.apiProvincias.CantonDTO;
import org.una.examenp3cliente.dtos.apiProvincias.DistritoDTO;
import org.una.examenp3cliente.entitiesServices.apiProvincias.CantonService;
import org.una.examenp3cliente.entitiesServices.apiProvincias.DistritoService;
import org.una.examenp3cliente.utils.FlowController;
import org.una.examenp3cliente.utils.Mensaje;

/**
 * FXML Controller class
 *
 * @author Bosco
 */
public class MantenimientoDistritosController extends Controller implements Initializable {

    @FXML
    private JFXTextField txtNombreDistrito;
    @FXML
    private JFXTextField txtCodigo;
    @FXML
    private JFXComboBox<CantonDTO> combCantones;
    public List<CantonDTO> cantonList = new ArrayList<CantonDTO>();
    public List<DistritoDTO> distritoList = new ArrayList<DistritoDTO>();
    DistritoDTO distrito;
    @FXML
    private TableView<DistritoDTO> tableView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cantonList = CantonService.allCanton();
        combCantones.setItems(FXCollections.observableArrayList(cantonList));
        actionDistritoClick();
        llenarDistrito();
    }

    @FXML
    private void regresar(ActionEvent event) {
        FlowController.getInstance().goView("menuProvincia/MenuProvincia");
    }

    private void llenarDistrito() {
        tableView.getItems().clear();
        tableView.getColumns().clear();
        distritoList = null;
        TableColumn<DistritoDTO, String> colNombre = new TableColumn("Nombre del distrito");
        colNombre.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getNombreDistrito()));
        TableColumn<DistritoDTO, String> colCodigo = new TableColumn("Código");
        colCodigo.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getCodigo()));
        TableColumn<DistritoDTO, String> colCanton = new TableColumn("Nombre del cantón");
        colCanton.setCellValueFactory((param) -> new SimpleObjectProperty(param.getValue().getCantonId().getNombreCanton()));
        tableView.getColumns().addAll(colNombre, colCodigo, colCanton);

        distritoList = DistritoService.estado(true);
        if (distritoList != null && !distritoList.isEmpty()) {
            tableView.setItems(FXCollections.observableArrayList(distritoList));
        } else {
            new Mensaje().showModal(Alert.AlertType.INFORMATION, "Error en distrito", ((Stage) txtNombreDistrito.getScene().getWindow()), "No existen distrito");
        }

    }

    private void actionDistritoClick() {
        tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 2 && tableView.selectionModelProperty().get().getSelectedItem() != null) {
                    distrito = (DistritoDTO) tableView.selectionModelProperty().get().getSelectedItem();
                    txtNombreDistrito.setText(distrito.getNombreDistrito());
                    txtCodigo.setText(distrito.getCodigo());
                    combCantones.setValue(distrito.getCantonId());
                }

            }
        });
    }

    @FXML
    private void guardar(ActionEvent event) {
        if (distrito == null) {
            if (!txtNombreDistrito.getText().isEmpty() && !txtCodigo.getText().isEmpty() && !combCantones.getValue().getNombreCanton().isEmpty()) {
                distrito = new DistritoDTO();
                distrito.setNombreDistrito(txtNombreDistrito.getText());
                distrito.setCodigo(txtCodigo.getText());
                distrito.setCantonId(combCantones.getValue());
                distrito.setEstado(true);
                if (DistritoService.createDistrito(distrito) != null) {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar Distrito", ((Stage) txtNombreDistrito.getScene().getWindow()), "Se guardó correctamente");
                    txtNombreDistrito.setText("");
                    txtCodigo.setText("");
                    combCantones.setValue(null);
                    distrito = null;
                    tableView.getItems().clear();
                    llenarDistrito();
                } else {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Error al guardar el Distrito", ((Stage) txtNombreDistrito.getScene().getWindow()), "No se guardó correctamente");
                }
            } else {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Error al crear Distrito", ((Stage) txtNombreDistrito.getScene().getWindow()), "Rellene los campos necesarios");
            }
        } else {
            if (!txtNombreDistrito.getText().isEmpty() && !txtCodigo.getText().isEmpty() && !combCantones.getValue().getNombreCanton().isEmpty()) {
                distrito.setNombreDistrito(txtNombreDistrito.getText());
                distrito.setCodigo(txtCodigo.getText());
                distrito.setCantonId(combCantones.getValue());
                if (DistritoService.updateDistrito(distrito) == 200) {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Editar Distrito", ((Stage) txtNombreDistrito.getScene().getWindow()), "Se editó correctamente");
                    tableView.getItems().clear();
                    distrito = null;
                    llenarDistrito();
                    txtNombreDistrito.setText("");
                    txtCodigo.setText("");
                    combCantones.setValue(null);
                } else {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Error al editar el Distrito", ((Stage) txtNombreDistrito.getScene().getWindow()), "No se editó correctamente");
                }
            } else {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Error al editar el Distrito", ((Stage) txtNombreDistrito.getScene().getWindow()), "Rellene los campos necesarios");
            }
        }
    }

    @Override
    public void initialize() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void eliminar(ActionEvent event) {
        if (distrito.getId() != null) {
            if (new Mensaje().showConfirmation("Eliminar Distrito", (Stage) txtNombreDistrito.getScene().getWindow(), "Desea eliminar el Distrito ")) {
               distrito.setEstado(false);
                if (DistritoService.updateDistrito(distrito) == 200) {                  
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Eliminar Distrito", (Stage) txtNombreDistrito.getScene().getWindow(), "Distrito eliminado con exito");
                    llenarDistrito();
                }

            }
            
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Error al eliminar el Distrito", ((Stage) txtNombreDistrito.getScene().getWindow()), "Elija en el tableView un Distrito");
        }
    }

}
