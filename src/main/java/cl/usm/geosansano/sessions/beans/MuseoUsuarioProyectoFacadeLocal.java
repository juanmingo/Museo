/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.usm.geosansano.sessions.beans;

import cl.usm.geosansano.entity.MuseoUsuarioProyecto;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Juan
 */
@Local
public interface MuseoUsuarioProyectoFacadeLocal {

    void create(MuseoUsuarioProyecto museoUsuarioProyecto);

    void edit(MuseoUsuarioProyecto museoUsuarioProyecto);

    void remove(MuseoUsuarioProyecto museoUsuarioProyecto);

    MuseoUsuarioProyecto find(Object id);

    List<MuseoUsuarioProyecto> findAll();

    List<MuseoUsuarioProyecto> findRange(int[] range);

    int count();
    
}
