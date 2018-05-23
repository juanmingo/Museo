/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.usm.geosansano.sessions.beans;

import cl.usm.geosansano.entity.Sede;
import java.util.List;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

/**
 *
 * @author Juan Delgado
 */
@Stateful
public class SedeFacade extends AbstractFacade<Sede> implements SedeFacadeLocal {

    @PersistenceContext(unitName = "USM_GeoSansano_war_1.0PU", type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SedeFacade() {
        super(Sede.class);
    }

    @Override
    public List<Sede> findCampusSede() {
        return em.createNamedQuery("Sede.findCampusSede")
                .getResultList();
    }
}
