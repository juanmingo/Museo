/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.usm.geosansano.sessions.beans;

import cl.usm.geosansano.entity.TipoPerfil;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Juan Delgado
 */
@Local
public interface TipoPerfilFacadeLocal {

    void create(TipoPerfil tipoPerfil);

    void edit(TipoPerfil tipoPerfil);

    void remove(TipoPerfil tipoPerfil);

    TipoPerfil find(Object id);

    List<TipoPerfil> findAll();

    List<TipoPerfil> findRange(int[] range);

    int count();
    
}
