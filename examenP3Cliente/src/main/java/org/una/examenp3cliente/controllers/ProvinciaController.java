/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.examenp3cliente.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeView;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TreeItem;
import javafx.stage.Stage;
import org.una.examenp3cliente.dtos.apiProvincias.CantonDTO;
import org.una.examenp3cliente.dtos.apiProvincias.DistritoDTO;
import org.una.examenp3cliente.dtos.apiProvincias.ProvinciaDTO;
import org.una.examenp3cliente.dtos.apiProvincias.UnidadDTO;
import org.una.examenp3cliente.entitiesServices.apiProvincias.CantonService;
import org.una.examenp3cliente.entitiesServices.apiProvincias.DistritoService;
import org.una.examenp3cliente.entitiesServices.apiProvincias.ProvinciaService;
import org.una.examenp3cliente.entitiesServices.apiProvincias.UnidadService;
import org.una.examenp3cliente.utils.FlowController;
import org.una.examenp3cliente.utils.Mensaje;

/**
 * FXML Controller class
 *
 * @author Bosco
 */
public class ProvinciaController extends Controller implements Initializable {

    @FXML
    private JFXTreeView<String> treeView;

    ProvinciaDTO pro;

    public List<ProvinciaDTO> provinciaList = new ArrayList<ProvinciaDTO>();

    public List<CantonDTO> cantonList = new ArrayList<CantonDTO>();
    public List<CantonDTO> cantonList2 = new ArrayList<CantonDTO>();

    public List<DistritoDTO> distritonList = new ArrayList<DistritoDTO>();
    public List<DistritoDTO> distritonList2 = new ArrayList<DistritoDTO>();

    public List<UnidadDTO> unidadnList = new ArrayList<UnidadDTO>();
    public List<UnidadDTO> unidadnList2 = new ArrayList<UnidadDTO>();

    public List<TreeItem> listaItemDistrito = new ArrayList<TreeItem>();
    public List<TreeItem> listaItemCanton = new ArrayList<TreeItem>();
    public List<TreeItem> listaItemProvincia = new ArrayList<TreeItem>();
    public List<TreeItem> padre = new ArrayList<TreeItem>();
    @FXML
    private JFXComboBox<String> combMayoMenor;
    @FXML
    private JFXTextField txtValor;
    @FXML
    private JFXTextField txtValor1;
    @FXML
    private JFXButton filtroProvincia;
    @FXML
    private JFXButton filtrarPoblacion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        combMayoMenor.setItems(FXCollections.observableArrayList("mayor", "menor"));
        provinciaList = ProvinciaService.estado(true);
        cargarTreeView(provinciaList);

    }

    public void cargarTreeView(List<ProvinciaDTO> r) {
        provinciaList = r;
        Collections.sort(provinciaList, (o1, o2) -> o1.getCodigo().compareTo(o2.getCodigo()));
        TreeItem<String> root1 = new TreeItem<>("Provincias");
        root1.setExpanded(false);
        int cont111 = 0;
        double cont222 = 0;
        for (int i = 0; i < provinciaList.size(); i++) {
            TreeItem<String> root;
            cantonList = CantonService.provinciaIdCanton(provinciaList.get(i).getId());
            cantonList2 = new ArrayList<CantonDTO>();
            listaItemProvincia = new ArrayList<TreeItem>();
            for (CantonDTO CantonDTO : cantonList) {
                if (CantonDTO.getEstado() == true) {
                    cantonList2.add(CantonDTO);
                }
            }
            int contt = 0;
            double cont22 = 0;
            for (int j = 0; j < cantonList2.size(); j++) {

                TreeItem<String> item;
                distritonList = DistritoService.cantonesIddistrito(cantonList2.get(j).getId());
                distritonList2 = new ArrayList<DistritoDTO>();
                listaItemCanton = new ArrayList<TreeItem>();
                for (DistritoDTO DistritoDTO : distritonList) {
                    if (DistritoDTO.isEstado() == true) {
                        distritonList2.add(DistritoDTO);
                    }
                }
                int cont = 0;
                double cont2 = 0;
                for (int k = 0; k < distritonList2.size(); k++) {

                    TreeItem<String> item1;
                    unidadnList = UnidadService.distritoIdUnidad(distritonList2.get(k).getId());
                    unidadnList2 = new ArrayList<UnidadDTO>();
                    listaItemDistrito = new ArrayList<TreeItem>();
                    for (UnidadDTO UnidadDTO : unidadnList) {
                        if (UnidadDTO.isEstado() == true) {
                            unidadnList2.add(UnidadDTO);
                        }
                    }
                    for (int f = 0; f < unidadnList2.size(); f++) {
                        TreeItem<String> item2 = new TreeItem<>(unidadnList2.get(f).getNombreUnidad() + " [Unidad] " + "Población:" + unidadnList2.get(f).getPoblacion() + " Área Cuadrada:" + unidadnList2.get(f).getAreaCuadrada());
                        listaItemDistrito.add(item2);
                        cont = (int) (cont + unidadnList2.get(f).getPoblacion());
                        cont2 = cont2 + unidadnList2.get(f).getAreaCuadrada();
                    }
                    item1 = new TreeItem<>(distritonList2.get(k).getNombreDistrito() + " [Distrito] " + "Población:" + cont + " Área Cuadrada:" + cont2);
                    for (TreeItem tree : listaItemDistrito) {
                        item1.getChildren().addAll(tree);
                    }
                    listaItemCanton.add(item1);
                    contt = (int) (contt + cont);
                    cont22 = cont22 + cont2;
                    cont = 0;
                    cont2 = 0;
                }
                item = new TreeItem<>(cantonList2.get(j).getNombreCanton() + " [Cantón] " + "Población:" + contt + " Área Cuadrada:" + cont22);
                for (TreeItem tree : listaItemCanton) {
                    item.getChildren().addAll(tree);
                }
                listaItemProvincia.add(item);
                cont111 = (int) (cont111 + contt);
                cont222 = cont222 + cont22;
                contt = 0;
                cont22 = 0;
            }
            root = new TreeItem<>(provinciaList.get(i).getNombreProvincia() + " [Provincia] " + "Población:" + cont111 + " Área Cuadrada:" + cont222);
            for (TreeItem tree : listaItemProvincia) {
                root.getChildren().addAll(tree);
            }
            cont111 = 0;
            cont222 = 0;
            root1.getChildren().add(root);
        }
        treeView.setRoot(root1);
        txtValor.setText("");
        combMayoMenor.setValue(null);
        txtValor1.setText("");
    }

    public void CargarTreeViewMayor(String MayorMenor, int valor) {

        if (MayorMenor.equals("menor")) {
            int cont1111 = 0;
            provinciaList = ProvinciaService.estado(true);
            Collections.sort(provinciaList, (o1, o2) -> o1.getCodigo().compareTo(o2.getCodigo()));
            TreeItem<String> root1 = new TreeItem<>("Provincias");
            root1.setExpanded(false);
            int cont111 = 0;
            double cont222 = 0;
            for (int i = 0; i < provinciaList.size(); i++) {
                TreeItem<String> root;
                cantonList = CantonService.provinciaIdCanton(provinciaList.get(i).getId());
                cantonList2 = new ArrayList<CantonDTO>();
                listaItemProvincia = new ArrayList<TreeItem>();
                for (CantonDTO CantonDTO : cantonList) {
                    if (CantonDTO.getEstado() == true) {
                        cantonList2.add(CantonDTO);
                    }
                }
                int contt = 0;
                double cont22 = 0;
                for (int j = 0; j < cantonList2.size(); j++) {

                    TreeItem<String> item;
                    distritonList = DistritoService.cantonesIddistrito(cantonList2.get(j).getId());
                    distritonList2 = new ArrayList<DistritoDTO>();
                    listaItemCanton = new ArrayList<TreeItem>();
                    for (DistritoDTO DistritoDTO : distritonList) {
                        if (DistritoDTO.isEstado() == true) {
                            distritonList2.add(DistritoDTO);
                        }
                    }
                    int cont = 0;
                    double cont2 = 0;
                    for (int k = 0; k < distritonList2.size(); k++) {

                        TreeItem<String> item1;
                        unidadnList = UnidadService.distritoIdUnidad(distritonList2.get(k).getId());
                        unidadnList2 = new ArrayList<UnidadDTO>();
                        listaItemDistrito = new ArrayList<TreeItem>();
                        for (UnidadDTO UnidadDTO : unidadnList) {
                            if (UnidadDTO.isEstado() == true) {
                                unidadnList2.add(UnidadDTO);
                            }
                        }
                        for (int f = 0; f < unidadnList2.size(); f++) {

                            if (unidadnList2.get(f).getPoblacion() < valor) {
                                TreeItem<String> item2 = new TreeItem<>(unidadnList2.get(f).getNombreUnidad() + " [Unidad] " + "Población:" + unidadnList2.get(f).getPoblacion() + " Área Cuadrada:" + unidadnList2.get(f).getAreaCuadrada());
                                listaItemDistrito.add(item2);
                                cont = (int) (cont + unidadnList2.get(f).getPoblacion());
                                cont2 = cont2 + unidadnList2.get(f).getAreaCuadrada();
                            }
                        }

                        contt = (int) (contt + cont);
                        cont22 = cont22 + cont2;
                        if (cont < valor && cont != 0) {
                            item1 = new TreeItem<>(distritonList2.get(k).getNombreDistrito() + " [Distrito] " + "Población:" + cont + " Área Cuadrada:" + cont2);
                            for (TreeItem tree : listaItemDistrito) {
                                item1.getChildren().addAll(tree);
                            }
                            listaItemCanton.add(item1);
                        }
                        cont = 0;
                        cont2 = 0;
                    }
                    cont111 = (int) (cont111 + contt);
                    cont222 = cont222 + cont22;
                    if (contt < valor && contt != 0) {
                        item = new TreeItem<>(cantonList2.get(j).getNombreCanton() + " [Cantón] " + "Población:" + contt + " Área Cuadrada:" + cont22);
                        for (TreeItem tree : listaItemCanton) {
                            item.getChildren().addAll(tree);
                        }
                        listaItemProvincia.add(item);
                    }
                    contt = 0;
                    cont22 = 0;
                }
                cont1111 = (int) (cont1111 + contt);
                if (cont111 < valor && cont111 != 0) {
                    root = new TreeItem<>(provinciaList.get(i).getNombreProvincia() + " [Provincia] " + "Población:" + cont111 + " Área Cuadrada:" + cont222);
                    for (TreeItem tree : listaItemProvincia) {
                        root.getChildren().addAll(tree);
                    }
                    root1.getChildren().addAll(root);
                }
                cont111 = 0;
                cont222 = 0;

            }
            treeView.setRoot(root1);
        } else {
            int cont1111 = 0;
            provinciaList = ProvinciaService.estado(true);
            Collections.sort(provinciaList, (o1, o2) -> o1.getCodigo().compareTo(o2.getCodigo()));
            TreeItem<String> root1 = new TreeItem<>("Provincias");
            root1.setExpanded(false);
            int cont111 = 0;
            double cont222 = 0;
            for (int i = 0; i < provinciaList.size(); i++) {
                TreeItem<String> root;
                cantonList = CantonService.provinciaIdCanton(provinciaList.get(i).getId());
                cantonList2 = new ArrayList<CantonDTO>();
                listaItemProvincia = new ArrayList<TreeItem>();
                for (CantonDTO CantonDTO : cantonList) {
                    if (CantonDTO.getEstado() == true) {
                        cantonList2.add(CantonDTO);
                    }
                }
                int contt = 0;
                double cont22 = 0;
                for (int j = 0; j < cantonList2.size(); j++) {

                    TreeItem<String> item;
                    distritonList = DistritoService.cantonesIddistrito(cantonList2.get(j).getId());
                    distritonList2 = new ArrayList<DistritoDTO>();
                    listaItemCanton = new ArrayList<TreeItem>();
                    for (DistritoDTO DistritoDTO : distritonList) {
                        if (DistritoDTO.isEstado() == true) {
                            distritonList2.add(DistritoDTO);
                        }
                    }
                    int cont = 0;
                    double cont2 = 0;
                    for (int k = 0; k < distritonList2.size(); k++) {

                        TreeItem<String> item1;
                        unidadnList = UnidadService.distritoIdUnidad(distritonList2.get(k).getId());
                        unidadnList2 = new ArrayList<UnidadDTO>();
                        listaItemDistrito = new ArrayList<TreeItem>();
                        for (UnidadDTO UnidadDTO : unidadnList) {
                            if (UnidadDTO.isEstado() == true) {
                                unidadnList2.add(UnidadDTO);
                            }
                        }
                        for (int f = 0; f < unidadnList2.size(); f++) {

                            if (unidadnList2.get(f).getPoblacion() > valor) {
                                TreeItem<String> item2 = new TreeItem<>(unidadnList2.get(f).getNombreUnidad() + " [Unidad] " + "Población:" + unidadnList2.get(f).getPoblacion() + " Área Cuadrada:" + unidadnList2.get(f).getAreaCuadrada());
                                listaItemDistrito.add(item2);
                                cont = (int) (cont + unidadnList2.get(f).getPoblacion());
                                cont2 = cont2 + unidadnList2.get(f).getAreaCuadrada();
                            }
                        }

                        contt = (int) (contt + cont);
                        cont22 = cont22 + cont2;
                        if (cont > valor && cont != 0) {
                            item1 = new TreeItem<>(distritonList2.get(k).getNombreDistrito() + " [Distrito] " + "Población:" + cont + " Área Cuadrada:" + cont2);
                            for (TreeItem tree : listaItemDistrito) {
                                item1.getChildren().addAll(tree);
                            }
                            listaItemCanton.add(item1);
                        }
                        cont = 0;
                        cont2 = 0;
                    }
                    cont111 = (int) (cont111 + contt);
                    cont222 = cont222 + cont22;
                    if (contt > valor && contt != 0) {
                        item = new TreeItem<>(cantonList2.get(j).getNombreCanton() + " [Cantón] " + "Población:" + contt + " Área Cuadrada:" + cont22);
                        for (TreeItem tree : listaItemCanton) {
                            item.getChildren().addAll(tree);
                        }
                        listaItemProvincia.add(item);
                    }
                    contt = 0;
                    cont22 = 0;
                }
                cont1111 = (int) (cont1111 + contt);
                if (cont111 > valor && cont111 != 0) {
                    root = new TreeItem<>(provinciaList.get(i).getNombreProvincia() + " [Provincia] " + "Población:" + cont111 + " Área Cuadrada:" + cont222);
                    for (TreeItem tree : listaItemProvincia) {
                        root.getChildren().addAll(tree);
                    }
                    root1.getChildren().addAll(root);
                }
                cont111 = 0;
                cont222 = 0;

            }
            treeView.setRoot(root1);
        }
        txtValor.setText("");
        combMayoMenor.setValue(null);
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
    private void Limpiar(ActionEvent event) {
        provinciaList=ProvinciaService.estado(true);
        cargarTreeView(provinciaList);
    }

    @FXML
    private void filtrarProvincia(ActionEvent event) {
        if (!txtValor1.getText().isEmpty()) {
            provinciaList = new ArrayList<ProvinciaDTO>();
            provinciaList.clear();
            provinciaList = ProvinciaService.estado(true);
            for (ProvinciaDTO provinciaDTO : provinciaList) {
                if (provinciaDTO.getNombreProvincia().equals(txtValor1.getText())) {
                    provinciaList = new ArrayList<ProvinciaDTO>();
                    provinciaList.add(provinciaDTO);
                }
            }
            if (provinciaList != null) {
                cargarTreeView(provinciaList);
            } else {
                new Mensaje().showModal(Alert.AlertType.INFORMATION, "Error al filtrar", ((Stage) txtValor.getScene().getWindow()), "No se encontró la provincia");
            }

        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Error al filtrar", ((Stage) txtValor.getScene().getWindow()), "Rellene los campos necesarios");
        }
    }

    @FXML
    private void filtrarPoblacion(ActionEvent event) {
        int numEntero = Integer.parseInt(txtValor.getText());

        if (!combMayoMenor.getValue().isEmpty() && !txtValor.getText().isEmpty()) {
            if (combMayoMenor.getValue().equals("mayor")) {
                CargarTreeViewMayor("mayor", numEntero);
            } else {
                CargarTreeViewMayor("menor", numEntero);
            }
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Error al filtrar", ((Stage) txtValor.getScene().getWindow()), "Rellene los campos necesarios");
        }
    }

}
