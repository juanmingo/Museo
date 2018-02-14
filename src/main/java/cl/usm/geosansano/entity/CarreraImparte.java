/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.usm.geosansano.entity;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juan
 */
@Entity
@Table(name = "carrera_imparte")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CarreraImparte.findAll", query = "SELECT c FROM CarreraImparte c")
    , @NamedQuery(name = "CarreraImparte.findByCodSedeCarrera", query = "SELECT c FROM CarreraImparte c WHERE c.carreraImpartePK.codSedeCarrera = :codSedeCarrera")
    , @NamedQuery(name = "CarreraImparte.findByCodCarrera", query = "SELECT c FROM CarreraImparte c WHERE c.carreraImpartePK.codCarrera = :codCarrera")
    , @NamedQuery(name = "CarreraImparte.findByCodSedeImparte", query = "SELECT c FROM CarreraImparte c WHERE c.carreraImpartePK.codSedeImparte = :codSedeImparte")
    , @NamedQuery(name = "CarreraImparte.findByCodMencion", query = "SELECT c FROM CarreraImparte c WHERE c.carreraImpartePK.codMencion = :codMencion")})
public class CarreraImparte implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CarreraImpartePK carreraImpartePK;
    @JoinColumns({
        @JoinColumn(name = "cod_sede_carrera", referencedColumnName = "sed_cod_sede", insertable = false, updatable = false)
        , @JoinColumn(name = "cod_carrera", referencedColumnName = "cod_carrera", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private CarreraSede carreraSede;
    @JoinColumn(name = "sed_cod_sede", referencedColumnName = "sed_cod_sede")
    @ManyToOne
    private Sede sedCodSede;

    public CarreraImparte() {
    }

    public CarreraImparte(CarreraImpartePK carreraImpartePK) {
        this.carreraImpartePK = carreraImpartePK;
    }

    public CarreraImparte(int codSedeCarrera, int codCarrera, int codSedeImparte, int codMencion) {
        this.carreraImpartePK = new CarreraImpartePK(codSedeCarrera, codCarrera, codSedeImparte, codMencion);
    }

    public CarreraImpartePK getCarreraImpartePK() {
        return carreraImpartePK;
    }

    public void setCarreraImpartePK(CarreraImpartePK carreraImpartePK) {
        this.carreraImpartePK = carreraImpartePK;
    }

    public CarreraSede getCarreraSede() {
        return carreraSede;
    }

    public void setCarreraSede(CarreraSede carreraSede) {
        this.carreraSede = carreraSede;
    }

    public Sede getSedCodSede() {
        return sedCodSede;
    }

    public void setSedCodSede(Sede sedCodSede) {
        this.sedCodSede = sedCodSede;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (carreraImpartePK != null ? carreraImpartePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CarreraImparte)) {
            return false;
        }
        CarreraImparte other = (CarreraImparte) object;
        if ((this.carreraImpartePK == null && other.carreraImpartePK != null) || (this.carreraImpartePK != null && !this.carreraImpartePK.equals(other.carreraImpartePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.usm.geosansano.entity.CarreraImparte[ carreraImpartePK=" + carreraImpartePK + " ]";
    }
    
}
