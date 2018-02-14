/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.usm.geosansano.sessions.beans;

import cl.usm.geosansano.entity.MuseoProyectoCoordenada;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Juan
 */
@Local
public interface MuseoProyectoCoordenadaFacadeLocal {

    void create(MuseoProyectoCoordenada museoProyectoCoordenada);

    void edit(MuseoProyectoCoordenada museoProyectoCoordenada);

    void remove(MuseoProyectoCoordenada museoProyectoCoordenada);

    MuseoProyectoCoordenada find(Object id);

    List<MuseoProyectoCoordenada> findAll();

    List<MuseoProyectoCoordenada> findRange(int[] range);

    int count();
    
}
