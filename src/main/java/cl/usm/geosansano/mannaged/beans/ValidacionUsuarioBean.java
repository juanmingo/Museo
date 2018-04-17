/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.usm.geosansano.mannaged.beans;

import cl.usm.geosansano.functions.FuncionEncriptado;
import cl.usm.geosansano.sessions.beans.CarreraImparteFacadeLocal;
import cl.usm.geosansano.sessions.beans.MuseoUsuarioFacadeLocal;
import cl.usm.geosansano.sessions.beans.SedeFacadeLocal;
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
    private SedeFacadeLocal sedeFacade;
    @EJB
    private CarreraImparteFacadeLocal carreraImparteFacade;
    @EJB
    private MuseoUsuarioFacadeLocal museoUsuarioFacade;

    private String codigo;

    @PostConstruct
    public void init() {
        System.out.println("init ValidacionUsuarioBean");

    }

    public void cargar() {
        try {
            FuncionEncriptado f = new FuncionEncriptado();
            f.makeKey();
            System.out.println("codigo encriptado: " + codigo);
            codigo = f.decrypt(codigo);
            System.out.println("codigo desencriptado: " + codigo);
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

}
