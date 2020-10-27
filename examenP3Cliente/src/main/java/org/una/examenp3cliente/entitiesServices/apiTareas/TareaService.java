/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.examenp3cliente.entitiesServices.apiTareas;

import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.una.examenp3cliente.dtos.apiTareas.TareaDTO;
import org.una.examenp3cliente.sharedServices.Conection;

/**
 *
 * @author colo7
 */
public class TareaService {

    public static List<TareaDTO> allTarea() {

        List<TareaDTO> listTareaDTOs = new ArrayList<>();
        try {
            listTareaDTOs = (List<TareaDTO>) Conection.listFromConnection("tarea/", new TypeToken<ArrayList<TareaDTO>>() {
            }.getType());

        } catch (IOException ex) {
            Logger.getLogger(TareaService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listTareaDTOs;
    }

    public static TareaDTO idTarea(Long id) {

        TareaDTO TareaDTO = new TareaDTO();
        try {
            TareaDTO = (TareaDTO) Conection.oneConnection("tarea/" + id, new TypeToken<TareaDTO>() {
            }.getType());

        } catch (IOException ex) {
            Logger.getLogger(TareaService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return TareaDTO;
    }

    public static List<TareaDTO> proyectoIdTarea(Long id) {

        List<TareaDTO> listTareaDTOs = new ArrayList<>();
        try {
            listTareaDTOs = (List<TareaDTO>) Conection.listFromConnection("tarea/proyectoID/" + id, new TypeToken<ArrayList<TareaDTO>>() {
            }.getType());

        } catch (IOException ex) {
            Logger.getLogger(TareaService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listTareaDTOs;
    }

    public static TareaDTO createTarea(TareaDTO createTarea) {
        TareaDTO usucreado = new TareaDTO();
        try {
            usucreado = (TareaDTO) Conection.createObjectToConnectionReturnObject("tarea/", createTarea, new TypeToken<TareaDTO>() {
            }.getType());
        } catch (IOException ex) {
            Logger.getLogger(TareaService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usucreado;
    }

    public static int updateTarea(TareaDTO createTarea) {
        int codeResponse = 0;
        try {
            codeResponse = Conection.updateObjectToConnection("tarea/" + createTarea.getId(), createTarea);
        } catch (IOException ex) {
            Logger.getLogger(TareaService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return codeResponse;
    }

}
