/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.usm.geosansano.sessions.beans;

import cl.usm.geosansano.entity.MuseoUsuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Juan Delgado
 */
@Local
public interface MuseoUsuarioFacadeLocal {

    void create(MuseoUsuario museoUsuario);

    void edit(MuseoUsuario museoUsuario);

    void remove(MuseoUsuario museoUsuario);

    MuseoUsuario find(Object id);

    List<MuseoUsuario> findAll();

    List<MuseoUsuario> findRange(int[] range);

    int count();

    //Busqueda Cuenta
    MuseoUsuario findByCuenta(String correo, String contraseña);

    MuseoUsuario findByCorreo(String correo);

    Long querySimple(String query);
}
