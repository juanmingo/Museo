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
@Table(name = "pais")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pais.findAll", query = "SELECT p FROM Pais p")
    , @NamedQuery(name = "Pais.findByCodPais", query = "SELECT p FROM Pais p WHERE p.codPais = :codPais")
    , @NamedQuery(name = "Pais.findByNomPais", query = "SELECT p FROM Pais p WHERE p.nomPais = :nomPais")
    , @NamedQuery(name = "Pais.findByNomNacionalidad", query = "SELECT p FROM Pais p WHERE p.nomNacionalidad = :nomNacionalidad")
    , @NamedQuery(name = "Pais.findByNumPais", query = "SELECT p FROM Pais p WHERE p.numPais = :numPais")})
public class Pais implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "cod_pais")
    private Integer codPais;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "nom_pais")
    private String nomPais;
    @Size(max = 50)
    @Column(name = "nom_nacionalidad")
    private String nomNacionalidad;
    @Column(name = "num_pais")
    private Integer numPais;
    @OneToMany(mappedBy = "codPais")
    private List<MuseoUsuario> museoUsuarioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codPais")
    private List<MuseoProyecto> museoProyectoList;

    public Pais() {
    }

    public Pais(Integer codPais) {
        this.codPais = codPais;
    }

    public Pais(Integer codPais, String nomPais) {
        this.codPais = codPais;
        this.nomPais = nomPais;
    }

    public Integer getCodPais() {
        return codPais;
    }

    public void setCodPais(Integer codPais) {
        this.codPais = codPais;
    }

    public String getNomPais() {
        return nomPais;
    }

    public void setNomPais(String nomPais) {
        this.nomPais = nomPais;
    }

    public String getNomNacionalidad() {
        return nomNacionalidad;
    }

    public void setNomNacionalidad(String nomNacionalidad) {
        this.nomNacionalidad = nomNacionalidad;
    }

    public Integer getNumPais() {
        return numPais;
    }

    public void setNumPais(Integer numPais) {
        this.numPais = numPais;
    }

    @XmlTransient
    public List<MuseoUsuario> getMuseoUsuarioList() {
        return museoUsuarioList;
    }

    public void setMuseoUsuarioList(List<MuseoUsuario> museoUsuarioList) {
        this.museoUsuarioList = museoUsuarioList;
    }

    @XmlTransient
    public List<MuseoProyecto> getMuseoProyectoList() {
        return museoProyectoList;
    }

    public void setMuseoProyectoList(List<MuseoProyecto> museoProyectoList) {
        this.museoProyectoList = museoProyectoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codPais != null ? codPais.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pais)) {
            return false;
        }
        Pais other = (Pais) object;
        if ((this.codPais == null && other.codPais != null) || (this.codPais != null && !this.codPais.equals(other.codPais))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.usm.geosansano.entity.Pais[ codPais=" + codPais + " ]";
    }
    
}
