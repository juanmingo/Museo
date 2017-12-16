/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.usm.geosansano.sessions.beans;

import cl.usm.geosansano.entity.CarreraImparte;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Juan Delgado
 */
@Local
public interface CarreraImparteFacadeLocal {

    void create(CarreraImparte carreraImparte);

    void edit(CarreraImparte carreraImparte);

    void remove(CarreraImparte carreraImparte);

    CarreraImparte find(Object id);

    List<CarreraImparte> findAll();

    List<CarreraImparte> findRange(int[] range);

    int count();
    
}
