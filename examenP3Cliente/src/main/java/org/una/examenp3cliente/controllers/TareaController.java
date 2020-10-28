/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.examenp3cliente.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeView;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeItem;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.una.examenp3cliente.dtos.apiTareas.ProyectoDTO;
import org.una.examenp3cliente.dtos.apiTareas.TareaDTO;
import org.una.examenp3cliente.entitiesServices.apiTareas.ProyectoService;
import org.una.examenp3cliente.entitiesServices.apiTareas.TareaService;
import org.una.examenp3cliente.utils.FlowController;
import org.una.examenp3cliente.utils.Mensaje;

/**
 * FXML Controller class
 *
 * @author colo7
 */
public class TareaController extends Controller implements Initializable {

    @FXML
    private JFXTreeView<String> treeView;

    List<ProyectoDTO> listProyectos = new ArrayList<>();
    ProyectoDTO proyectoSelect;
    TareaDTO tareaSelect;

    @FXML
    private JFXTextField txtnombreProyecto;
    @FXML
    private JFXButton btnProyectoNuevo;
    @FXML
    private JFXTextField txtNombreProyecto;
    @FXML
    private JFXTextField txtPorcentajeAvanceProyecto;
    @FXML
    private JFXTextArea txtdescripcionProyecto;
    @FXML
    private JFXButton btnCrearTarea;
    @FXML
    private JFXButton btnEditarProeycto;
    @FXML
    private JFXTextField txtNombreTarea;
    @FXML
    private JFXTextField txtPorcentajeTarea;
    @FXML
    private DatePicker fechaInicio;
    @FXML
    private DatePicker fechaFinalizacion;
    @FXML
    private JFXTextField txtImportancia;
    @FXML
    private JFXTextField txtUrgancia;
    @FXML
    private JFXTextField txtPrioridad;
    @FXML
    private JFXTextArea txtdescripcionTarea;
    @FXML
    private JFXButton btnEditarTarea;

    final ContextMenu importanciaValidator = new ContextMenu();
    final ContextMenu urgenciaValidator = new ContextMenu();
    @FXML
    private Button btnCancelarTarea;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        llenarProyectos();
        llenarTreeVeew(listProyectos);
        actionTreeView();
        importanciaValidator.setAutoHide(false);
        urgenciaValidator.setAutoHide(false);

    }

    @Override
    public void initialize() {

    }

    private void llenarProyectos() {
        List<TareaDTO> listTareas = new ArrayList<>();
        listProyectos = ProyectoService.allProyecto();
        for (ProyectoDTO proyecto : listProyectos) {
            listTareas = new ArrayList<>();
            listTareas = TareaService.proyectoIdTarea(proyecto.getId());
            proyecto.setListTareas(listTareas);
        }
    }

    private void llenarTreeVeew(List<ProyectoDTO> listProyect) {
        treeView.setRoot(null);
        if (listProyect != null) {
            treeView.setRoot(null);
//  
//            Node imgroot = new ImageView(new Image("org/una/laboratorio/icons/menu.png"));
//            Node imgInformacion = new ImageView(new Image("org/una/laboratorio/icons/informacion.png"));
//            Node imgAdmin = new ImageView(new Image("org/una/laboratorio/icons/lengueta.png"));

            TreeItem<String> root = new TreeItem<>("Proyectos");
//            root.setGraphic(imgroot);
            root.setExpanded(true);
            treeView.setRoot(root);

            for (ProyectoDTO proyecto : listProyect) {
                TreeItem<String> item = new TreeItem<>(proyecto.getNombre());
//                item.setGraphic(imgroot);
                root.getChildren().add(item);
                for (TareaDTO tarea : proyecto.getListTareas()) {
                    TreeItem<String> itemTarea = new TreeItem<>(tarea.getNombre());
//                    itemTarea.setGraphic(imgAdmin);
                    item.getChildren().add(itemTarea);

                }

            }

        }

    }

    void actionTreeView() {
        treeView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                if (mouseEvent.getClickCount() == 2) {
                    TreeItem<String> item = (TreeItem<String>) treeView.getSelectionModel().getSelectedItem();
                    if (!item.getValue().equals("Proyectos")) {
                        selectItem(item.getParent().getValue(), item.getValue());
                    }

                }

            }
        });
    }

    private void selectItem(String nombreProyecto, String nombreTarea) {
        if (!nombreProyecto.equals("Proyectos")) {
            proyectoSelect = listProyectos.stream().filter(x -> x.getNombre().equals(nombreProyecto)).findFirst().get();
            if (proyectoSelect != null) {

                tareaSelect = proyectoSelect.getListTareas().stream().filter(x -> x.getNombre().equals(nombreTarea)).findFirst().get();

                if (tareaSelect != null) {
                    txtNombreProyecto.setText(proyectoSelect.getNombre());
                    txtdescripcionProyecto.setText(proyectoSelect.getDescripcion());
                    txtNombreTarea.setText(tareaSelect.getNombre());
                    txtdescripcionTarea.setText(tareaSelect.getDescripcion());
                    txtPorcentajeTarea.setText(String.valueOf(tareaSelect.getProcentajeAvance()));
                    txtImportancia.setText(String.valueOf(tareaSelect.getImportancia()));
                    txtUrgancia.setText(String.valueOf(tareaSelect.getUrgencia()));
                    txtPrioridad.setText(String.valueOf(tareaSelect.getUrgencia() * tareaSelect.getImportancia()));
                    fechaInicio.setValue(tareaSelect.getFechaInicio().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                    fechaFinalizacion.setValue(tareaSelect.getFechaFinalizacion().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

                }
            }
        } else {
            proyectoSelect = listProyectos.stream().filter(x -> x.getNombre().equals(nombreTarea)).findFirst().get();
            txtNombreProyecto.setText(proyectoSelect.getNombre());
            txtdescripcionProyecto.setText(proyectoSelect.getDescripcion());
        }

    }

    @FXML
    private void actionFilterproyecto(KeyEvent event) {
        if (txtnombreProyecto.getText() != null) {
            llenarTreeVeew(listProyectos.stream().filter(x -> x.getNombre().toUpperCase().startsWith(txtnombreProyecto.getText().toUpperCase())).collect(Collectors.toList()));
        } else {
            llenarTreeVeew(listProyectos);
        }
    }

    @FXML
    private void actionProyectoNuevo(ActionEvent event) {
        proyectoSelect = new ProyectoDTO();
        tareaSelect = new TareaDTO();
    }

    @FXML
    private void accionCrearTarea(ActionEvent event) {
        if (proyectoSelect != null) {
            if (btnCrearTarea.getText().equals("Cancelar")) {
                btnEditarProeycto.setText("Editar proyecto");
                btnCrearTarea.setText("Crear tarea");
                txtNombreProyecto.setDisable(true);
                txtdescripcionProyecto.setDisable(true);
                txtNombreProyecto.setText(proyectoSelect.getNombre());
                txtdescripcionProyecto.setText(proyectoSelect.getDescripcion());
            } else {
                tareaSelect = new TareaDTO();
                txtNombreTarea.setText("");
                txtdescripcionTarea.setText("");
                txtPorcentajeTarea.setText("");
                txtImportancia.setText("");
                txtUrgancia.setText("");
                txtPrioridad.setText("");
                fechaInicio.setValue(null);
                fechaFinalizacion.setValue(null);
                btnEditarTarea.setText("Guardar tarea");
                txtNombreTarea.setDisable(false);
                txtdescripcionTarea.setDisable(false);
                txtPorcentajeTarea.setDisable(false);
                txtImportancia.setDisable(false);
                txtUrgancia.setDisable(false);
                fechaInicio.setDisable(false);
                fechaFinalizacion.setDisable(false);
            }
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Crear Tarea", ((Stage) btnCancelarTarea.getScene().getWindow()), "Seleccione un proyecto");

        }
    }

    @FXML
    private void actionEditarGuadrarProyecto(ActionEvent event) {

        if (proyectoSelect != null) {
            if (btnEditarProeycto.getText().equals("Editar proyecto")) {
                btnEditarProeycto.setText("Guardar proyecto");
                btnCrearTarea.setText("Cancelar");
                txtNombreProyecto.setDisable(false);
                txtdescripcionProyecto.setDisable(false);
            } else {
                if (txtNombreProyecto.getText() != null && txtdescripcionProyecto.getText() != null) {
                    proyectoSelect.setDescripcion(txtdescripcionProyecto.getText());
                    proyectoSelect.setNombre(txtNombreProyecto.getText());
                    if (ProyectoService.updateProyecto(proyectoSelect) == 200) {
                        llenarProyectos();
                        new Mensaje().showModal(Alert.AlertType.CONFIRMATION, "Editar Proyecto", ((Stage) btnCancelarTarea.getScene().getWindow()), "Proyecto guardado correctamente");
                        btnEditarProeycto.setText("Editar proyecto");
                        btnCrearTarea.setText("Crear tarea");
                        txtNombreProyecto.setDisable(true);
                        txtdescripcionProyecto.setDisable(true);
                    }
                } else {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Editar Proyecto", ((Stage) btnCancelarTarea.getScene().getWindow()), "Por favor complete todos los campos");
                }
            }
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Editar Proyecto", ((Stage) btnCancelarTarea.getScene().getWindow()), "Por favor seleccione un proyecto");
        }
    }

    @FXML
    private void actionMensageUrgencia(MouseEvent event) {
        if (txtUrgancia.getText().equals("")) {
            urgenciaValidator.getItems().clear();
            urgenciaValidator.getItems().add(
                    new MenuItem("Campo numero del 1 al 10, con el fin de evaluar la urgencia del proyecto"));
            urgenciaValidator.show(txtUrgancia, Side.RIGHT, 10, 0);
        }
    }

    @FXML
    private void actionGuardarEditarTarea(ActionEvent event) {
        if (btnEditarTarea.getText().equals("Editar tarea")) {
            btnEditarTarea.setText("Guardar tarea");
            txtNombreTarea.setDisable(false);
            txtdescripcionTarea.setDisable(false);
            txtPorcentajeTarea.setDisable(false);
            txtImportancia.setDisable(false);
            txtUrgancia.setDisable(false);
            fechaInicio.setDisable(false);
            fechaFinalizacion.setDisable(false);
        } else {
            if (txtNombreTarea.getText() != null && txtdescripcionTarea.getText() != null && txtPorcentajeTarea.getText() != null && txtImportancia.getText() != null && txtUrgancia.getText() != null && fechaInicio.getValue() != null && fechaFinalizacion.getValue() != null) {
                tareaSelect.setProyectoId(proyectoSelect);
                tareaSelect.setDescripcion(txtdescripcionTarea.getText());
                tareaSelect.setNombre(txtNombreTarea.getText());
                tareaSelect.setProcentajeAvance(Double.valueOf(txtPorcentajeTarea.getText()));
                tareaSelect.setUrgencia(Double.valueOf(txtUrgancia.getText()));
                tareaSelect.setImportancia(Double.valueOf(txtImportancia.getText()));
                tareaSelect.setFechaInicio(Date.from(fechaInicio.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                tareaSelect.setFechaFinalizacion(Date.from(fechaFinalizacion.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                if (tareaSelect.getId() != null) {
                    if (TareaService.updateTarea(tareaSelect) == 200) {
                        llenarProyectos();
                        new Mensaje().showModal(Alert.AlertType.CONFIRMATION, "Editar Tarea", ((Stage) btnCancelarTarea.getScene().getWindow()), "Tarea guardada correctamente");
                        btnEditarTarea.setText("Editar tarea");
                        txtNombreTarea.setDisable(true);
                        txtdescripcionTarea.setDisable(true);
                        txtPorcentajeTarea.setDisable(true);
                        txtImportancia.setDisable(true);
                        txtUrgancia.setDisable(true);
                        fechaInicio.setDisable(true);
                        fechaFinalizacion.setDisable(true);
                    } else {
                        new Mensaje().showModal(Alert.AlertType.ERROR, "Editar Tarea", ((Stage) btnCancelarTarea.getScene().getWindow()), "Ocurrio un error al guardar la tarea");
                    }
                } else {
                    tareaSelect = TareaService.createTarea(tareaSelect);
                    if (tareaSelect.getId() != null) {
                        llenarProyectos();
                        llenarTreeVeew(listProyectos);
                        new Mensaje().showModal(Alert.AlertType.CONFIRMATION, "Crear Tarea", ((Stage) btnCancelarTarea.getScene().getWindow()), "Tarea guardada correctamente");
                        btnEditarTarea.setText("Crear tarea");
                        txtNombreTarea.setDisable(true);
                        txtdescripcionTarea.setDisable(true);
                        txtPorcentajeTarea.setDisable(true);
                        txtImportancia.setDisable(true);
                        txtUrgancia.setDisable(true);
                        fechaInicio.setDisable(true);
                        fechaFinalizacion.setDisable(true);
                    } else {
                        new Mensaje().showModal(Alert.AlertType.ERROR, "Crear Tarea", ((Stage) btnCancelarTarea.getScene().getWindow()), "Ocurrio un error al guardar la tarea");
                    }
                }
            } else {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Crear Tarea", ((Stage) btnCancelarTarea.getScene().getWindow()), "Por favor complete todos los campos");
            }
        }
    }

    @FXML
    private void actionMensageImportancia(MouseEvent event) {
        if (txtImportancia.getText().equals("")) {
            importanciaValidator.getItems().clear();
            importanciaValidator.getItems().add(
                    new MenuItem("Campo numero del 1 al 10, con el fin de evaluar la importancia del proyecto"));
            importanciaValidator.show(txtImportancia, Side.RIGHT, 10, 0);
        }
    }

    @FXML
    private void actionCancelarTarea(ActionEvent event) {
    }

    @FXML
    private void actionSalir(ActionEvent event) {
        FlowController.getInstance().goView("inicio/Inicio");
    }

    @FXML
    private void actionPrioridad(KeyEvent event) {
        if (!txtImportancia.getText().isEmpty() && !txtUrgancia.getText().isEmpty()) {
            txtPrioridad.setText(String.valueOf((Double.valueOf(txtImportancia.getText()) * Double.valueOf(txtUrgancia.getText()))));
        }
    }

}
