package cl.usm.geosansano.sistema;

import java.io.IOException;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Juan D. Delgado Robles
 */
@ManagedBean(name = "common", eager = true)
@SessionScoped
public class Common implements Serializable {



    public static void exit() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.invalidate();
        redireccionar(Pagina.PAGINA_LOGIN);
    }

    public static void eliminarSession() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.invalidate();
    }


    public static String obtenereUrlBase() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String url = request.getRequestURL().toString();
        String parts[] = url.split(Pagina.NOMBRE_WAR);
        return (parts[parts.length - 2] + Pagina.NOMBRE_WAR);
    }

    public static void redireccionar(String url) {
        //System.out.println("url: " + url);
        ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String path = ctx.getContextPath();
        try {
            //System.out.println("path + url: " + path + url);
            FacesContext.getCurrentInstance().getExternalContext().redirect(path + url);
        } catch (IOException e) {
            System.out.println("[Error][Common][redireccionar][IOException]: " + e.getMessage());
        }
    }

    public static void redireccionarExterior(String url) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(url);
        } catch (IOException e) {
            System.out.println("[Error][Common][redireccionarExterior][IOException]: " + e.getMessage());
        }
    }

    public static void redirectNotLogin() {
        if (!isLoggedIn()) {
            redireccionar(Pagina.PAGINA_LOGIN);
        }
    }

    public static boolean isLoggedIn() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        String isLog = (String) session.getAttribute("isLoggedIn");
        return ((isLog != null && isLog.equals("yes")));
    }

    public static void setRutUsuario(long rutUsuario) {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("rutUsuario", rutUsuario);
    }

    public static String obtenerCuentaUsuario() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        return (String) session.getAttribute("cuentaUsuario");
    }

    public static String obtenerCodPerfil() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        return (String) session.getAttribute("codPerfil");
    }

    public static String obtenerIsLoggedIn() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        return (String) session.getAttribute("isLoggedIn");
    }


    public static String path() {
        ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String path = ctx.getContextPath();
        return path;
    }

    public static String baseURL() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String url = request.getRequestURL().toString();
        return url.substring(0, url.length() - request.getRequestURI().length()) + request.getContextPath();
    }

    public static void doNoCache() {
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setDateHeader("Expires", 0); // Proxies.
    }
}
