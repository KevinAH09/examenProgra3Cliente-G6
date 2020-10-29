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
    @FXML
    private JFXButton brnCancelarProyecto;
    @FXML
    private JFXButton brnGuardarProyecto;
    @FXML
    private JFXButton brnGuardarTarea;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        proyectoSelect = new ProyectoDTO();
        tareaSelect = new TareaDTO();
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
        listProyectos = new ArrayList<>();
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
        btnEditarProeycto.setDisable(true);
        btnCrearTarea.setDisable(true);
        btnEditarTarea.setDisable(true);
        btnCancelarTarea.setDisable(true);
        brnGuardarProyecto.setVisible(true);
        txtNombreProyecto.setDisable(false);
        txtdescripcionProyecto.setDisable(false);
        proyectoSelect = new ProyectoDTO();
        tareaSelect = new TareaDTO();
        txtNombreProyecto.setText("");
        txtdescripcionProyecto.setText("");

        txtNombreTarea.setText("");
        txtdescripcionTarea.setText("");
        txtPorcentajeTarea.setText("");
        txtImportancia.setText("");
        txtUrgancia.setText("");
        txtPrioridad.setText("");
        fechaInicio.setValue(null);
        fechaFinalizacion.setValue(null);

    }

    @FXML
    private void accionCrearTarea(ActionEvent event) {
        if (proyectoSelect.getId() != null) {

            tareaSelect = new TareaDTO();
            txtNombreTarea.setText("");
            txtdescripcionTarea.setText("");
            txtPorcentajeTarea.setText("");
            txtImportancia.setText("");
            txtUrgancia.setText("");
            txtPrioridad.setText("");
            fechaInicio.setValue(null);
            fechaFinalizacion.setValue(null);
            txtNombreTarea.setDisable(false);
            txtdescripcionTarea.setDisable(false);
            txtPorcentajeTarea.setDisable(false);
            txtImportancia.setDisable(false);
            txtUrgancia.setDisable(false);
            fechaInicio.setDisable(false);
            fechaFinalizacion.setDisable(false);
            btnEditarProeycto.setDisable(true);
            brnCancelarProyecto.setDisable(true);
            btnEditarTarea.setDisable(true);
            btnProyectoNuevo.setDisable(true);
            brnGuardarTarea.setVisible(true);

        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Crear Tarea", ((Stage) btnCancelarTarea.getScene().getWindow()), "Seleccione un proyecto");

        }
    }

    @FXML
    private void actionEditarGuadrarProyecto(ActionEvent event) {

        if (proyectoSelect.getId() != null) {
            btnProyectoNuevo.setDisable(true);
            btnCrearTarea.setDisable(true);
            btnEditarTarea.setDisable(true);
            btnCancelarTarea.setDisable(true);
            brnGuardarProyecto.setVisible(true);
            txtNombreProyecto.setDisable(false);
            txtdescripcionProyecto.setDisable(false);

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
        if (proyectoSelect.getId() != null) {

            if (tareaSelect.getId() != null) {
                btnEditarProeycto.setDisable(true);
                brnCancelarProyecto.setDisable(true);
                btnCrearTarea.setDisable(true);
                btnProyectoNuevo.setDisable(true);
                brnGuardarTarea.setVisible(true);
                txtNombreTarea.setDisable(false);
                txtdescripcionTarea.setDisable(false);
                txtPorcentajeTarea.setDisable(false);
                txtImportancia.setDisable(false);
                txtUrgancia.setDisable(false);
                fechaInicio.setDisable(false);
                fechaFinalizacion.setDisable(false);
            } else {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Editar Tarea", ((Stage) btnCancelarTarea.getScene().getWindow()), "Seleccione una tarea");
            }

        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Editar Tarea", ((Stage) btnCancelarTarea.getScene().getWindow()), "Seleccione un proyecto");
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
        btnEditarProeycto.setDisable(false);
        btnCrearTarea.setDisable(false);
        btnProyectoNuevo.setDisable(false);
        brnGuardarTarea.setVisible(false);
        brnCancelarProyecto.setDisable(false);
        if (tareaSelect.getId() != null) {
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

    private void actionSalir(ActionEvent event) {
        FlowController.getInstance().goView("inicio/Inicio");
    }

    @FXML
    private void actionPrioridad(KeyEvent event) {
        if (!txtImportancia.getText().isEmpty() && !txtUrgancia.getText().isEmpty()) {
            txtPrioridad.setText(String.valueOf((Double.valueOf(txtImportancia.getText()) * Double.valueOf(txtUrgancia.getText()))));
        }
    }

    @FXML
    private void actionGuardarProyecto(ActionEvent event) {
        if (!txtNombreProyecto.getText().isEmpty() && !txtdescripcionProyecto.getText().isEmpty()) {
            proyectoSelect.setDescripcion(txtdescripcionProyecto.getText());
            proyectoSelect.setNombre(txtNombreProyecto.getText());
            if (proyectoSelect.getId() != null) {
                if (ProyectoService.updateProyecto(proyectoSelect) == 200) {
                    llenarProyectos();
                    new Mensaje().showModal(Alert.AlertType.CONFIRMATION, "Editar Proyecto", ((Stage) btnCancelarTarea.getScene().getWindow()), "Proyecto guardado correctamente");

                    txtNombreProyecto.setDisable(true);
                    txtdescripcionProyecto.setDisable(true);
                    btnEditarProeycto.setDisable(false);
                    btnCrearTarea.setDisable(false);
                    btnEditarTarea.setDisable(false);
                    brnGuardarProyecto.setVisible(false);
                    btnCancelarTarea.setDisable(false);
                }
            } else {
                proyectoSelect = ProyectoService.createProyecto(proyectoSelect);
                if (proyectoSelect != null) {
                    llenarProyectos();
                    llenarTreeVeew(listProyectos);
                    new Mensaje().showModal(Alert.AlertType.CONFIRMATION, "Crear Proyecto", ((Stage) btnCancelarTarea.getScene().getWindow()), "Proyecto guardado correctamente");

                    txtNombreProyecto.setDisable(true);
                    txtdescripcionProyecto.setDisable(true);
                    btnEditarProeycto.setDisable(false);
                    btnCrearTarea.setDisable(false);
                    btnEditarTarea.setDisable(false);
                    brnGuardarProyecto.setVisible(false);
                    btnCancelarTarea.setDisable(false);
                }
            }
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar Proyecto", ((Stage) btnCancelarTarea.getScene().getWindow()), "Por favor complete todos los campos");
        }
    }

    @FXML
    private void actionGuardarTarea(ActionEvent event) {
        if (!txtNombreTarea.getText().isEmpty() && !txtdescripcionTarea.getText().isEmpty() && !txtPorcentajeTarea.getText().isEmpty() && !txtImportancia.getText().isEmpty() && !txtUrgancia.getText().isEmpty() && fechaInicio.getValue() != null && fechaFinalizacion.getValue() != null) {
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

                    txtNombreTarea.setDisable(true);
                    txtdescripcionTarea.setDisable(true);
                    txtPorcentajeTarea.setDisable(true);
                    txtImportancia.setDisable(true);
                    txtUrgancia.setDisable(true);
                    fechaInicio.setDisable(true);
                    fechaFinalizacion.setDisable(true);
                    btnEditarProeycto.setDisable(false);
                    btnCrearTarea.setDisable(false);
                    btnProyectoNuevo.setDisable(false);
                    brnGuardarTarea.setVisible(false);
                    brnCancelarProyecto.setDisable(false);
                } else {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Editar Tarea", ((Stage) btnCancelarTarea.getScene().getWindow()), "Ocurrio un error al guardar la tarea");
                }
            } else {
                tareaSelect = TareaService.createTarea(tareaSelect);
                if (tareaSelect.getId() != null) {
                    llenarProyectos();
                    llenarTreeVeew(listProyectos);
                    new Mensaje().showModal(Alert.AlertType.CONFIRMATION, "Crear Tarea", ((Stage) btnCancelarTarea.getScene().getWindow()), "Tarea guardada correctamente");
                    txtNombreTarea.setDisable(true);
                    txtdescripcionTarea.setDisable(true);
                    txtPorcentajeTarea.setDisable(true);
                    txtImportancia.setDisable(true);
                    txtUrgancia.setDisable(true);
                    fechaInicio.setDisable(true);
                    fechaFinalizacion.setDisable(true);
                    btnEditarProeycto.setDisable(false);
                    btnCrearTarea.setDisable(false);
                    btnProyectoNuevo.setDisable(false);
                    brnGuardarTarea.setVisible(false);
                    brnCancelarProyecto.setDisable(false);
                } else {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Crear Tarea", ((Stage) btnCancelarTarea.getScene().getWindow()), "Ocurrio un error al guardar la tarea");
                }
            }
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Crear Tarea", ((Stage) btnCancelarTarea.getScene().getWindow()), "Por favor complete todos los campos");
        }
    }

    @FXML
    private void actionCancelarProyecto(ActionEvent event) {
        btnEditarProeycto.setDisable(false);
        btnCrearTarea.setDisable(false);
        btnEditarTarea.setDisable(false);
        brnGuardarProyecto.setVisible(false);
        txtNombreProyecto.setDisable(true);
        txtdescripcionProyecto.setDisable(true);
        btnCancelarTarea.setDisable(false);
        if (proyectoSelect.getId() != null) {
            txtNombreProyecto.setText(proyectoSelect.getNombre());
            txtdescripcionProyecto.setText(proyectoSelect.getDescripcion());
        }
    }

    @FXML
    private void actionEliminarProyecto(ActionEvent event) {
        if (proyectoSelect.getId() != null) {
            if (new Mensaje().showConfirmation("Eliminar proyecto", (Stage) btnCancelarTarea.getScene().getWindow(), "Desea eliminar la proyecto" + proyectoSelect.getNombre())) {
                for (TareaDTO listTarea : proyectoSelect.getListTareas()) {
                    TareaService.deleteTarea(listTarea.getId());
                }
                if (TareaService.deleteTarea(proyectoSelect.getId()) == 200) {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Eliminar proyecto", (Stage) btnCancelarTarea.getScene().getWindow(), "Proyecto eliminada con exito");
                    llenarProyectos();
                    llenarTreeVeew(listProyectos);
                }
            }
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar proyecto", (Stage) btnCancelarTarea.getScene().getWindow(), "Seleccione una proyecto");
        }
    }

    @FXML
    private void actionEliminarTarea(ActionEvent event) {
        if (tareaSelect.getId() != null) {
            if (new Mensaje().showConfirmation("Eliminar tarea", (Stage) btnCancelarTarea.getScene().getWindow(), "Desea eliminar la tarea " + tareaSelect.getNombre())) {
                if (TareaService.deleteTarea(tareaSelect.getId()) == 200) {
                    llenarProyectos();
                    llenarTreeVeew(listProyectos);
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Eliminar tarea", (Stage) btnCancelarTarea.getScene().getWindow(), "Tarea eliminada con exito");
                }

            }
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar tarea", (Stage) btnCancelarTarea.getScene().getWindow(), "Seleccione una tarea");
        }
    }

}
