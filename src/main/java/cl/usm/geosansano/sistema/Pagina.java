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
    public static final String PAGINA_MENU_CARGAR_DATOS_GENERAL_MAPA = "/generalmapa.jsf";
    //
    //public static final String ICON_MARKER_VERDE = "/images/icon_marker_verde.png";
    //public static final String ICON_MARKER_CELESTE = "/images/icon_marker_celeste.png";
    //public static final String ICON_MARKER_ROZADA = "/images/icon_marker_rozado.png";
    //public static final String ICON_MARKER_NEGRO = "/images/icon_marker_negro.png";
    public static final String ICON_MARKER_USM = "/images/icon_marker_usm.png";

    //http://icon-park.com/icon/simple-location-map-pin-icon2-gray-free-vector-data/
    public static final String ICON_MARKER_BLACK = "/images/icon_location_black.png";
    public static final String ICON_MARKER_BLUE = "/images/icon_location_blue.png";
    public static final String ICON_MARKER_BROWN = "/images/icon_location_brown.png";
    public static final String ICON_MARKER_DARK_GREEN = "/images/icon_location_dark_green.png";
    public static final String ICON_MARKER_GRAY = "/images/icon_location_gray.png";
    public static final String ICON_MARKER_GREEN = "/images/icon_location_green.png";
    public static final String ICON_MARKER_LIGHT_BLUE = "/images/icon_location_light_blue.png";
    public static final String ICON_MARKER_LIGHT_GREEN = "/images/icon_location_light_green.png";
    public static final String ICON_MARKER_NAVY_BLUE = "/images/icon_location_navy_blue.png";
    public static final String ICON_MARKER_ORANGE = "/images/icon_location_orange.png";
    public static final String ICON_MARKER_PINK = "/images/icon_location_pink.png";
    public static final String ICON_MARKER_PURPLE = "/images/icon_location_purple.png";
    public static final String ICON_MARKER_RED = "/images/icon_location_red.png";
    public static final String ICON_MARKER_TOURQUOSIDE_BLUE = "/images/icon_location_turquoise_blue.png";
    public static final String ICON_MARKER_YELLOW = "/images/icon_location_yellow.png";

    //
    //
    public static final double CENTRAL_LATITUD = -34.023547171924655;
    public static final double CENTRAL_LONGITUD = -71.93909037042135;
    public static final int CENTRAL_ZOOM = 6;
    public static final double CENTRAL_NORTE_LATITUD = -29.0533557894247;
    public static final double CENTRAL_NORTE_LONGITUD = -57.316287636046354;
    public static final double CENTRAL_SUR_LATITUD = -38.99373855442461;
    public static final double CENTRAL_SUR_LONGITUD = -86.56189310479635;
    //
    public static final String PAGINA_MENU_CARGAR_DATOS_SANSANO_MAPA = "/sansano/mapacd.jsf";
    public static final String PAGINA_MENU_SANSANO_MAPA = "/sansano/mapa.jsf";

    public static final String PAGINA_MENU_CARGAR_DATOS_AGREGAR_PROYECTO = "/sansano/agregarproyectocd.jsf";
    public static final String PAGINA_MENU_AGREGAR_PROYECTO = "/sansano/agregarproyecto.jsf";

}
