/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.usm.geosansano.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Juan
 */
@Entity
@Table(name = "sede")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sede.findAll", query = "SELECT s FROM Sede s")
    , @NamedQuery(name = "Sede.findBySedCodSede", query = "SELECT s FROM Sede s WHERE s.sedCodSede = :sedCodSede")
    , @NamedQuery(name = "Sede.findBySedNomSede", query = "SELECT s FROM Sede s WHERE s.sedNomSede = :sedNomSede")
    , @NamedQuery(name = "Sede.findBySedDireccion", query = "SELECT s FROM Sede s WHERE s.sedDireccion = :sedDireccion")
    , @NamedQuery(name = "Sede.findBySedAbreviacion", query = "SELECT s FROM Sede s WHERE s.sedAbreviacion = :sedAbreviacion")
    , @NamedQuery(name = "Sede.findBySedNombreCampus", query = "SELECT s FROM Sede s WHERE s.sedNombreCampus = :sedNombreCampus")})
public class Sede implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "sed_cod_sede")
    private Integer sedCodSede;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "sed_nom_sede")
    private String sedNomSede;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "sed_direccion")
    private String sedDireccion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "sed_abreviacion")
    private String sedAbreviacion;
    @Size(max = 20)
    @Column(name = "sed_nombre_campus")
    private String sedNombreCampus;
    @OneToMany(mappedBy = "sedCodSedeFisica")
    private List<MuseoUsuarioCarrera> museoUsuarioCarreraList;
    @OneToMany(mappedBy = "sedCodSede")
    private List<CarreraImparte> carreraImparteList;

    public Sede() {
    }

    public Sede(Integer sedCodSede) {
        this.sedCodSede = sedCodSede;
    }

    public Sede(Integer sedCodSede, String sedNomSede, String sedDireccion, String sedAbreviacion) {
        this.sedCodSede = sedCodSede;
        this.sedNomSede = sedNomSede;
        this.sedDireccion = sedDireccion;
        this.sedAbreviacion = sedAbreviacion;
    }

    public Integer getSedCodSede() {
        return sedCodSede;
    }

    public void setSedCodSede(Integer sedCodSede) {
        this.sedCodSede = sedCodSede;
    }

    public String getSedNomSede() {
        return sedNomSede;
    }

    public void setSedNomSede(String sedNomSede) {
        this.sedNomSede = sedNomSede;
    }

    public String getSedDireccion() {
        return sedDireccion;
    }

    public void setSedDireccion(String sedDireccion) {
        this.sedDireccion = sedDireccion;
    }

    public String getSedAbreviacion() {
        return sedAbreviacion;
    }

    public void setSedAbreviacion(String sedAbreviacion) {
        this.sedAbreviacion = sedAbreviacion;
    }

    public String getSedNombreCampus() {
        return sedNombreCampus;
    }

    public void setSedNombreCampus(String sedNombreCampus) {
        this.sedNombreCampus = sedNombreCampus;
    }

    @XmlTransient
    public List<MuseoUsuarioCarrera> getMuseoUsuarioCarreraList() {
        return museoUsuarioCarreraList;
    }

    public void setMuseoUsuarioCarreraList(List<MuseoUsuarioCarrera> museoUsuarioCarreraList) {
        this.museoUsuarioCarreraList = museoUsuarioCarreraList;
    }

    @XmlTransient
    public List<CarreraImparte> getCarreraImparteList() {
        return carreraImparteList;
    }

    public void setCarreraImparteList(List<CarreraImparte> carreraImparteList) {
        this.carreraImparteList = carreraImparteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sedCodSede != null ? sedCodSede.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sede)) {
            return false;
        }
        Sede other = (Sede) object;
        if ((this.sedCodSede == null && other.sedCodSede != null) || (this.sedCodSede != null && !this.sedCodSede.equals(other.sedCodSede))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.usm.geosansano.entity.Sede[ sedCodSede=" + sedCodSede + " ]";
    }
    
}
