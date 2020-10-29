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
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
    UnidadDTO unidad;
    @FXML
    private JFXComboBox<String> combTipoUnidad;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        distritoList = DistritoService.allDistrito();
        combDistrito.setItems(FXCollections.observableArrayList(distritoList));
        combTipoUnidad.setItems(FXCollections.observableArrayList("Calle", "Comunidad", "Barrio"));
    }

    @FXML
    private void regresar(ActionEvent event) {
        FlowController.getInstance().goView("menuProvincia/MenuProvincia");
    }

    @FXML
    private void guardar(ActionEvent event) {
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
            } else {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Error al guardar el Unidad", ((Stage) txtNombreUnidad.getScene().getWindow()), "No se guardó correctamente");
            }
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Error al crear Unidad", ((Stage) txtNombreUnidad.getScene().getWindow()), "Rellene los campos necesarios");
        }
    }

    @Override
    public void initialize() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
