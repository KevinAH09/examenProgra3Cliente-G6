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
import org.una.examenp3cliente.dtos.apiProvincias.DistritoDTO;
import org.una.examenp3cliente.dtos.apiProvincias.UnidadDTO;
import org.una.examenp3cliente.entitiesServices.apiProvincias.DistritoService;
import org.una.examenp3cliente.entitiesServices.apiProvincias.UnidadService;
import org.una.examenp3cliente.utils.FlowController;
import org.una.examenp3cliente.utils.Mensaje;

/**
 * FXML Controller class
 *
 * @author Bosco
 */
public class MantenimientoUnidadesController extends Controller implements Initializable {

    @FXML
    private JFXTextField txtNombreUnidad;
    @FXML
    private JFXTextField txtCodigo;
    @FXML
    private JFXTextField txtPoblacion;
    @FXML
    private JFXTextField txtAreaCuadrada;
    @FXML
    private JFXComboBox<DistritoDTO> combDistrito;
    public List<DistritoDTO> distritoList = new ArrayList<DistritoDTO>();
    public List<UnidadDTO> unidadList = new ArrayList<UnidadDTO>();
    UnidadDTO unidad;
    @FXML
    private JFXComboBox<String> combTipoUnidad;
    @FXML
    private TableView<UnidadDTO> tableView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        distritoList = DistritoService.allDistrito();
        combDistrito.setItems(FXCollections.observableArrayList(distritoList));
        combTipoUnidad.setItems(FXCollections.observableArrayList("Calle", "Comunidad", "Barrio"));
        actionUnidadClick();
        llenarUnidad();
    }

    @FXML
    private void regresar(ActionEvent event) {
        FlowController.getInstance().goView("menuProvincia/MenuProvincia");
    }

    private void llenarUnidad() {
        tableView.getItems().clear();
        tableView.getColumns().clear();
        unidadList = null;
        TableColumn<UnidadDTO, String> colNombre = new TableColumn("Nombre de la unidad");
        colNombre.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getNombreUnidad()));
        TableColumn<UnidadDTO, String> colCodigo = new TableColumn("Código");
        colCodigo.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getCodigo()));

        TableColumn<UnidadDTO, String> colTipo = new TableColumn("Tipo");
        colTipo.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getTipo()));

        TableColumn<UnidadDTO, String> colPoblacion = new TableColumn("Población");
        colPoblacion.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getPoblacion().toString()));

        TableColumn<UnidadDTO, String> colAreaCuadrada = new TableColumn("Área cuadrada");
        colAreaCuadrada.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getAreaCuadrada().toString()));

        TableColumn<UnidadDTO, String> colDistrito = new TableColumn("Nombre del distrito");
        colDistrito.setCellValueFactory((param) -> new SimpleObjectProperty(param.getValue().getDistritoId().getNombreDistrito()));
        tableView.getColumns().addAll(colNombre, colCodigo, colTipo, colPoblacion, colAreaCuadrada, colDistrito);

        unidadList = UnidadService.allUnidad();
        if (unidadList != null && !unidadList.isEmpty()) {
            tableView.setItems(FXCollections.observableArrayList(unidadList));
        } else {
            new Mensaje().showModal(Alert.AlertType.INFORMATION, "Error en distrito", ((Stage) txtNombreUnidad.getScene().getWindow()), "No existen distrito");
        }

    }

    private void actionUnidadClick() {
        tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 2 && tableView.selectionModelProperty().get().getSelectedItem() != null) {
                    unidad = (UnidadDTO) tableView.selectionModelProperty().get().getSelectedItem();
                    txtNombreUnidad.setText(unidad.getNombreUnidad());
                    txtCodigo.setText(unidad.getCodigo());
                    txtPoblacion.setText(unidad.getPoblacion().toString());
                    txtAreaCuadrada.setText(unidad.getAreaCuadrada().toString());
                    combDistrito.setValue(unidad.getDistritoId());
                    combTipoUnidad.setValue(unidad.getTipo());
                }

            }
        });
    }

    @FXML
    private void guardar(ActionEvent event) {
        if (unidad == null) {
            if (!txtNombreUnidad.getText().isEmpty() && !txtCodigo.getText().isEmpty() && !combDistrito.getValue().getNombreDistrito().isEmpty() && !combTipoUnidad.getValue().isEmpty() && !txtPoblacion.getText().isEmpty() && !txtAreaCuadrada.getText().isEmpty()) {
                unidad = new UnidadDTO();
                unidad.setNombreUnidad(txtNombreUnidad.getText());
                unidad.setCodigo(txtCodigo.getText());
                unidad.setPoblacion(new Long(txtPoblacion.getText()));
                unidad.setAreaCuadrada(new Double(txtAreaCuadrada.getText()));
                unidad.setDistritoId(combDistrito.getValue());
                if (combTipoUnidad.getValue().equals("Calle")) {
                    unidad.setTipo("Calle");
                } else if (combTipoUnidad.getValue().equals("Comunidad")) {
                    unidad.setTipo("Comunidad");
                } else if (combTipoUnidad.getValue().equals("Barrio")) {
                    unidad.setTipo("Barrio");
                }
                if (UnidadService.createUnidad(unidad) != null) {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar Unidad", ((Stage) txtNombreUnidad.getScene().getWindow()), "Se guardó correctamente");
                    tableView.getItems().clear();
                    unidad = null;
                    llenarUnidad();
                    txtNombreUnidad.setText("");
                    txtAreaCuadrada.setText("");
                    txtPoblacion.setText("");
                    txtCodigo.setText("");
                    combDistrito.setValue(null);
                    combTipoUnidad.setValue(null);
                } else {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Error al guardar el Unidad", ((Stage) txtNombreUnidad.getScene().getWindow()), "No se guardó correctamente");
                }
            } else {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Error al crear Unidad", ((Stage) txtNombreUnidad.getScene().getWindow()), "Rellene los campos necesarios");
            }
        } else {
            if (!txtNombreUnidad.getText().isEmpty() && !txtCodigo.getText().isEmpty() && !combDistrito.getValue().getNombreDistrito().isEmpty() && !combTipoUnidad.getValue().isEmpty() && !txtPoblacion.getText().isEmpty() && !txtAreaCuadrada.getText().isEmpty()) {
                unidad.setNombreUnidad(txtNombreUnidad.getText());
                unidad.setCodigo(txtCodigo.getText());
                unidad.setDistritoId(combDistrito.getValue());
                unidad.setPoblacion(new Long(txtPoblacion.getText()));
                unidad.setAreaCuadrada(new Double(txtAreaCuadrada.getText()));
                if (combTipoUnidad.getValue().equals("Calle")) {
                    unidad.setTipo("Calle");
                } else if (combTipoUnidad.getValue().equals("Comunidad")) {
                    unidad.setTipo("Comunidad");
                } else if (combTipoUnidad.getValue().equals("Barrio")) {
                    unidad.setTipo("Barrio");
                }
                if (UnidadService.updateUnidad(unidad) == 200) {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Editar Distrito", ((Stage) txtNombreUnidad.getScene().getWindow()), "Se editó correctamente");
                    tableView.getItems().clear();
                    unidad = null;
                    llenarUnidad();
                    txtNombreUnidad.setText("");
                    txtAreaCuadrada.setText("");
                    txtPoblacion.setText("");
                    txtCodigo.setText("");
                    combDistrito.setValue(null);
                    combTipoUnidad.setValue(null);
                } else {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Error al editar el Distrito", ((Stage) txtNombreUnidad.getScene().getWindow()), "No se editó correctamente");
                }
            } else {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Error al editar el Distrito", ((Stage) txtNombreUnidad.getScene().getWindow()), "Rellene los campos necesarios");
            }
        }
    }

    @Override
    public void initialize() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void eliminar(ActionEvent event) {
    }

}
