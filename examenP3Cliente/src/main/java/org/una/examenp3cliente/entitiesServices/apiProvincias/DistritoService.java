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
import org.una.examenp3cliente.dtos.apiProvincias.DistritoDTO;
import org.una.examenp3cliente.sharedServices.Conection;

/**
 *
 * @author Bosco
 */
public class DistritoService {
    public static List<DistritoDTO> allDistrito() {

        List<DistritoDTO> listDistritoDTO = new ArrayList<>();
        try {
            listDistritoDTO = (List<DistritoDTO>) Conection.listFromConnection("distritos/", new TypeToken<ArrayList<DistritoDTO>>() {
            }.getType());

        } catch (IOException ex) {
            Logger.getLogger(DistritoService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listDistritoDTO;
    }

    public static DistritoDTO idDistrito(Long id) {

        DistritoDTO DistritoDTO = new DistritoDTO();
        try {
            DistritoDTO = (DistritoDTO) Conection.oneConnection("distritos/" + id, new TypeToken<DistritoDTO>() {
            }.getType());

        } catch (IOException ex) {
            Logger.getLogger(DistritoService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return DistritoDTO;
    }
    
    public static List<DistritoDTO> cantonesIddistrito(Long id) {

        List<DistritoDTO> listdistritoDTOs = new ArrayList<>();
        try {
            listdistritoDTOs = (List<DistritoDTO>) Conection.listFromConnection("distritos/cantonId/" + id, new TypeToken<ArrayList<DistritoDTO>>() {
            }.getType());

        } catch (IOException ex) {
            Logger.getLogger(DistritoService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listdistritoDTOs;
    }

    public static DistritoDTO createDistrito(DistritoDTO create) {
        DistritoDTO proCreado = new DistritoDTO();
        try {
            proCreado = (DistritoDTO) Conection.createObjectToConnectionReturnObject("distritos/", create, new TypeToken<DistritoDTO>() {
            }.getType());
        } catch (IOException ex) {
            Logger.getLogger(DistritoService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return proCreado;
    }
    
    public static List<DistritoDTO> estado(boolean estado) {

        List<DistritoDTO> dtos = new ArrayList<>();
        try {
            dtos = (List<DistritoDTO>) Conection.listFromConnection("distritos/estado/" +estado, new TypeToken<ArrayList<DistritoDTO>>() {
            }.getType());

        } catch (IOException ex) {
            Logger.getLogger(DistritoService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dtos;
    }

    public static int updateDistrito(DistritoDTO update) {
        int codeResponse = 0;
        try {
            codeResponse = Conection.updateObjectToConnection("distritos/" + update.getId(), update);
        } catch (IOException ex) {
            Logger.getLogger(DistritoService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return codeResponse;
    }
}
