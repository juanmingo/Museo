/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.usm.geosansano.sessions.beans;

import cl.usm.geosansano.entity.MuseoUsuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Juan
 */
@Stateless
public class MuseoUsuarioFacade extends AbstractFacade<MuseoUsuario> implements MuseoUsuarioFacadeLocal {

    @PersistenceContext(unitName = "USM_GeoSansano_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MuseoUsuarioFacade() {
        super(MuseoUsuario.class);
    }

    @Override
    public List<MuseoUsuario> findByUsuario() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
