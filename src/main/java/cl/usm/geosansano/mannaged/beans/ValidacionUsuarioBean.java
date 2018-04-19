/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.usm.geosansano.mannaged.beans;

import cl.usm.geosansano.entity.MuseoUsuario;
import cl.usm.geosansano.entity.TipoVigencia;
import cl.usm.geosansano.functions.FuncionEncriptado;
import cl.usm.geosansano.sessions.beans.MuseoUsuarioFacadeLocal;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Alex
 */
@Named(value = "validacionUsuarioBean")
@ViewScoped
public class ValidacionUsuarioBean implements Serializable {

    @EJB
    private MuseoUsuarioFacadeLocal museoUsuarioFacade;

    private String codigo = "";

    private String mensajeValidacion = "";

    @PostConstruct
    public void init() {
        System.out.println("init ValidacionUsuarioBean");

    }

    public void cargar() {
        try {

            System.out.println("codigo encriptado: " + codigo);
            codigo = FuncionEncriptado.desencriptar(codigo);
            System.out.println("codigo desencriptado: " + codigo);
            MuseoUsuario mu = museoUsuarioFacade.find(Long.parseLong(codigo));
            if (mu == null) {
                mensajeValidacion = "Ocurrio un problema al intentar validar su cuenta, por favor comuniquese con un administrador.";
            } else {
                mu.setCodVigencia(new TipoVigencia(1));
                museoUsuarioFacade.edit(mu);
                mensajeValidacion = "Su cuenta se activo con éxito !!!";
            }
        } catch (Exception ex) {
            Logger.getLogger(ValidacionUsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the mensajeValidacion
     */
    public String getMensajeValidacion() {
        return mensajeValidacion;
    }

    /**
     * @param mensajeValidacion the mensajeValidacion to set
     */
    public void setMensajeValidacion(String mensajeValidacion) {
        this.mensajeValidacion = mensajeValidacion;
    }

}
