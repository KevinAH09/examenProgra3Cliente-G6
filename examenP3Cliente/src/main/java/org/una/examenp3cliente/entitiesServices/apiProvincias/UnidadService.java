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
import org.una.examenp3cliente.dtos.apiProvincias.UnidadDTO;
import org.una.examenp3cliente.sharedServices.Conection;

/**
 *
 * @author Bosco
 */
public class UnidadService {
    public static List<UnidadDTO> allUnidad() {

        List<UnidadDTO> listUnidadDTO = new ArrayList<>();
        try {
            listUnidadDTO = (List<UnidadDTO>) Conection.listFromConnection("unidades/", new TypeToken<ArrayList<UnidadDTO>>() {
            }.getType());

        } catch (IOException ex) {
            Logger.getLogger(UnidadService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listUnidadDTO;
    }

    public static UnidadDTO idUnidad(Long id) {

        UnidadDTO UnidadDTO = new UnidadDTO();
        try {
            UnidadDTO = (UnidadDTO) Conection.oneConnection("unidades/" + id, new TypeToken<UnidadDTO>() {
            }.getType());

        } catch (IOException ex) {
            Logger.getLogger(UnidadService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return UnidadDTO;
    }

    public static UnidadDTO createUnidad(UnidadDTO create) {
        UnidadDTO proCreado = new UnidadDTO();
        try {
            proCreado = (UnidadDTO) Conection.createObjectToConnectionReturnObject("unidades/", create, new TypeToken<UnidadDTO>() {
            }.getType());
        } catch (IOException ex) {
            Logger.getLogger(UnidadService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return proCreado;
    }

    public static int updateUnidad(UnidadDTO update) {
        int codeResponse = 0;
        try {
            codeResponse = Conection.updateObjectToConnection("unidades/" + update.getId(), update);
        } catch (IOException ex) {
            Logger.getLogger(UnidadService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return codeResponse;
    }
}
