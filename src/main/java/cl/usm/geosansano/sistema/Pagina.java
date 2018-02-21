package cl.usm.geosansano.sistema;

import java.io.Serializable;

/**
 *
 * @author Juan D. Delgado Robles
 */
public abstract class Pagina implements Serializable {

    public static final String NOMBRE_WAR = "geosansano";

    //
    public static final String PAGINA_LOGIN = "/index.jsf";
    public static final String PAGINA_INDEX = "/index.jsf";
    //
    public static final String ICON_MARKER_NARANJA = "/images/icon_marker_naranja2.png";
    //
    public static final String PAGINA_MENU_CARGAR_DATOS_SANSANO_MAPA = "/sansano/mapacd.jsf";
    public static final String PAGINA_MENU_SANSANO_MAPA = "/sansano/mapa.jsf";

    public static final String PAGINA_MENU_CARGAR_DATOS_AGREGAR_PROYECTO = "/sansano/agregarproyectocd.jsf";
    public static final String PAGINA_MENU_AGREGAR_PROYECTO = "/sansano/agregarproyecto.jsf";

}
