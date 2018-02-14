/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.usm.geosansano.mannaged.beans;

import cl.usm.geosansano.entity.MuseoUsuario;
import cl.usm.geosansano.entity.Sede;
import cl.usm.geosansano.entity.TipoRevision;
import cl.usm.geosansano.functions.FuncionMD5;
import cl.usm.geosansano.sessions.beans.MuseoUsuarioFacadeLocal;
import cl.usm.geosansano.sessions.beans.SedeFacadeLocal;
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
    
    @EJB
    private MuseoUsuarioFacadeLocal museoUsuarioFacade;
    private List<MuseoUsuario> museoUsuarioList;
    private MuseoUsuario museoUsuario;
    
    @EJB
    private SedeFacadeLocal sedeListFacade;
    private List<Sede> sedeList;

    /**
     * Creates a new instance of indexBean
     */
    public indexBean() {
        
    }
    
    @PostConstruct
    public void init() {
        System.out.println("indexBean");
        
        tipoRevisionList = tipoRevisionFacade.findAll();
        System.out.println("tipoRevisionList: " + tipoRevisionList.size());
        
        sedeList = sedeListFacade.findAll();
        System.out.println("sedeList: " + sedeList.size());
        
        museoUsuarioList = museoUsuarioFacade.findAll();
        System.out.println("museoUsuarioList: " + museoUsuarioList.size());
        
        museoUsuario = museoUsuarioFacade.findByCuenta("juan.delgado@usm.cl", "A94652AA97C7211BA8954DD15A3CF838");
        //museoUsuario = museoUsuarioFacade.findByCuenta("juan.delgado@usm.cl", "A94652AA97C7211BA8954DD15A3CF838");
        System.out.println("1 - museoUsuario: " + museoUsuario);
        
        System.out.println(FuncionMD5.obtenerHash("juan").toUpperCase());
        
    }
    
    public List<TipoRevision> getTipoRevisionList() {
        return tipoRevisionList;
    }
    
    public void setTipoRevisionList(List<TipoRevision> tipoRevisionList) {
        this.tipoRevisionList = tipoRevisionList;
    }
    
    public List<MuseoUsuario> getMuseoUsuarioList() {
        return museoUsuarioList;
    }
    
    public void setMuseoUsuarioList(List<MuseoUsuario> museoUsuarioList) {
        this.museoUsuarioList = museoUsuarioList;
    }
    
    public List<Sede> getSedeList() {
        return sedeList;
    }
    
    public void setSedeList(List<Sede> sedeList) {
        this.sedeList = sedeList;
    }
    
    public MuseoUsuario getMuseoUsuario() {
        return museoUsuario;
    }
    
    public void setMuseoUsuario(MuseoUsuario museoUsuario) {
        this.museoUsuario = museoUsuario;
    }
    
}
