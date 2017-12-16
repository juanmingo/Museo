/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.usm.geosansano.sessions.beans;

import cl.usm.geosansano.entity.MuseoUsuarioFoto;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Juan Delgado
 */
@Stateless
public class MuseoUsuarioFotoFacade extends AbstractFacade<MuseoUsuarioFoto> implements MuseoUsuarioFotoFacadeLocal {

    @PersistenceContext(unitName = "USM_GeoSansano_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MuseoUsuarioFotoFacade() {
        super(MuseoUsuarioFoto.class);
    }
    
}
