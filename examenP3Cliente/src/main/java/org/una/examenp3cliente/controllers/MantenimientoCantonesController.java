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
import org.una.examenp3cliente.dtos.apiProvincias.ProvinciaDTO;
import org.una.examenp3cliente.entitiesServices.apiProvincias.CantonService;
import org.una.examenp3cliente.entitiesServices.apiProvincias.ProvinciaService;
import org.una.examenp3cliente.utils.FlowController;
import org.una.examenp3cliente.utils.Mensaje;

/**
 * FXML Controller class
 *
 * @author Bosco
 */
public class MantenimientoCantonesController extends Controller implements Initializable {

    @FXML
    private JFXTextField txtNombreCanton;
    @FXML
    private JFXTextField txtCodigo;
    @FXML
    private JFXComboBox<ProvinciaDTO> combProvincia;
    @FXML
    private JFXButton btnGuardar;
    public List<ProvinciaDTO> provinciaList = new ArrayList<ProvinciaDTO>();
    public List<CantonDTO> cantonList = new ArrayList<CantonDTO>();

    CantonDTO canton;
    @FXML
    private TableView<CantonDTO> tableView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        provinciaList = ProvinciaService.allProvincia();
        combProvincia.setItems(FXCollections.observableArrayList(provinciaList));
        actionCantonClick();
        llenarCanton();

    }

    private void llenarCanton() {
        tableView.getItems().clear();
        tableView.getColumns().clear();
        cantonList = null;
        TableColumn<CantonDTO, String> colNombre = new TableColumn("Nombre del cantón");
        colNombre.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getNombreCanton()));
        TableColumn<CantonDTO, String> colCodigo = new TableColumn("Código");
        colCodigo.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getCodigo()));
        TableColumn<CantonDTO, String> colProvincia = new TableColumn("Nombre de la Provincia");
        colProvincia.setCellValueFactory((param) -> new SimpleObjectProperty(param.getValue().getProvinciaId().getNombreProvincia()));
        tableView.getColumns().addAll(colNombre, colCodigo, colProvincia);

        cantonList = CantonService.allCanton();
        if (cantonList != null && !cantonList.isEmpty()) {
            tableView.setItems(FXCollections.observableArrayList(cantonList));
        } else {
            new Mensaje().showModal(Alert.AlertType.INFORMATION, "Error en cantón", ((Stage) txtNombreCanton.getScene().getWindow()), "No existen Cantón");
        }

    }

    private void actionCantonClick() {
        tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 2 && tableView.selectionModelProperty().get().getSelectedItem() != null) {
                    canton = (CantonDTO) tableView.selectionModelProperty().get().getSelectedItem();
                    txtNombreCanton.setText(canton.getNombreCanton());
                    txtCodigo.setText(canton.getCodigo());
                    combProvincia.setValue(canton.getProvinciaId());
                }

            }
        });
    }

    @FXML
    private void guardar(ActionEvent event) {

        if (canton == null) {
            if (!txtNombreCanton.getText().isEmpty() && !txtCodigo.getText().isEmpty() && !combProvincia.getValue().getNombreProvincia().isEmpty()) {
                canton = new CantonDTO();
                canton.setNombreCanton(txtNombreCanton.getText());
                canton.setCodigo(txtCodigo.getText());
                canton.setProvinciaId(combProvincia.getValue());
                if (CantonService.createCantones(canton) != null) {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar Cantón", ((Stage) txtNombreCanton.getScene().getWindow()), "Se guardó correctamente");
                    txtNombreCanton.setText("");
                    txtCodigo.setText("");
                    combProvincia.setValue(null);
                    canton = null;
                    tableView.getItems().clear();
                    llenarCanton();

                } else {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Error al guardar el cantón", ((Stage) txtNombreCanton.getScene().getWindow()), "No se guardó correctamente");
                }
            } else {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Error al crear cantón", ((Stage) txtNombreCanton.getScene().getWindow()), "Rellene los campos necesarios");
            }
        } else {
            if (!txtNombreCanton.getText().isEmpty() && !txtCodigo.getText().isEmpty() && !combProvincia.getValue().getNombreProvincia().isEmpty()) {
                canton.setNombreCanton(txtNombreCanton.getText());
                canton.setCodigo(txtCodigo.getText());
                canton.setProvinciaId(combProvincia.getValue());
                if (CantonService.updateCanton(canton) == 200) {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Editar Cantón", ((Stage) txtNombreCanton.getScene().getWindow()), "Se editó correctamente");
                    tableView.getItems().clear();
                    canton = null;
                    llenarCanton();
                    txtNombreCanton.setText("");
                    txtCodigo.setText("");
                    combProvincia.setValue(null);
                } else {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Error al editar la Cantón", ((Stage) txtNombreCanton.getScene().getWindow()), "No se editó correctamente");
                }
            } else {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Error al editar la Cantón", ((Stage) txtNombreCanton.getScene().getWindow()), "Rellene los campos necesarios");
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
    }

}
