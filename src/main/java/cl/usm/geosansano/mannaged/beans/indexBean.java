/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.usm.geosansano.mannaged.beans;


import cl.usm.geosansano.entity.TipoRevision;
import cl.usm.geosansano.sessions.beans.TipoRevisionFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author Juan Delgado
 */
@Named(value = "index")
@SessionScoped
public class indexBean implements Serializable {

    @EJB
    private TipoRevisionFacadeLocal tipoRevisionFacade;
    private List<TipoRevision> tipoRevisionList;

    /**
     * Creates a new instance of indexBean
     */
    public indexBean() {

    }

    @PostConstruct
    public void init() {
        System.out.println("indexBean");
        tipoRevisionList = tipoRevisionFacade.findAll();
    }

    public List<TipoRevision> getTipoRevisionList() {
        return tipoRevisionList;
    }

    public void setTipoRevisionList(List<TipoRevision> tipoRevisionList) {
        this.tipoRevisionList = tipoRevisionList;
    }

}
