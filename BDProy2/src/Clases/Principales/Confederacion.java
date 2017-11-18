/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases.Principales;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author M Express
 */
@Entity
@Table(name = "CONFEDERACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Confederacion.findAll", query = "SELECT c FROM Confederacion c")
    , @NamedQuery(name = "Confederacion.findByCodigoconfederacion", query = "SELECT c FROM Confederacion c WHERE c.codigoconfederacion = :codigoconfederacion")
    , @NamedQuery(name = "Confederacion.findByNombre", query = "SELECT c FROM Confederacion c WHERE c.nombre = :nombre")})
public class Confederacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODIGOCONFEDERACION")
    private String codigoconfederacion;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoconfederacion")
    private Collection<Equipo> equipoCollection;

    public Confederacion() {
    }

    public Confederacion(String codigoconfederacion) {
        this.codigoconfederacion = codigoconfederacion;
    }

    public Confederacion(String codigoconfederacion, String nombre) {
        this.codigoconfederacion = codigoconfederacion;
        this.nombre = nombre;
    }

    public String getCodigoconfederacion() {
        return codigoconfederacion;
    }

    public void setCodigoconfederacion(String codigoconfederacion) {
        this.codigoconfederacion = codigoconfederacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public Collection<Equipo> getEquipoCollection() {
        return equipoCollection;
    }

    public void setEquipoCollection(Collection<Equipo> equipoCollection) {
        this.equipoCollection = equipoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoconfederacion != null ? codigoconfederacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Confederacion)) {
            return false;
        }
        Confederacion other = (Confederacion) object;
        if ((this.codigoconfederacion == null && other.codigoconfederacion != null) || (this.codigoconfederacion != null && !this.codigoconfederacion.equals(other.codigoconfederacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Clases.Principales.Confederacion[ codigoconfederacion=" + codigoconfederacion + " ]";
    }
    
}
