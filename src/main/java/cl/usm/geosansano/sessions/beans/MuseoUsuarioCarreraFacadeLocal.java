/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.usm.geosansano.sessions.beans;

import cl.usm.geosansano.entity.MuseoUsuarioCarrera;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Juan Delgado
 */
@Local
public interface MuseoUsuarioCarreraFacadeLocal {

    void create(MuseoUsuarioCarrera museoUsuarioCarrera);

    void edit(MuseoUsuarioCarrera museoUsuarioCarrera);

    void remove(MuseoUsuarioCarrera museoUsuarioCarrera);

    MuseoUsuarioCarrera find(Object id);

    List<MuseoUsuarioCarrera> findAll();

    List<MuseoUsuarioCarrera> findRange(int[] range);

    int count();
    
}
