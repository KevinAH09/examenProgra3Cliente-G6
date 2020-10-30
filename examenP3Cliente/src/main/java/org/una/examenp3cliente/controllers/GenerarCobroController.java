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
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.una.examenp3cliente.dtos.apiCobros.ClienteDTO;
import org.una.examenp3cliente.dtos.apiCobros.CobroDTO;
import org.una.examenp3cliente.dtos.apiCobros.MembresiaDTO;
import org.una.examenp3cliente.entitiesServices.apiCobros.ClienteService;
import org.una.examenp3cliente.entitiesServices.apiCobros.CobrosService;
import org.una.examenp3cliente.entitiesServices.apiCobros.MembresiaService;
import org.una.examenp3cliente.utils.FlowController;

/**
 * FXML Controller class
 *
 * @author cfugu
 */
public class GenerarCobroController extends Controller implements Initializable {

    @FXML
    private JFXTextField txtBusqueda;
    @FXML
    private JFXComboBox<String> cmbBusqueda;
    @FXML
    private JFXButton btnFiltrar;
    @FXML
    private TableView<ClienteDTO> tableView;
    @FXML
    private JFXTextField txtId;
    @FXML
    private JFXTextField txtIdentificacion;
    @FXML
    private JFXTextField txtNombre;
    @FXML
    private JFXTextField txtTelefono;
    @FXML
    private JFXTextField txtDescripcion;
    @FXML
    private JFXTextField txtPeridiocidad;
    @FXML
    private JFXTextField txtMonto;
    @FXML
    private JFXButton btnVolver;
    @FXML
    private JFXButton btnCobro;
    @FXML
    private JFXComboBox<MembresiaDTO> cmbMembresia;
    @FXML
    private Pane paneNotificar;
    @FXML
    private HBox hboxProgress;
    
    public List<ClienteDTO> clientesList = new ArrayList<ClienteDTO>();
    public List<MembresiaDTO> membresiaList = new ArrayList<MembresiaDTO>();
    public List<CobroDTO> cobroList = new ArrayList<CobroDTO>();
    public ClienteDTO clientesFilt = new ClienteDTO();
    public ClienteDTO data = new ClienteDTO();
    public MembresiaDTO membresiaFilt = new MembresiaDTO();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtId.setDisable(true);
        txtNombre.setDisable(true);
        txtTelefono.setDisable(true);
        txtIdentificacion.setDisable(true);
        paneNotificar.setVisible(false);

        txtDescripcion.setDisable(true);
        txtPeridiocidad.setDisable(true);
        txtMonto.setDisable(true);
        cmbBusqueda.setItems(FXCollections.observableArrayList("Todos", "Identificacion"));
        notificar(1);

        cmbMembresia.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends MembresiaDTO> ov, MembresiaDTO t, MembresiaDTO t1) -> {
            txtDescripcion.setText(t1.getDescripcion());
            txtPeridiocidad.setText(t1.getPeriodicidad());
            txtMonto.setText(t1.getMonto().toString());
            membresiaFilt = t1;
            verificar(data.getIdentificacion(), t1.getDescripcion());
        });
    }

    @FXML
    private void onActionFiltrar(ActionEvent event) {
        if (cmbBusqueda.getValue() != null) {
            if (cmbBusqueda.getValue().equals("Todos")) {
                clientesList = ClienteService.allCliente();
                clientesList.sort(Comparator.comparing(ClienteDTO::getNombre));
                if (clientesList != null) {
                    tableView.getItems().clear();
                    tableView.getColumns().clear();

                    InicializarTableView();
                    tableView.setItems(FXCollections.observableArrayList(clientesList));
                    addButtonToTable();
                } else {
                    notificar(0);
                }
            }
            if (cmbBusqueda.getValue().equals("Identificacion") && !txtBusqueda.getText().isEmpty()) {
                clientesFilt = ClienteService.identificacionCliente(txtBusqueda.getText());
                if (clientesFilt != null) {
                    tableView.getItems().clear();
                    tableView.getColumns().clear();

                    InicializarTableView();
                    tableView.setItems(FXCollections.observableArrayList(clientesFilt));
                    addButtonToTable();
                } else {
                    notificar(0);
                }
            }
        }

    }

    @FXML
    private void onActionVolver(ActionEvent event) {
        FlowController.getInstance().goView("cobro/MenuCobrosPendientes");
    }

    @FXML
    private void onActionGenerar(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Se va a generar una cantidad grande de cobros, realmente desea generar el cobro?", ButtonType.YES, ButtonType.NO
        );
        ButtonType result = alert.showAndWait().orElse(ButtonType.YES);

        if (ButtonType.YES.equals(result)) {
            btnCobro.setDisable(true);
            FlowController.getInstance().goViewInWindowModal2("auxiliarCobros/AuxiliarCobros", ((Stage) txtNombre.getScene().getWindow()), false);
        }
        btnCobro.setDisable(false);

    }

    private void InicializarTableView() {

        TableColumn<ClienteDTO, String> colNombre = new TableColumn("Nombre");
        colNombre.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getNombre()));
        TableColumn<ClienteDTO, String> colIdentificacion = new TableColumn("Identificación");
        colIdentificacion.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getIdentificacion()));
        TableColumn<ClienteDTO, String> colTelefono = new TableColumn("Telefono");
        colTelefono.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getTelefono()));
        tableView.getColumns().addAll(colNombre, colIdentificacion, colTelefono);
    }

    private void notificar(int num) {
        tableView.getItems().clear();
        if (num == 1) {
            ImageView imageView = new ImageView(new Image("org/una/examenp3cliente/views/shared/info.png"));
            Text lab = new Text("Para mostrar datos en este apartado debe realizar el filtro correspondiente");
            lab.setFill(Color.web("#0076a3"));
            VBox box = new VBox();
            box.setAlignment(Pos.CENTER);
            box.getChildren().add(imageView);
            box.getChildren().add(lab);
            tableView.setPlaceholder(box);
        } else {
            ImageView imageView2 = new ImageView(new Image("org/una/examenp3cliente/views/shared/warning.png"));
            Text lab = new Text("No se encontro coincidencias");
            lab.setFill(Color.web("#0076a3"));
            VBox box = new VBox();
            box.setAlignment(Pos.CENTER);
            box.getChildren().add(imageView2);
            box.getChildren().add(lab);
            tableView.setPlaceholder(box);
        }
    }

    private void addButtonToTable() {
        TableColumn<ClienteDTO, Void> colBtn = new TableColumn("Acción");

        Callback<TableColumn<ClienteDTO, Void>, TableCell<ClienteDTO, Void>> cellFactory = new Callback<TableColumn<ClienteDTO, Void>, TableCell<ClienteDTO, Void>>() {
            @Override
            public TableCell<ClienteDTO, Void> call(final TableColumn<ClienteDTO, Void> param) {
                final TableCell<ClienteDTO, Void> cell = new TableCell<ClienteDTO, Void>() {

                    private final JFXButton btn = new JFXButton("Seleccionar");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            data = getTableView().getItems().get(getIndex());
                            seleccionar(data);
                            System.out.println("selectedData: " + data.getNombre());
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);

        tableView.getColumns().add(colBtn);

    }

    private void seleccionar(ClienteDTO cliente) {
        txtId.setText(cliente.getId().toString());
        txtNombre.setText(cliente.getNombre());
        txtTelefono.setText(cliente.getTelefono());
        txtIdentificacion.setText(cliente.getIdentificacion());
        membresiaList = MembresiaService.idClienteMembresia(Long.valueOf(cliente.getId()));
        System.out.println(membresiaList.size());
        if (membresiaList != null) {
            cmbMembresia.setItems(FXCollections.observableArrayList(membresiaList));
        }

    }

    public void verificar(String identificacion, String tipo) {
        cobroList = null;
        cobroList = CobrosService.identificacionTipoClienteCobros(identificacion, tipo);
        if (cobroList.size() > 0) {
            paneNotificar.setVisible(true);
        } else {
            paneNotificar.setVisible(false);
        }
    }

    public void limpiar() {
        txtDescripcion.clear();
        txtId.clear();
        txtIdentificacion.clear();
        txtMonto.clear();
        txtNombre.clear();
        txtPeridiocidad.clear();
        txtTelefono.clear();
        cmbMembresia.getItems().clear();

    }

    @Override
    public void initialize() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
