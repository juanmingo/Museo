/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.usm.geosansano.sessions.beans;

import cl.usm.geosansano.entity.TipoVigencia;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Juan Delgado
 */
@Local
public interface TipoVigenciaFacadeLocal {

    void create(TipoVigencia tipoVigencia);

    void edit(TipoVigencia tipoVigencia);

    void remove(TipoVigencia tipoVigencia);

    TipoVigencia find(Object id);

    List<TipoVigencia> findAll();

    List<TipoVigencia> findRange(int[] range);

    int count();
    
}
