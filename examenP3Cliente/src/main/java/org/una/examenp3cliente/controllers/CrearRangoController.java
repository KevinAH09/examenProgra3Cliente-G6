/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.examenp3cliente.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXSlider;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.una.examenp3cliente.utils.AppContext;
import org.una.examenp3cliente.utils.FlowController;
import org.una.examenp3cliente.utils.Mensaje;

/**
 * FXML Controller class
 *
 * @author colo7
 */
public class CrearRangoController extends Controller implements Initializable {

    @FXML
    private JFXSlider slider;
    @FXML
    private Label lblIni;
    @FXML
    private Label lblfin;
    @FXML
    private JFXColorPicker colorPicker;
    @FXML
    private JFXButton btnCrear;
    @FXML
    private GridPane gripPane;
    List<RangoExtendsAnchor> listrangoExtendsAnchor = new ArrayList();
    List<RangoExtendsAnchor> listrangoCopia = new ArrayList();
    int colunm = 2;
    int row = 2;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colorPicker.setValue(Color.BLACK);
        lblIni.setText(String.format("%.0f", slider.getMin()));
        lblfin.setText(String.format("%.0f", slider.getValue()));
        listrangoExtendsAnchor = (List<RangoExtendsAnchor>) AppContext.getInstance().get("listRangos");
        for (RangoExtendsAnchor rangoExtendsAnchor : listrangoExtendsAnchor) {
            listrangoCopia.add(rangoExtendsAnchor);
        }
        if (listrangoExtendsAnchor.size() > 0) {

            crearRangos(false);
        }
        System.out.println("org.una.examenp3cliente.controllers.CrearRangoController.initialize()");
    }

    @Override
    public void initialize() {
    }

    @FXML
    private void actionCrearRango(ActionEvent event) {

        if (!Double.valueOf(String.format("%.0f", slider.getMin())).equals(100)) {
            if (!Double.valueOf(String.format("%.0f", slider.getValue())).equals(Double.valueOf(String.format("%.0f", slider.getMin())))) {
                RangoExtendsAnchor rangoExtendsAnchor = new RangoExtendsAnchor(String.format("%.0f", slider.getValue()), String.format("%.0f", slider.getMin()), colorPicker.getValue());
                listrangoExtendsAnchor.add(rangoExtendsAnchor);
                crearRangos(true);
            } else {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Crear rango", (Stage) btnCrear.getScene().getWindow(), "Seleccione un rango mayor al anterior");
            }
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Crear rango", (Stage) btnCrear.getScene().getWindow(), "Llego al limite del rango");
        }
    }

    void crearRangos(boolean aux) {
        while (listrangoExtendsAnchor.size() > colunm * row) {
            colunm++;
            row++;
        }
        gripPane.getChildren().clear();
        int contList = 0;
        for (int i = 0; i < colunm; i++) {
            for (int j = 0; j < row; j++) {
                if (contList < listrangoExtendsAnchor.size()) {
                    gripPane.add(listrangoExtendsAnchor.get(contList), i, j);
                }
                contList++;
            }
        }
        if (aux) {
            slider.setMin(Double.valueOf(String.format("%.0f", slider.getValue())));
            lblIni.setText(String.format("%.0f", slider.getMin()));
            lblfin.setText(String.format("%.0f", slider.getValue()));
        } else {
            slider.setMin(listrangoExtendsAnchor.get(listrangoExtendsAnchor.size() - 1).getValorFin());
            slider.setValue(listrangoExtendsAnchor.get(listrangoExtendsAnchor.size() - 1).getValorFin());
            lblIni.setText(String.format("%.0f", slider.getMin()));
            lblfin.setText(String.format("%.0f", slider.getValue()));
        }
    }

    @FXML
    private void actionCancelarCambios(ActionEvent event) {
        if( new Mensaje().showConfirmation("Salir de la ventana rango", (Stage) btnCrear.getScene().getWindow(), "Desea salir sin guardar cambios"))
        AppContext.getInstance().set("listRangos", listrangoCopia);
        FlowController.getInstance().goView("tarea/Tarea");
    }

    @FXML
    private void actionGuardarCambios(ActionEvent event) {
        AppContext.getInstance().set("listRangos", listrangoExtendsAnchor);
        FlowController.getInstance().goView("tarea/Tarea");
    }

    @FXML
    private void actionDrag(MouseEvent event) {
        lblfin.setText(String.format("%.0f", slider.getValue()));
    }

    @FXML
    private void actionClick(MouseEvent event) {
        lblfin.setText(String.format("%.0f", slider.getValue()));
    }

    @FXML
    private void actionLimpiarCambio(ActionEvent event) {
        gripPane.getChildren().clear();
        listrangoExtendsAnchor.clear();
        slider.setMin(0);
        slider.setValue(50);
        lblIni.setText("");
        lblfin.setText("");
    }

}
