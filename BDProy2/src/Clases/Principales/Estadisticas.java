/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases.Principales;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
@Table(name = "ESTADISTICAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estadisticas.findAll", query = "SELECT e FROM Estadisticas e")
    , @NamedQuery(name = "Estadisticas.findByNumeropartido", query = "SELECT e FROM Estadisticas e WHERE e.numeropartido = :numeropartido")
    , @NamedQuery(name = "Estadisticas.findByMinuto", query = "SELECT e FROM Estadisticas e WHERE e.minuto = :minuto")
    , @NamedQuery(name = "Estadisticas.findByTipo", query = "SELECT e FROM Estadisticas e WHERE e.tipo = :tipo")})
public class Estadisticas implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "NUMEROPARTIDO")
    private BigDecimal numeropartido;
    @Basic(optional = false)
    @Column(name = "MINUTO")
    private BigInteger minuto;
    @Basic(optional = false)
    @Column(name = "TIPO")
    private String tipo;
    @JoinColumn(name = "NUMEROPARTIDO", referencedColumnName = "NUMEROPARTIDO", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Partido partido;
    @JoinColumn(name = "NUMEROPASAPORTE", referencedColumnName = "NUMEROPASAPORTE")
    @ManyToOne(optional = false)
    private Persona numeropasaporte;

    public Estadisticas() {
    }

    public Estadisticas(BigDecimal numeropartido) {
        this.numeropartido = numeropartido;
    }

    public Estadisticas(BigDecimal numeropartido, BigInteger minuto, String tipo) {
        this.numeropartido = numeropartido;
        this.minuto = minuto;
        this.tipo = tipo;
    }

    public BigDecimal getNumeropartido() {
        return numeropartido;
    }

    public void setNumeropartido(BigDecimal numeropartido) {
        this.numeropartido = numeropartido;
    }

    public BigInteger getMinuto() {
        return minuto;
    }

    public void setMinuto(BigInteger minuto) {
        this.minuto = minuto;
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
        if (!(object instanceof Estadisticas)) {
            return false;
        }
        Estadisticas other = (Estadisticas) object;
        if ((this.numeropartido == null && other.numeropartido != null) || (this.numeropartido != null && !this.numeropartido.equals(other.numeropartido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Clases.Principales.Estadisticas[ numeropartido=" + numeropartido + " ]";
    }
    
}
