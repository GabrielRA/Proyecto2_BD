/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases.Principales;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author M Express
 */
@Entity
@Table(name = "SUPERVISA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Supervisa.findAll", query = "SELECT s FROM Supervisa s")
    , @NamedQuery(name = "Supervisa.findByNumeropartido", query = "SELECT s FROM Supervisa s WHERE s.numeropartido = :numeropartido")
    , @NamedQuery(name = "Supervisa.findByTipo", query = "SELECT s FROM Supervisa s WHERE s.tipo = :tipo")})
public class Supervisa implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "NUMEROPARTIDO")
    private BigDecimal numeropartido;
    @Basic(optional = false)
    @Column(name = "TIPO")
    private String tipo;
    @JoinColumn(name = "NUMEROPARTIDO", referencedColumnName = "NUMEROPARTIDO", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Partido partido;
    @JoinColumn(name = "NUMEROPASAPORTE", referencedColumnName = "NUMEROPASAPORTE")
    @ManyToOne(optional = false)
    private Persona numeropasaporte;

    public Supervisa() {
    }

    public Supervisa(BigDecimal numeropartido) {
        this.numeropartido = numeropartido;
    }

    public Supervisa(BigDecimal numeropartido, String tipo) {
        this.numeropartido = numeropartido;
        this.tipo = tipo;
    }

    public BigDecimal getNumeropartido() {
        return numeropartido;
    }

    public void setNumeropartido(BigDecimal numeropartido) {
        this.numeropartido = numeropartido;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }

    public Persona getNumeropasaporte() {
        return numeropasaporte;
    }

    public void setNumeropasaporte(Persona numeropasaporte) {
        this.numeropasaporte = numeropasaporte;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numeropartido != null ? numeropartido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Supervisa)) {
            return false;
        }
        Supervisa other = (Supervisa) object;
        if ((this.numeropartido == null && other.numeropartido != null) || (this.numeropartido != null && !this.numeropartido.equals(other.numeropartido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Clases.Principales.Supervisa[ numeropartido=" + numeropartido + " ]";
    }
    
}
