/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases.Principales;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author M Express
 */
@Entity
@Table(name = "SEDES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sedes.findAll", query = "SELECT s FROM Sedes s")
    , @NamedQuery(name = "Sedes.findByNombreestadio", query = "SELECT s FROM Sedes s WHERE s.nombreestadio = :nombreestadio")
    , @NamedQuery(name = "Sedes.findByNombresede", query = "SELECT s FROM Sedes s WHERE s.nombresede = :nombresede")
    , @NamedQuery(name = "Sedes.findByCapacidad", query = "SELECT s FROM Sedes s WHERE s.capacidad = :capacidad")})
public class Sedes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "NOMBREESTADIO")
    private String nombreestadio;
    @Basic(optional = false)
    @Column(name = "NOMBRESEDE")
    private String nombresede;
    @Basic(optional = false)
    @Column(name = "CAPACIDAD")
    private BigInteger capacidad;
    @ManyToMany(mappedBy = "sedesCollection")
    private Collection<Partido> partidoCollection;

    public Sedes() {
    }

    public Sedes(String nombreestadio) {
        this.nombreestadio = nombreestadio;
    }

    public Sedes(String nombreestadio, String nombresede, BigInteger capacidad) {
        this.nombreestadio = nombreestadio;
        this.nombresede = nombresede;
        this.capacidad = capacidad;
    }

    public String getNombreestadio() {
        return nombreestadio;
    }

    public void setNombreestadio(String nombreestadio) {
        this.nombreestadio = nombreestadio;
    }

    public String getNombresede() {
        return nombresede;
    }

    public void setNombresede(String nombresede) {
        this.nombresede = nombresede;
    }

    public BigInteger getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(BigInteger capacidad) {
        this.capacidad = capacidad;
    }

    @XmlTransient
    public Collection<Partido> getPartidoCollection() {
        return partidoCollection;
    }

    public void setPartidoCollection(Collection<Partido> partidoCollection) {
        this.partidoCollection = partidoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nombreestadio != null ? nombreestadio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sedes)) {
            return false;
        }
        Sedes other = (Sedes) object;
        if ((this.nombreestadio == null && other.nombreestadio != null) || (this.nombreestadio != null && !this.nombreestadio.equals(other.nombreestadio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Clases.Principales.Sedes[ nombreestadio=" + nombreestadio + " ]";
    }
    
}
