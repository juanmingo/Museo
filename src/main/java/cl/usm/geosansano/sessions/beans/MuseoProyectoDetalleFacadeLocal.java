/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.usm.geosansano.sessions.beans;

import cl.usm.geosansano.entity.MuseoProyectoDetalle;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Juan Delgado
 */
@Local
public interface MuseoProyectoDetalleFacadeLocal {

    void create(MuseoProyectoDetalle museoProyectoDetalle);

    void edit(MuseoProyectoDetalle museoProyectoDetalle);

    void remove(MuseoProyectoDetalle museoProyectoDetalle);

    MuseoProyectoDetalle find(Object id);

    MuseoProyectoDetalle findId(long musproId, long musprodetId);

    MuseoProyectoDetalle findByMaxMusprodetId(long musproId);

    long newMusprodetId(long musproId);

    List<MuseoProyectoDetalle> findAll();

    List<MuseoProyectoDetalle> findRange(int[] range);

    List<MuseoProyectoDetalle> findByDetalleActivo(long musproId);

    int count();

}
