/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.usm.geosansano.sessions.beans;

import cl.usm.geosansano.entity.MuseoUsuarioFoto;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Juan
 */
@Local
public interface MuseoUsuarioFotoFacadeLocal {

    void create(MuseoUsuarioFoto museoUsuarioFoto);

    void edit(MuseoUsuarioFoto museoUsuarioFoto);

    void remove(MuseoUsuarioFoto museoUsuarioFoto);

    MuseoUsuarioFoto find(Object id);

    List<MuseoUsuarioFoto> findAll();

    List<MuseoUsuarioFoto> findRange(int[] range);

    int count();
    
}
