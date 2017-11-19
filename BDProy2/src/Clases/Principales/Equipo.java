/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases.Principales;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author M Express
 */
@Entity
@Table(name = "EQUIPO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Equipo.findAll", query = "SELECT e FROM Equipo e")
    , @NamedQuery(name = "Equipo.findByCodigopais", query = "SELECT e FROM Equipo e WHERE e.codigopais = :codigopais")
    , @NamedQuery(name = "Equipo.findByNombre", query = "SELECT e FROM Equipo e WHERE e.nombre = :nombre")
    , @NamedQuery(name = "Equipo.findByGrupo", query = "SELECT e FROM Equipo e WHERE e.grupo = :grupo")})
public class Equipo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODIGOPAIS")
    private String codigopais;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "GRUPO")
    private String grupo;
    @JoinColumn(name = "CODIGOCONFEDERACION", referencedColumnName = "CODIGOCONFEDERACION")
    @ManyToOne(optional = false)
    private Confederacion codigoconfederacion;

    public Equipo() {
    }

    public Equipo(String codigopais) {
        this.codigopais = codigopais;
    }

    public Equipo(String codigopais, String nombre, String grupo) {
        this.codigopais = codigopais;
        this.nombre = nombre;
        this.grupo = grupo;
    }

    public String getCodigopais() {
        return codigopais;
    }

    public void setCodigopais(String codigopais) {
        this.codigopais = codigopais;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public Confederacion getCodigoconfederacion() {
        return codigoconfederacion;
    }

    public void setCodigoconfederacion(Confederacion codigoconfederacion) {
        this.codigoconfederacion = codigoconfederacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigopais != null ? codigopais.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Equipo)) {
            return false;
        }
        Equipo other = (Equipo) object;
        if ((this.codigopais == null && other.codigopais != null) || (this.codigopais != null && !this.codigopais.equals(other.codigopais))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Clases.Principales.Equipo[ codigopais=" + codigopais + " ]";
    }
    
}
