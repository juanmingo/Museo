/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.usm.geosansano.sessions.beans;

import cl.usm.geosansano.entity.MuseoProyecto;
import cl.usm.geosansano.entity.MuseoUsuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Juan Delgado
 */
@Stateless
public class MuseoProyectoFacade extends AbstractFacade<MuseoProyecto> implements MuseoProyectoFacadeLocal {

    @PersistenceContext(unitName = "USM_GeoSansano_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MuseoProyectoFacade() {
        super(MuseoProyecto.class);
    }

    @Override
    public MuseoProyecto findByMaxMusproId() {
        try {
            return (MuseoProyecto) em.createNamedQuery("MuseoProyecto.findByMaxMusproId")
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public long newMusproId() {
        MuseoProyecto museoProyecto = findByMaxMusproId();
        if (museoProyecto != null) {
            return museoProyecto.getMusproId() + 1;
        } else {
            return 1;
        }
    }

    @Override
    public List<MuseoProyecto> findByMususuId(long mususuId) {
        return em.createNamedQuery("MuseoProyecto.findByMususuId")
                .setParameter("mususuId", mususuId)
                .getResultList();
    }

}
