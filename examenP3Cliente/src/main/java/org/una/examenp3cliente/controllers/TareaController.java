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
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeItem;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import org.una.examenp3cliente.dtos.apiTareas.ProyectoDTO;
import org.una.examenp3cliente.dtos.apiTareas.TareaDTO;
import org.una.examenp3cliente.entitiesServices.apiTareas.ProyectoService;
import org.una.examenp3cliente.entitiesServices.apiTareas.TareaService;

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
    }

    @FXML
    private void accionCrearTarea(ActionEvent event) {
    }

    @FXML
    private void actionEditarGuadrarProyecto(ActionEvent event) {
    }

    @FXML
    private void actionMensageUrgencia(ActionEvent event) {
        if (txtUrgancia.getText().equals("")) {
            urgenciaValidator.getItems().clear();
            urgenciaValidator.getItems().add(
                    new MenuItem("Campo numero del 1 al 10, con el fin de evaluar la urgencia del proyecto"));
            urgenciaValidator.show(txtUrgancia, Side.RIGHT, 10, 0);
        }
    }

    @FXML
    private void actionGuardarEditarTarea(ActionEvent event) {
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
}
