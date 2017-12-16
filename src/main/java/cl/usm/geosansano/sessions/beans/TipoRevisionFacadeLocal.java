/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.usm.geosansano.sessions.beans;

import cl.usm.geosansano.entity.TipoRevision;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Juan Delgado
 */
@Local
public interface TipoRevisionFacadeLocal {

    void create(TipoRevision tipoRevision);

    void edit(TipoRevision tipoRevision);

    void remove(TipoRevision tipoRevision);

    TipoRevision find(Object id);

    List<TipoRevision> findAll();

    List<TipoRevision> findRange(int[] range);

    int count();
    
}
