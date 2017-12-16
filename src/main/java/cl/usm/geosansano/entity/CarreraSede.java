/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.usm.geosansano.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
 * @author Juan Delgado
 */
@Entity
@Table(name = "carrera_sede")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CarreraSede.findAll", query = "SELECT c FROM CarreraSede c")
    , @NamedQuery(name = "CarreraSede.findBySedCodSede", query = "SELECT c FROM CarreraSede c WHERE c.carreraSedePK.sedCodSede = :sedCodSede")
    , @NamedQuery(name = "CarreraSede.findByCodCarrera", query = "SELECT c FROM CarreraSede c WHERE c.carreraSedePK.codCarrera = :codCarrera")
    , @NamedQuery(name = "CarreraSede.findByNomCarrera", query = "SELECT c FROM CarreraSede c WHERE c.nomCarrera = :nomCarrera")
    , @NamedQuery(name = "CarreraSede.findByAbreviacion", query = "SELECT c FROM CarreraSede c WHERE c.abreviacion = :abreviacion")})
public class CarreraSede implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CarreraSedePK carreraSedePK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nom_carrera")
    private String nomCarrera;
    @Size(max = 50)
    @Column(name = "abreviacion")
    private String abreviacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "carreraSede")
    private List<MuseoUsuarioCarrera> museoUsuarioCarreraList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "carreraSede")
    private List<CarreraImparte> carreraImparteList;

    public CarreraSede() {
    }

    public CarreraSede(CarreraSedePK carreraSedePK) {
        this.carreraSedePK = carreraSedePK;
    }

    public CarreraSede(CarreraSedePK carreraSedePK, String nomCarrera) {
        this.carreraSedePK = carreraSedePK;
        this.nomCarrera = nomCarrera;
    }

    public CarreraSede(int sedCodSede, int codCarrera) {
        this.carreraSedePK = new CarreraSedePK(sedCodSede, codCarrera);
    }

    public CarreraSedePK getCarreraSedePK() {
        return carreraSedePK;
    }

    public void setCarreraSedePK(CarreraSedePK carreraSedePK) {
        this.carreraSedePK = carreraSedePK;
    }

    public String getNomCarrera() {
        return nomCarrera;
    }

    public void setNomCarrera(String nomCarrera) {
        this.nomCarrera = nomCarrera;
    }

    public String getAbreviacion() {
        return abreviacion;
    }

    public void setAbreviacion(String abreviacion) {
        this.abreviacion = abreviacion;
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
        hash += (carreraSedePK != null ? carreraSedePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CarreraSede)) {
            return false;
        }
        CarreraSede other = (CarreraSede) object;
        if ((this.carreraSedePK == null && other.carreraSedePK != null) || (this.carreraSedePK != null && !this.carreraSedePK.equals(other.carreraSedePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.usm.geosansano.entity.CarreraSede[ carreraSedePK=" + carreraSedePK + " ]";
    }
    
}
