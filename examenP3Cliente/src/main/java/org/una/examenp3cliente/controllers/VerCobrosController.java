/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.examenp3cliente.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeView;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.una.examenp3cliente.dtos.apiCobros.ClienteDTO;
import org.una.examenp3cliente.dtos.apiCobros.CobroDTO;
import org.una.examenp3cliente.dtos.apiCobros.MembresiaDTO;
import org.una.examenp3cliente.entitiesServices.apiCobros.ClienteService;
import org.una.examenp3cliente.entitiesServices.apiCobros.CobrosService;
import org.una.examenp3cliente.entitiesServices.apiCobros.MembresiaService;
import org.una.examenp3cliente.utils.FlowController;
import org.una.examenp3cliente.utils.Mensaje;

/**
 * FXML Controller class
 *
 * @author cfugu
 */
public class VerCobrosController extends Controller implements Initializable {

    @FXML
    private JFXTextField txtBusqueda;
    @FXML
    private JFXComboBox<String> cmbBusqueda;
    @FXML
    private JFXButton btnFiltrar;
    @FXML
    private JFXTreeView<String> treeview;
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    public List<ClienteDTO> clientesList = new ArrayList<ClienteDTO>();
    public ClienteDTO clientesFilt = new ClienteDTO();
    public List<MembresiaDTO> membresiaList = new ArrayList<MembresiaDTO>();
    public List<CobroDTO> cobrosList = new ArrayList<CobroDTO>();
    @FXML
    private StackPane stack;
    VBox box2 = new VBox();
    VBox box1 = new VBox();
    @FXML
    private HBox hboxDates;
    @FXML
    private JFXDatePicker fInicio;
    @FXML
    private JFXDatePicker fFin;
    List<CobroDTO> filtered = new ArrayList<CobroDTO>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbBusqueda.setItems(FXCollections.observableArrayList("Todos", "Identificacion", "Fechas"));
        notificar();
        box1.setVisible(true);
        cmbBusqueda.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {//Para obtener en tiempo real el cambio de seleccion de jun item en el combobox
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
                if (t1 == "Todos") {
                    hboxDates.setDisable(true);
                    hboxDates.setVisible(false);
                    txtBusqueda.setDisable(true);
                    txtBusqueda.setVisible(true);
                }
                if (t1 == "Identificacion") {
                    hboxDates.setDisable(true);
                    hboxDates.setVisible(false);
                    txtBusqueda.setDisable(false);
                    txtBusqueda.setVisible(true);
                }
                if (t1 == "Fechas") {
                    hboxDates.setDisable(false);
                    hboxDates.setVisible(true);
                    txtBusqueda.setDisable(true);
                    txtBusqueda.setVisible(false);
                }
            }
        }
        );
    }

    @FXML
    private void onActionFiltrar(ActionEvent event) {
        if (cmbBusqueda.getValue() != null) {
            clientesList = new ArrayList<ClienteDTO>();
            if (cmbBusqueda.getValue().equals("Todos")) {
                clientesList = ClienteService.allCliente();
                if (clientesList != null) {
                    box1.setVisible(false);
                    box2.setVisible(false);
                    treeview.setVisible(true);
                    llenarTree(false);
                } else {
                    treeview.setVisible(false);
                    box2.setVisible(true);
                    box1.setVisible(false);
                }
            }
            if (cmbBusqueda.getValue() != null && fInicio.getValue() != null && fFin.getValue() != null) {
                clientesList = new ArrayList<ClienteDTO>();
                if (cmbBusqueda.getValue().equals("Fechas")) {
                    clientesList = ClienteService.allCliente();
                    if (clientesList != null) {
                        box1.setVisible(false);
                        box2.setVisible(false);
                        treeview.setVisible(true);
                        llenarTree(true);
                    } else {
                        treeview.setVisible(false);
                        box2.setVisible(true);
                        box1.setVisible(false);
                    }
                }
            }
            if (cmbBusqueda.getValue().equals("Identificacion")) {
                clientesFilt = ClienteService.identificacionCliente(txtBusqueda.getText());
                if (clientesFilt != null) {
                    box1.setVisible(false);
                    box2.setVisible(false);
                    clientesList.add(clientesFilt);
                    treeview.setVisible(true);
                    llenarTree(false);
                } else {

                    treeview.setVisible(false);
                    box2.setVisible(true);
                    box1.setVisible(false);
                }
            }
        }
    }

    private void llenarTree(Boolean band) {
        TreeItem<String> root = new TreeItem<>("Datos Generales de cobros");
        treeview.setRoot(root);
        TreeItem<String> inicio = new TreeItem<>("Cliente");
        root.getChildren().add(inicio);
        if (clientesList != null) {
            clientesList.sort(Comparator.comparing(ClienteDTO::getNombre));
            for (ClienteDTO clienteDTO : clientesList) {
                String title = clienteDTO.getNombre();
                TreeItem<String> item = new TreeItem<>(title);
                inicio.getChildren().add(item);

                TreeItem<String> item2 = new TreeItem<>("Información del Cliente");
                item.getChildren().add(item2);

                item2.getChildren().add(new TreeItem("Identificación: " + clienteDTO.getIdentificacion()));
                item2.getChildren().add(new TreeItem("Teléfono: " + clienteDTO.getTelefono()));

                TreeItem<String> item3 = new TreeItem<>("Información de la o las Membresías");
                item.getChildren().add(item3);
                membresiaList = MembresiaService.idClienteMembresia(Long.valueOf(clienteDTO.getId()));
                if (membresiaList != null) {
                    for (MembresiaDTO membresiaDTO : membresiaList) {

                        TreeItem<String> itemMembresia = new TreeItem<>(membresiaDTO.getDescripcion());
                        item3.getChildren().add(itemMembresia);
                        itemMembresia.getChildren().add(new TreeItem("Periodicidad: " + membresiaDTO.getPeriodicidad()));
                        itemMembresia.getChildren().add(new TreeItem("Monto: " + membresiaDTO.getMonto()));
                    }
                } else {
                    TreeItem<String> itemMembresia = new TreeItem<>("No hay membresias asociadas a este usuario");
                    item3.getChildren().add(itemMembresia);
                }
                TreeItem<String> item4 = new TreeItem<>("Información de Cobros Pendientes");
                item.getChildren().add(item4);

                cobrosList = CobrosService.idClienteCobros(Long.valueOf(clienteDTO.getId()));
                if (cobrosList != null) {
                    if (band) {
                        Date ini = new Date();
                        Date fina = new Date();

                        ini = Date.from(fInicio.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                        fina = Date.from(fFin.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                        if (ini.before(fina)) {
                            cobrosList = null;
                        } else {
                            filtered = new ArrayList<CobroDTO>();
                            for (CobroDTO cobroDTO : cobrosList) {
                                if (cobroDTO.getFechaVencimiento().after(ini) && cobroDTO.getFechaVencimiento().before(fina)) {
                                    filtered.add(cobroDTO);
                                }
                            }
                            cobrosList = filtered;
                        }

                    }
                    if (cobrosList != null) {
                        cobrosList.sort(Comparator.comparing(CobroDTO::getFechaVencimiento));
                        for (CobroDTO cobroDTO : cobrosList) {
                            TreeItem<String> itemCobro = new TreeItem<>("Fecha vencimiento: " + formatter.format(cobroDTO.getFechaVencimiento()));
                            item4.getChildren().add(itemCobro);
                            itemCobro.getChildren().add(new TreeItem("Año: " + cobroDTO.getAnno()));
                            itemCobro.getChildren().add(new TreeItem("Período: " + cobroDTO.getPeriodo()));
                            itemCobro.getChildren().add(new TreeItem("Tipo servicio: " + cobroDTO.getTipo()));
                            itemCobro.getChildren().add(new TreeItem("Monto: " + cobroDTO.getMonto()));

                        }
                    } else {
                        new Mensaje().showModal(Alert.AlertType.ERROR, "Error en fechas", ((Stage) txtBusqueda.getScene().getWindow()), "No se puede tener la fecha final menor o igual que la inicial");
                        TreeItem<String> itemCobro = new TreeItem<>("No hay cobros  para mostrar");
                        item4.getChildren().add(itemCobro);
                    }
                } else {
                    TreeItem<String> itemCobro = new TreeItem<>("No existen cobros registrados para este usuario");
                    item4.getChildren().add(itemCobro);
                }
                treeview.getSelectionModel().select(item);
            }
        }

    }
//Metodo para notificar graficamente cuando no hay resultados de los filtros

    private void notificar() {
        treeview.setVisible(false);

        ImageView imageView = new ImageView(new Image("org/una/examenp3cliente/views/shared/info2.png"));
        Text lab = new Text("Para mostrar datos en este apartado debe realizar el filtro correspondiente");
        lab.setFill(Color.web("#ffffff"));
        box1 = new VBox();
        box1.setAlignment(Pos.CENTER);
        box1.getChildren().add(imageView);
        box1.getChildren().add(lab);
        stack.getChildren().add(box1);
        StackPane.setAlignment(box1, Pos.CENTER_LEFT);

        ImageView imageView2 = new ImageView(new Image("org/una/examenp3cliente/views/shared/hazard.png"));
        Text lab2 = new Text("No se encontro coincidencias");
        lab2.setFill(Color.web("#ffffff"));
        box2 = new VBox();
        box2.setAlignment(Pos.CENTER);
        box2.getChildren().add(imageView2);
        box2.getChildren().add(lab2);
        stack.getChildren().add(box2);
        StackPane.setAlignment(box2, Pos.CENTER_LEFT);

        box1.setVisible(false);
        box2.setVisible(false);
    }

    @Override
    public void initialize() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void onActionVolverMenu(ActionEvent event) {
        FlowController.getInstance().goView("cobro/MenuCobrosPendientes");
    }

}
