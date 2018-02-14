/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.usm.geosansano.sessions.beans;

import cl.usm.geosansano.entity.CarreraSede;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Juan
 */
@Local
public interface CarreraSedeFacadeLocal {

    void create(CarreraSede carreraSede);

    void edit(CarreraSede carreraSede);

    void remove(CarreraSede carreraSede);

    CarreraSede find(Object id);

    List<CarreraSede> findAll();

    List<CarreraSede> findRange(int[] range);

    int count();
    
}
