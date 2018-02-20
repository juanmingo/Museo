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
    //MENU PERFIL
    //public static final String PAGINA_MENU_PERFIL = "/menu/menuperfil.jsf";
    //public static final String PAGINA_MENU_PERFIL_BAR = "/menu/menuperfilbar.jsf";
    //
    public static final String PAGINA_MENU_CARGAR_DATOS_SANSANO_MAPA = "/sansano/mapacd.jsf";
    public static final String PAGINA_MENU_SANSANO_MAPA = "/sansano/mapa.jsf";

}
