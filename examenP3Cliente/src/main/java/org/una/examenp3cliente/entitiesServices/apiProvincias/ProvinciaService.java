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
import org.una.examenp3cliente.dtos.apiProvincias.ProvinciaDTO;
import org.una.examenp3cliente.sharedServices.Conection;

/**
 *
 * @author Bosco
 */

public class ProvinciaService {
    public static List<ProvinciaDTO> allProvincia() {

        List<ProvinciaDTO> listProvinciaDTOs = new ArrayList<>();
        try {
            listProvinciaDTOs = (List<ProvinciaDTO>) Conection.listFromConnection("provincias/", new TypeToken<ArrayList<ProvinciaDTO>>() {
            }.getType());

        } catch (IOException ex) {
            Logger.getLogger(ProvinciaService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProvinciaDTOs;
    }

    public static ProvinciaDTO idProvincia(Long id) {

        ProvinciaDTO ProvinciaDTO = new ProvinciaDTO();
        try {
            ProvinciaDTO = (ProvinciaDTO) Conection.oneConnection("provincias/" + id, new TypeToken<ProvinciaDTO>() {
            }.getType());

        } catch (IOException ex) {
            Logger.getLogger(ProvinciaService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ProvinciaDTO;
    }

    public static ProvinciaDTO createProvincias(ProvinciaDTO create) {
        ProvinciaDTO proCreado = new ProvinciaDTO();
        try {
            proCreado = (ProvinciaDTO) Conection.createObjectToConnectionReturnObject("provincias/", create, new TypeToken<ProvinciaDTO>() {
            }.getType());
        } catch (IOException ex) {
            Logger.getLogger(ProvinciaService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return proCreado;
    }

    public static int updateTarea(ProvinciaDTO update) {
        int codeResponse = 0;
        try {
            codeResponse = Conection.updateObjectToConnection("provincias/" + update.getId(), update);
        } catch (IOException ex) {
            Logger.getLogger(ProvinciaService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return codeResponse;
    }
}
