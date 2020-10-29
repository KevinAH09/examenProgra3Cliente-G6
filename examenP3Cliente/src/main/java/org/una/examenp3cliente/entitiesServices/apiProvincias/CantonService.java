/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.examenp3cliente.entitiesServices.apiProvincias;

import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.una.examenp3cliente.dtos.apiProvincias.CantonDTO;
import org.una.examenp3cliente.sharedServices.Conection;

/**
 *
 * @author Bosco
 */
public class CantonService {
    public static List<CantonDTO> allCanton() {

        List<CantonDTO> listCantonDTOs = new ArrayList<>();
        try {
            listCantonDTOs = (List<CantonDTO>) Conection.listFromConnection("cantones/", new TypeToken<ArrayList<CantonDTO>>() {
            }.getType());

        } catch (IOException ex) {
            Logger.getLogger(CantonService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listCantonDTOs;
    }
    
    public static List<CantonDTO> provinciaIdCanton(Long id) {

        List<CantonDTO> listcantonesDTOs = new ArrayList<>();
        try {
            listcantonesDTOs = (List<CantonDTO>) Conection.listFromConnection("cantones/provinciaId/" + id, new TypeToken<ArrayList<CantonDTO>>() {
            }.getType());

        } catch (IOException ex) {
            Logger.getLogger(CantonService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listcantonesDTOs;
    }

    public static CantonDTO idCanton(Long id) {

        CantonDTO CantonDTO = new CantonDTO();
        try {
            CantonDTO = (CantonDTO) Conection.oneConnection("cantones/" + id, new TypeToken<CantonDTO>() {
            }.getType());

        } catch (IOException ex) {
            Logger.getLogger(CantonService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return CantonDTO;
    }
    
    public static List<CantonDTO> estado(boolean estado) {

        List<CantonDTO> dtos = new ArrayList<>();
        try {
            dtos = (List<CantonDTO>) Conection.listFromConnection("cantones/estado/" +estado, new TypeToken<ArrayList<CantonDTO>>() {
            }.getType());

        } catch (IOException ex) {
            Logger.getLogger(CantonService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dtos;
    }

    public static CantonDTO createCantones(CantonDTO create) {
        CantonDTO proCreado = new CantonDTO();
        try {
            proCreado = (CantonDTO) Conection.createObjectToConnectionReturnObject("cantones/", create, new TypeToken<CantonDTO>() {
            }.getType());
        } catch (IOException ex) {
            Logger.getLogger(CantonService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return proCreado;
    }

    public static int updateCanton(CantonDTO update) {
        int codeResponse = 0;
        try {
            codeResponse = Conection.updateObjectToConnection("cantones/" + update.getId(), update);
        } catch (IOException ex) {
            Logger.getLogger(CantonService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return codeResponse;
    }
}
