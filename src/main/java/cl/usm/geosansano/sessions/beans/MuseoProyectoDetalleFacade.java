/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.usm.geosansano.sessions.beans;

import cl.usm.geosansano.entity.MuseoProyectoDetalle;
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
public class MuseoProyectoDetalleFacade extends AbstractFacade<MuseoProyectoDetalle> implements MuseoProyectoDetalleFacadeLocal {

    @PersistenceContext(unitName = "USM_GeoSansano_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MuseoProyectoDetalleFacade() {
        super(MuseoProyectoDetalle.class);
    }

    @Override
    public List<MuseoProyectoDetalle> findByDetalleActivo(long musproId) {
        return em.createNamedQuery("MuseoProyectoDetalle.findByDetalleActivo")
                .setParameter("musproId", musproId)
                .getResultList();
    }

    @Override
    public MuseoProyectoDetalle findId(long musproId, long musprodetId) {
        try {
            return (MuseoProyectoDetalle) em.createNamedQuery("MuseoProyectoDetalle.findId")
                    .setParameter("musproId", musproId)
                    .setParameter("musprodetId", musprodetId)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public MuseoProyectoDetalle findByMaxMusprodetId(long musproId) {
        try {
            return (MuseoProyectoDetalle) em.createNamedQuery("MuseoProyectoDetalle.findByMaxMusprodetId")
                    .setParameter("musproId", musproId)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public long newMusprodetId(long musproId) {
        MuseoProyectoDetalle museoProyectoDetalle = findByMaxMusprodetId(musproId);
        if (museoProyectoDetalle != null) {
            return museoProyectoDetalle.getMuseoProyectoDetallePK().getMusprodetId() + 1;
        } else {
            return 1;
        }
    }

}
