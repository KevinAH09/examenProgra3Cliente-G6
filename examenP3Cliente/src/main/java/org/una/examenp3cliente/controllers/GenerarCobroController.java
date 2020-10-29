/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.examenp3cliente.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
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
import org.una.examenp3cliente.utils.Mensaje;

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
    public List<ClienteDTO> clientesList = new ArrayList<ClienteDTO>();
    public List<MembresiaDTO> membresiaList = new ArrayList<MembresiaDTO>();
    public List<CobroDTO> cobroList = new ArrayList<CobroDTO>();
    public ClienteDTO clientesFilt = new ClienteDTO();
    public ClienteDTO data = new ClienteDTO();
    public MembresiaDTO membresiaFilt = new MembresiaDTO();
    public CobroDTO cobroDTO = new CobroDTO();
    public CobroDTO cobroDTO2 = new CobroDTO();
    Date date = new Date();
    @FXML
    private JFXComboBox<String> cmbPeriodos;

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
            cargarPeriodos(t1.getPeriodicidad());
            membresiaFilt = t1;
            verificar(data.getIdentificacion(), t1.getDescripcion());
        });
    }

    @FXML
    private void onActionFiltrar(ActionEvent event) {
        if (cmbBusqueda.getValue().equals("Todos")) {
            clientesList = ClienteService.allCliente();
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

    @FXML
    private void onActionVolver(ActionEvent event) {
        FlowController.getInstance().goView("cobro/MenuCobrosPendientes");
    }

    @FXML
    private void onActionGenerar(ActionEvent event) {
        if (cmbPeriodos.getValue() != null && cmbMembresia.getValue() != null) {
            System.out.println("Generó");
            date = new Date();
            if (membresiaFilt.getPeriodicidad().equals("Anual")) {
                if (cmbPeriodos.getValue().equals("Anual")) {
                    guardarCobros(date, 1, 12, membresiaFilt.getMonto());
                }
                if (cmbPeriodos.getValue().equals("Mensual")) {
                    guardarCobros(date, 12, 1, (membresiaFilt.getMonto() / 12));
                }
                if (cmbPeriodos.getValue().equals("Bimestral")) {
                    guardarCobros(date, (6), 2, (membresiaFilt.getMonto() / 6));
                }
                if (cmbPeriodos.getValue().equals("Trimestral")) {
                    guardarCobros(date, (4), 3, (membresiaFilt.getMonto() / 4));
                }
                if (cmbPeriodos.getValue().equals("Cuatrimestral")) {
                    guardarCobros(date, (3), 4, (membresiaFilt.getMonto() / 3));
                }
                if (cmbPeriodos.getValue().equals("Semestral")) {
                    guardarCobros(date, (2), 6, (membresiaFilt.getMonto() / 2));
                }
            }
            if (membresiaFilt.getPeriodicidad().equals("Mensual")) {
                if (cmbPeriodos.getValue().equals("Mensual")) {
                    guardarCobros(date, 1, 1, membresiaFilt.getMonto());
                }
            }
            if (membresiaFilt.getPeriodicidad().equals("Bimestral")) {
                if (cmbPeriodos.getValue().equals("Mensual")) {
                    guardarCobros(date, 2, 1, (membresiaFilt.getMonto() / 2));
                }
                if (cmbPeriodos.getValue().equals("Bimestral")) {
                    guardarCobros(date, 1, 2, membresiaFilt.getMonto());
                }
            }
            if (membresiaFilt.getPeriodicidad().equals("Trimestral")) {
                if (cmbPeriodos.getValue().equals("Mensual")) {
                    guardarCobros(date, 3, 1, (membresiaFilt.getMonto() / 3));
                }
                if (cmbPeriodos.getValue().equals("Trimestral")) {
                    guardarCobros(date, 1, 3, membresiaFilt.getMonto());
                }

            }
            if (membresiaFilt.getPeriodicidad().equals("Cuatrimestral")) {
                if (cmbPeriodos.getValue().equals("Mensual")) {
                    guardarCobros(date, 4, 1, (membresiaFilt.getMonto() / 4));
                }
                if (cmbPeriodos.getValue().equals("Bimestral")) {
                    guardarCobros(date, 2, 2, (membresiaFilt.getMonto() / 2));
                }
                if (cmbPeriodos.getValue().equals("Cuatrimestral")) {
                    guardarCobros(date, 1, 4, membresiaFilt.getMonto());

                }
            }
            if (membresiaFilt.getPeriodicidad().equals("Semestral")) {
                if (cmbPeriodos.getValue().equals("Mensual")) {
                    guardarCobros(date, 6, 1, (membresiaFilt.getMonto() / 6));
                }
                if (cmbPeriodos.getValue().equals("Bimestral")) {
                    guardarCobros(date, 3, 2, (membresiaFilt.getMonto() / 3));
                }
                if (cmbPeriodos.getValue().equals("Trimestral")) {
                    guardarCobros(date, 2, 3, (membresiaFilt.getMonto() / 2));
                }
                if (cmbPeriodos.getValue().equals("Semestral")) {
                    guardarCobros(date, 1, 6, membresiaFilt.getMonto());
                }
            }
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Error al generar cobros pendientes", ((Stage) txtNombre.getScene().getWindow()), "No se generó el cobro correctamente, ya que debe seleccionar una membresia y un período, por favor verifique los datos.");
        }
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

    public void FechaVencimiento(Date dat, int dias) {
        Calendar hoy = Calendar.getInstance();
        hoy.setTime(date);
        hoy.add(Calendar.DATE, dias);
        date = hoy.getTime();
    }

    public void verificar(String identificacion, String tipo) {
        cobroList = null;
        cobroList = CobrosService.identificacionTipoClienteCobros(identificacion, tipo);
        if (cobroList.size() > 0) {
            btnCobro.setDisable(true);
            paneNotificar.setVisible(true);
        } else {
            btnCobro.setDisable(false);
            paneNotificar.setVisible(false);
        }
    }

    public void guardarCobros(Date dates, int cantidad, int cantDias, Double monto) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Se va a generar " + cantidad + " cobros por un monto de " + monto + " colones, realmente desea generar el cobro?", ButtonType.YES, ButtonType.NO);
        ButtonType result = alert.showAndWait().orElse(ButtonType.YES);

        if (ButtonType.YES.equals(result)) {
            Calendar fecha = Calendar.getInstance();
            boolean band = true;
            for (int i = 0; i < cantidad && band == true; i++) {
                if (i == 0) {
                    int dias = 30 * cantDias;
                    FechaVencimiento(dates, dias);
                    cobroDTO = new CobroDTO();
                    fecha.setTime(dates);
                } else {
                    int dias = 30 * cantDias;
                    FechaVencimiento(date, dias);
                    cobroDTO = new CobroDTO();
                    fecha.setTime(date);
                }
                BigDecimal formatNumber = new BigDecimal(monto);
                formatNumber = formatNumber.setScale(2, RoundingMode.UP);

                cobroDTO.setAnno(String.valueOf(fecha.get(Calendar.YEAR)));
                cobroDTO.setClientesId(data);
                cobroDTO.setFechaVencimiento(date);
                cobroDTO.setMonto(formatNumber.doubleValue());
                cobroDTO.setPeriodo(cmbPeriodos.getValue());
                cobroDTO.setTipo(membresiaFilt.getDescripcion());
                cobroDTO2 = CobrosService.createCobros(cobroDTO);
                if (cobroDTO2 == null) {
                    band = false;
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Error al guardar cobros pendientes", ((Stage) txtNombre.getScene().getWindow()), "No se guardó el cobro correctamente");
                }
                System.out.println(cobroDTO);
            }
            if (cobroDTO2 != null) {
                new Mensaje().showModal(Alert.AlertType.CONFIRMATION, "Guardar cobros pendientes", ((Stage) txtNombre.getScene().getWindow()), "Se guardó el cobro correctamente");
                limpiar();
            }
        }

    }

    public void cargarPeriodos(String per) {
        System.out.println("Entró");
        cmbPeriodos.getItems().clear();
        if ("Anual".equals(per)) {
            cmbPeriodos.setItems(FXCollections.observableArrayList("Mensual", "Bimestral", "Trimestral", "Cuatrimestral", "Semestral", "Anual"));
        }
        if ("Semestral".equals(per)) {
            cmbPeriodos.setItems(FXCollections.observableArrayList("Mensual", "Bimestral", "Trimestral", "Semestral"));
        }
        if ("Cuatrimestral".equals(per)) {
            cmbPeriodos.setItems(FXCollections.observableArrayList("Mensual", "Bimestral", "Cuatrimestral"));
        }
        if ("Trimestral".equals(per)) {
            cmbPeriodos.setItems(FXCollections.observableArrayList("Mensual", "Trimestral"));
        }
        if ("Bimestral".equals(per)) {
            cmbPeriodos.setItems(FXCollections.observableArrayList("Mensual", "Bimestral"));
        }
        if ("Mensual".equals(per)) {
            cmbPeriodos.setItems(FXCollections.observableArrayList("Mensual"));
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
        cmbPeriodos.getItems().clear();
//        data = null;
//        membresiaFilt = null;
//        membresiaList=null;
    }

    @Override
    public void initialize() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
