/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.examenp3cliente.controllers;

import com.jfoenix.controls.JFXTreeView;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        llenarProyectos();
        System.out.println(listProyectos);
        if (listProyectos != null) {

            Node imgroot = new ImageView(new Image("org/una/laboratorio/icons/menu.png"));
            Node imgInformacion = new ImageView(new Image("org/una/laboratorio/icons/informacion.png"));
            Node imgAdmin = new ImageView(new Image("org/una/laboratorio/icons/lengueta.png"));

            TreeItem<String> root = new TreeItem<>("Proyectos");
            root.setGraphic(imgroot);
            treeView.setRoot(root);

            for (ProyectoDTO proyecto : listProyectos) {
                TreeItem<String> item = new TreeItem<>(proyecto.getNombre());
                item.setGraphic(imgroot);
                root.getChildren().add(item);
                for (TareaDTO tarea : proyecto.getListTareas()) {
                    TreeItem<String> itemTarea = new TreeItem<>(tarea.getDescripcion());
                    itemTarea.setGraphic(imgAdmin);
                    item.getChildren().add(itemTarea);

                }

            }

//            for (int i = 0; i < ListPerOtor.size(); i++) {
//                if (ListPerOtor.get(i).getPermisoId().getCodigo().contains("TRA") && TreeUsu) {
//                    TreeItem<String> item = new TreeItem<>("Tipos de Trámites");
//                    itemInformacion.getChildren().add(item);
//                    TreeUsu = false;
//                }
//                if (ListPerOtor.get(i).getPermisoId().getCodigo().contains("DEP") && TreeDep) {
//                    TreeItem<String> item = new TreeItem<>("Departamentos");
//                    itemInformacion.getChildren().add(item);
//                    treeAcciones.getSelectionModel().select(item);
//                    TreeDep = false;
//                }
//                if (ListPerOtor.get(i).getPermisoId().getCodigo().contains("TRD") && TreeTra) {
//                    TreeItem<String> item = new TreeItem<>("Diseño de Trámites");
//                    itemInformacion.getChildren().add(item);
//                    treeView.getSelectionModel().select(item);
//                    TreeTra = false;
//                }
//
//            }
//
//            TreeUsu = true;
//            TreeDep = true;
//            TreeTra = true;
//
//            for (int i = 0; i < ListPerOtor.size(); i++) {
//                if (ListPerOtor.get(i).getPermisoId().getCodigo().contains("PER") && TreeUsu) {
//                    TreeItem<String> item = new TreeItem<>("Permisos");
//                    itemAdministracion.getChildren().add(item);
//                    TreeUsu = false;
//                }
//                if (ListPerOtor.get(i).getPermisoId().getCodigo().contains("USU") && TreeDep) {
//                    TreeItem<String> item = new TreeItem<>("Usuarios");
//                    itemAdministracion.getChildren().add(item);
//                    treeAcciones.getSelectionModel().select(item);
//                    TreeDep = false;
//                }
//                if (ListPerOtor.get(i).getPermisoId().getCodigo().contains("PAR") && TreeTra) {
//                    TreeItem<String> item = new TreeItem<>("Parametros");
//                    itemAdministracion.getChildren().add(item);
//                    treeAcciones.getSelectionModel().select(item);
//                    TreeTra = false;
//                }
//
//            }
//
//        } else {
//            TreeItem<String> root = new TreeItem<>((((UsuarioDTO) AppContext.getInstance().get("usuarioLog")).getNombreCompleto()) + " no posee permisos");
//            treeAcciones.setRoot(root);
        }

//        treeAcciones.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//
//                if (mouseEvent.getClickCount() == 2) {
//                    TreeItem<String> item = (TreeItem<String>) treeAcciones.getSelectionModel()
//                            .getSelectedItem();
//                    try {
//                        if (item.getValue().equals("Usuarios")) {
//                            cambiarUsuario("Informacion");
//
//                        } else if (item.getValue().equals("Departamentos")) {
//                            cambiarDepartamento("Departamentos");
//
//                        } else if (item.getValue().equals("Diseño de Trámites")) {
//                            cambiarDiseñoTramites("Tramites");
//
//                        } else if (item.getValue().equals("Permisos")) {
//                            cambiarPermisos();
//
//                        } else if (item.getValue().equals("Parametros")) {
//                            cambiarParametros();
//
//                        } else if (item.getValue().equals("Tipos de Trámites")) {
//                            cambiarTramites();
//                        }
//
//                    } catch (IOException ex) {
//                        Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
//
//            }
//        });
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
}
