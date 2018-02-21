/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.usm.geosansano.sessions.beans;

import cl.usm.geosansano.entity.MuseoUsuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Juan Delgado
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
    public MuseoUsuario findByCuenta(String correo, String contraseña) {
        try {
            return (MuseoUsuario) em.createNamedQuery("MuseoUsuario.findByCuenta")
                    .setParameter("correo", correo)
                    .setParameter("contraseña", contraseña)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
}
    }

    @Override
    public MuseoUsuario findByCorreo(String correo) {
        try {
            return (MuseoUsuario) em.createNamedQuery("MuseoUsuario.findByCorreo")
                    .setParameter("correo", correo)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

}
