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
import org.una.examenp3cliente.dtos.apiTareas.ProyectoDTO;
import org.una.examenp3cliente.sharedServices.Conection;

/**
 *
 * @author colo7
 */
public class ProyectoService {

    public static List<ProyectoDTO> allProyecto() {

        List<ProyectoDTO> listProyectoDTOs = new ArrayList<>();
        try {
            listProyectoDTOs = (List<ProyectoDTO>) Conection.listFromConnection("proyecto/", new TypeToken<ArrayList<ProyectoDTO>>() {
            }.getType());

        } catch (IOException ex) {
            Logger.getLogger(ProyectoService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProyectoDTOs;
    }

    public static ProyectoDTO idProyecto(Long id) {

        ProyectoDTO ProyectoDTO = new ProyectoDTO();
        try {
            ProyectoDTO = (ProyectoDTO) Conection.oneConnection("proyecto/" + id, new TypeToken<ProyectoDTO>() {
            }.getType());

        } catch (IOException ex) {
            Logger.getLogger(ProyectoService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ProyectoDTO;
    }

    public static List<ProyectoDTO> nombreProyecto(String nombre) {

        List<ProyectoDTO> listProyectoDTOs = new ArrayList<>();
        try {
            listProyectoDTOs = (List<ProyectoDTO>) Conection.listFromConnection("proyecto/nombre/" + nombre, new TypeToken<ArrayList<ProyectoDTO>>() {
            }.getType());

        } catch (IOException ex) {
            Logger.getLogger(ProyectoService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProyectoDTOs;
    }

    public static ProyectoDTO createProyecto(ProyectoDTO createProyecto) {
        ProyectoDTO usucreado = new ProyectoDTO();
        try {
            usucreado = (ProyectoDTO) Conection.createObjectToConnectionReturnObject("proyecto/", createProyecto, new TypeToken<ProyectoDTO>() {
            }.getType());
        } catch (IOException ex) {
            Logger.getLogger(ProyectoService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usucreado;
    }

    public static int updateProyecto(ProyectoDTO createProyecto) {
        int codeResponse = 0;
        try {
            codeResponse = Conection.updateObjectToConnection("proyecto/" + createProyecto.getId(), createProyecto);
        } catch (IOException ex) {
            Logger.getLogger(ProyectoService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return codeResponse;
    }
      public static int deleteProyecto(Long id) {

        try {
            return Conection.deleteObjetcConnection("proyecto/" + id);

        } catch (IOException ex) {
            Logger.getLogger(TareaService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

}
