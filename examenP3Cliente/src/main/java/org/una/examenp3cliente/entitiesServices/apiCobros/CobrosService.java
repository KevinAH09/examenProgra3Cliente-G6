/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.examenp3cliente.entitiesServices.apiCobros;

import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.una.examenp3cliente.dtos.apiCobros.CobroDTO;
import org.una.examenp3cliente.dtos.apiCobros.MembresiaDTO;
import org.una.examenp3cliente.sharedServices.Conection;

/**
 *
 * @author cfugu
 */
public class CobrosService {

    public static List<CobroDTO> allCobros() {

        List<CobroDTO> cobrosDTOs = new ArrayList<>();
        try {
            cobrosDTOs = (List<CobroDTO>) Conection.listFromConnection("cobros_pendientes/", new TypeToken<ArrayList<CobroDTO>>() {
            }.getType());

        } catch (IOException ex) {
            Logger.getLogger(CobrosService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cobrosDTOs;
    }

    public static List<CobroDTO> idClienteCobros(Long id) {

        List<CobroDTO> cobrosDTOs = new ArrayList<>();
        try {
            cobrosDTOs = (List<CobroDTO>) Conection.listFromConnection("cobros_pendientes/cliente/" + id, new TypeToken<ArrayList<CobroDTO>>() {
            }.getType());

        } catch (IOException ex) {
            Logger.getLogger(CobrosService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cobrosDTOs;
    }

    public static List<CobroDTO> identificacionClienteCobros(String identificacion) {

        List<CobroDTO> cobrosDTOs = new ArrayList<>();
        try {
            cobrosDTOs = (List<CobroDTO>) Conection.listFromConnection("cobros_pendientes/identificacion/" + identificacion, new TypeToken<ArrayList<CobroDTO>>() {
            }.getType());

        } catch (IOException ex) {
            Logger.getLogger(CobrosService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cobrosDTOs;
    }

    public static List<CobroDTO> identificacionTipoClienteCobros(String identificacion, String tipo) {

        List<CobroDTO> cobrosDTOs = new ArrayList<>();
        try {
            cobrosDTOs = (List<CobroDTO>) Conection.listFromConnection("cobros_pendientes/identificacion-tipo/" + identificacion + "/" + tipo, new TypeToken<ArrayList<CobroDTO>>() {
            }.getType());

        } catch (IOException ex) {
            Logger.getLogger(CobrosService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cobrosDTOs;
    }

    public static CobroDTO idCobro(Long id) {

        CobroDTO CobroPendienteDTO = new CobroDTO();
        try {
            CobroPendienteDTO = (CobroDTO) Conection.oneConnection("cobros_pendientes/" + id, new TypeToken<CobroDTO>() {
            }.getType());

        } catch (IOException ex) {
            Logger.getLogger(CobrosService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return CobroPendienteDTO;
    }

    public static CobroDTO createCobros(CobroDTO createCobros) {
        CobroDTO cobro = new CobroDTO();
        try {
            cobro = (CobroDTO) Conection.createObjectToConnectionReturnObject("cobros_pendientes/", createCobros, new TypeToken<CobroDTO>() {
            }.getType());
        } catch (IOException ex) {
            Logger.getLogger(CobrosService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cobro;
    }

    public static int updateProyecto(CobroDTO updateCobro) {
        int codeResponse = 0;
        try {
            codeResponse = Conection.updateObjectToConnection("cobros_pendientes/" + updateCobro.getId(), updateCobro);
        } catch (IOException ex) {
            Logger.getLogger(CobrosService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return codeResponse;
    }
}
