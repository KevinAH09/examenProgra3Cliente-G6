/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.examenp3cliente.controllers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.una.examenp3cliente.dtos.apiCobros.ClienteDTO;
import org.una.examenp3cliente.dtos.apiCobros.CobroDTO;
import org.una.examenp3cliente.dtos.apiCobros.MembresiaDTO;
import org.una.examenp3cliente.entitiesServices.apiCobros.ClienteService;
import org.una.examenp3cliente.entitiesServices.apiCobros.CobrosService;
import org.una.examenp3cliente.entitiesServices.apiCobros.MembresiaService;

/**
 * FXML Controller class
 *
 * @author cfugu
 */
public class AuxiliarCobrosController extends Controller implements Initializable {

    private VBox vboxCargando;
    @FXML
    private VBox vboxCheck;
    public CobroDTO cobroDTO = new CobroDTO();
    public CobroDTO cobroDTO2 = new CobroDTO();
    Date date = new Date();
    public List<CobroDTO> cobroList = new ArrayList<CobroDTO>();
    public List<ClienteDTO> clientesListCobro = new ArrayList<ClienteDTO>();
    public List<MembresiaDTO> membresiaListCobro = new ArrayList<MembresiaDTO>();
    public MembresiaDTO membresiaFilt2 = new MembresiaDTO();
    public ClienteDTO clientesFilt2 = new ClienteDTO();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicio();
    }

    //Metodo para obtener la fecha de vencimiento
    public void FechaVencimiento(Date dat, int dias) {
        Calendar hoy = Calendar.getInstance();
        hoy.setTime(date);
        hoy.add(Calendar.DATE, dias);
        date = hoy.getTime();
    }

    //Metodo para verificar que un cliente no posea un cobro ya realizado
    public boolean verificaficacion(String identificacion, String tipo) {
        cobroList = null;
        cobroList = CobrosService.identificacionTipoClienteCobros(identificacion, tipo);
        if (cobroList.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public void inicio() {
        generarCobro();
    }

    //Metodo para guardar los cobros 
    public void guardarCobros(Date dates, int cantidad, int cantDias, Double monto, String periodo) {

        BigDecimal formatNumber = new BigDecimal(monto);
        formatNumber = formatNumber.setScale(2, RoundingMode.UP);

        Calendar fecha = Calendar.getInstance();
        boolean band = true;
        for (int i = 0; i < cantidad && band == true; i++) {
            if (i == 0) {
                int dias = 30 * cantDias;
                FechaVencimiento(dates, dias);
                cobroDTO = new CobroDTO();
                fecha.setTime(dates);
            } else {
                int dias = 30 * cantDias;//30 dias por la cantidad de meses
                FechaVencimiento(date, dias);
                cobroDTO = new CobroDTO();
                fecha.setTime(date);
            }
            cobroDTO.setAnno(String.valueOf(fecha.get(Calendar.YEAR)));//Se le ingresa el año de la fecha de vencimiento.
            cobroDTO.setClientesId(clientesFilt2);
            cobroDTO.setFechaVencimiento(date);
            cobroDTO.setMonto(formatNumber.doubleValue());
            cobroDTO.setPeriodo(periodo);
            cobroDTO.setTipo(membresiaFilt2.getDescripcion());
            cobroDTO2 = CobrosService.createCobros(cobroDTO);
            System.out.println(cobroDTO);
        }
    }

    //Metodo para iniciar con los cobros respectivos
    private void generarCobro() {
        clientesListCobro = ClienteService.allCliente();

        for (ClienteDTO clienteDTO : clientesListCobro) {
            clientesFilt2 = clienteDTO;
            membresiaListCobro = MembresiaService.idClienteMembresia(Long.valueOf(clienteDTO.getId()));
            if (membresiaListCobro != null) {
                for (MembresiaDTO membresiaDTO : membresiaListCobro) {
                    membresiaFilt2 = membresiaDTO;
                    if (verificaficacion(clienteDTO.getIdentificacion(), membresiaDTO.getDescripcion())) {
                        if (membresiaDTO.getPeriodicidad().equals("Anual")) {
                            date = new Date();
                            guardarCobros(date, 1, 12, membresiaDTO.getMonto(), "Anual");
                            //el date es la fecha actual, el 1 es la cantidad de cobros a realizar, el 12 es la cantidad
                            //de meses para la fecha de vencimiento, luego el monto y por ultimo el periodo de pago.
                        }
                        if (membresiaDTO.getPeriodicidad().equals("Mensual")) {
                            date = new Date();
                            guardarCobros(date, 12, 1, (membresiaDTO.getMonto() / 12), "Mensual");//Se divide el monto por la cantidad de cobros a realizar.
                        }
                        if (membresiaDTO.getPeriodicidad().equals("Bimestral")) {
                            date = new Date();
                            guardarCobros(date, 6, 2, (membresiaDTO.getMonto() / 6), "Bimestral");
                        }
                        if (membresiaDTO.getPeriodicidad().equals("Trimestral")) {
                            date = new Date();
                            guardarCobros(date, 4, 3, (membresiaDTO.getMonto() / 4), "Trimestral");

                        }
                        if (membresiaDTO.getPeriodicidad().equals("Cuatrimestral")) {
                            date = new Date();
                            guardarCobros(date, 3, 4, (membresiaDTO.getMonto() / 3), "Cuatrimestral");
                        }
                        if (membresiaDTO.getPeriodicidad().equals("Semestral")) {
                            date = new Date();
                            guardarCobros(date, 2, 6, (membresiaDTO.getMonto() / 2), "Semestral");
                        }
                    }
                }
            }
        }

    }

    @Override
    public void initialize() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void onActionClose(ActionEvent event) {
        ((Stage) vboxCheck.getScene().getWindow()).close();
    }

}
