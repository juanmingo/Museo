/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.usm.geosansano.sessions.beans;

import cl.usm.geosansano.entity.MuseoProyecto;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Juan Delgado
 */
@Local
public interface MuseoProyectoFacadeLocal {

    void create(MuseoProyecto museoProyecto);

    void edit(MuseoProyecto museoProyecto);

    void remove(MuseoProyecto museoProyecto);

    MuseoProyecto find(Object id);

    List<MuseoProyecto> findAll();

    List<MuseoProyecto> findRange(int[] range);

    int count();

    MuseoProyecto findByMaxMusproId();

    long newMusproId();

    List<MuseoProyecto> findByMususuId(long mususuId);

    List<MuseoProyecto> findByProyectoUsuario(long mususuId);

    List<MuseoProyecto> findByProyectosGeo(double norteLatitud, double norteLongitud, double surteLatitud, double surLongitud);

    List<MuseoProyecto> findByProyectosGeo2(double norteLatitud, double norteLongitud, double surteLatitud, double surLongitud);



    List<MuseoProyecto> findByProyectosUsuarioPendienteRechazado(long mususuId, double norteLatitud, double norteLongitud, double surteLatitud, double surLongitud);

}
