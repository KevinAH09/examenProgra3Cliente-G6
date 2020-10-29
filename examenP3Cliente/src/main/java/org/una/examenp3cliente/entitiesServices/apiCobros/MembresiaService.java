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
import org.una.examenp3cliente.dtos.apiCobros.MembresiaDTO;
import org.una.examenp3cliente.sharedServices.Conection;

/**
 *
 * @author cfugu
 */
public class MembresiaService {

    public static List<MembresiaDTO> allMembresia() {

        List<MembresiaDTO> listMembresiaDTOs = new ArrayList<>();
        try {
            listMembresiaDTOs = (List<MembresiaDTO>) Conection.listFromConnection("membresias/", new TypeToken<ArrayList<MembresiaDTO>>() {
            }.getType());

        } catch (IOException ex) {
            Logger.getLogger(MembresiaService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listMembresiaDTOs;
    }

    public static List<MembresiaDTO> idClienteMembresia(Long id) {

        List<MembresiaDTO> listMembresiaDTOs = new ArrayList<>();
        try {
            listMembresiaDTOs = (List<MembresiaDTO>) Conection.listFromConnection("membresias/cliente/" + id, new TypeToken<ArrayList<MembresiaDTO>>() {
            }.getType());

        } catch (IOException ex) {
            Logger.getLogger(MembresiaService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listMembresiaDTOs;
    }

    public static MembresiaDTO idMembresia(Long id) {

        MembresiaDTO MembresiaDTO = new MembresiaDTO();
        try {
            MembresiaDTO = (MembresiaDTO) Conection.oneConnection("membresias/" + id, new TypeToken<MembresiaDTO>() {
            }.getType());

        } catch (IOException ex) {
            Logger.getLogger(MembresiaService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return MembresiaDTO;
    }

    public static MembresiaDTO createProyecto(MembresiaDTO createMembresia) {
        MembresiaDTO membresia = new MembresiaDTO();
        try {
            membresia = (MembresiaDTO) Conection.createObjectToConnectionReturnObject("membresias/", createMembresia, new TypeToken<MembresiaDTO>() {
            }.getType());
        } catch (IOException ex) {
            Logger.getLogger(MembresiaService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return membresia;
    }

    public static int updateProyecto(MembresiaDTO updateMembresia) {
        int codeResponse = 0;
        try {
            codeResponse = Conection.updateObjectToConnection("membresias/" + updateMembresia.getId(), updateMembresia);
        } catch (IOException ex) {
            Logger.getLogger(MembresiaService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return codeResponse;
    }
}
