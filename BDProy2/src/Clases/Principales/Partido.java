/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases.Principales;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author M Express
 */
@Entity
@Table(name = "PARTIDO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Partido.findAll", query = "SELECT p FROM Partido p")
    , @NamedQuery(name = "Partido.findByNumeropartido", query = "SELECT p FROM Partido p WHERE p.numeropartido = :numeropartido")
    , @NamedQuery(name = "Partido.findByEtapa", query = "SELECT p FROM Partido p WHERE p.etapa = :etapa")
    , @NamedQuery(name = "Partido.findByFecha", query = "SELECT p FROM Partido p WHERE p.fecha = :fecha")
    , @NamedQuery(name = "Partido.findByHora", query = "SELECT p FROM Partido p WHERE p.hora = :hora")
    , @NamedQuery(name = "Partido.findByCantidadAficionados", query = "SELECT p FROM Partido p WHERE p.cantidadAficionados = :cantidadAficionados")
    , @NamedQuery(name = "Partido.findByMinReposicionTiempo1", query = "SELECT p FROM Partido p WHERE p.minReposicionTiempo1 = :minReposicionTiempo1")
    , @NamedQuery(name = "Partido.findByMinReposicionTiempo2", query = "SELECT p FROM Partido p WHERE p.minReposicionTiempo2 = :minReposicionTiempo2")})
public class Partido implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "NUMEROPARTIDO")
    private BigDecimal numeropartido;
    @Basic(optional = false)
    @Column(name = "ETAPA")
    private String etapa;
    @Basic(optional = false)
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "HORA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hora;
    @Basic(optional = false)
    @Column(name = "CANTIDAD_AFICIONADOS")
    private BigInteger cantidadAficionados;
    @Basic(optional = false)
    @Column(name = "MIN_REPOSICION_TIEMPO1")
    private BigInteger minReposicionTiempo1;
    @Basic(optional = false)
    @Column(name = "MIN_REPOSICION_TIEMPO2")
    private BigInteger minReposicionTiempo2;
    @JoinTable(name = "DELEGADO", joinColumns = {
        @JoinColumn(name = "NUMEROPARTIDO", referencedColumnName = "NUMEROPARTIDO")}, inverseJoinColumns = {
        @JoinColumn(name = "NUMEROPASAPORTE", referencedColumnName = "NUMEROPASAPORTE")})
    @ManyToMany
    private Collection<Persona> personaCollection;
    @JoinTable(name = "DISPUTA", joinColumns = {
        @JoinColumn(name = "NUMEROPARTIDO", referencedColumnName = "NUMEROPARTIDO")}, inverseJoinColumns = {
        @JoinColumn(name = "NOMBREESTADIO", referencedColumnName = "NOMBREESTADIO")})
    @ManyToMany
    private Collection<Sedes> sedesCollection;
    @JoinTable(name = "CAMBIOS", joinColumns = {
        @JoinColumn(name = "NUMEROPARTIDO", referencedColumnName = "NUMEROPARTIDO")}, inverseJoinColumns = {
        @JoinColumn(name = "NUMEROPASAPORTE", referencedColumnName = "NUMEROPASAPORTE")})
    @ManyToMany
    private Collection<Persona> personaCollection1;
    @JoinTable(name = "MEDICO", joinColumns = {
        @JoinColumn(name = "NUMEROPARTIDO", referencedColumnName = "NUMEROPARTIDO")}, inverseJoinColumns = {
        @JoinColumn(name = "NUMEROPASAPORTE", referencedColumnName = "NUMEROPASAPORTE")})
    @ManyToMany
    private Collection<Persona> personaCollection2;
    @JoinTable(name = "TITULARES", joinColumns = {
        @JoinColumn(name = "NUMEROPARTIDO", referencedColumnName = "NUMEROPARTIDO")}, inverseJoinColumns = {
        @JoinColumn(name = "NUMEROPASAPORTE", referencedColumnName = "NUMEROPASAPORTE")})
    @ManyToMany
    private Collection<Persona> personaCollection3;
    @JoinTable(name = "SUPLENTES", joinColumns = {
        @JoinColumn(name = "NUMEROPARTIDO", referencedColumnName = "NUMEROPARTIDO")}, inverseJoinColumns = {
        @JoinColumn(name = "NUMEROPASAPORTE", referencedColumnName = "NUMEROPASAPORTE")})
    @ManyToMany
    private Collection<Persona> personaCollection4;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "partido")
    private Supervisa supervisa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "partido")
    private Collection<Juegan> jueganCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "partido")
    private Estadisticas estadisticas;

    public Partido() {
    }

    public Partido(BigDecimal numeropartido) {
        this.numeropartido = numeropartido;
    }

    public Partido(BigDecimal numeropartido, String etapa, Date fecha, Date hora, BigInteger cantidadAficionados, BigInteger minReposicionTiempo1, BigInteger minReposicionTiempo2) {
        this.numeropartido = numeropartido;
        this.etapa = etapa;
        this.fecha = fecha;
        this.hora = hora;
        this.cantidadAficionados = cantidadAficionados;
        this.minReposicionTiempo1 = minReposicionTiempo1;
        this.minReposicionTiempo2 = minReposicionTiempo2;
    }

    public BigDecimal getNumeropartido() {
        return numeropartido;
    }

    public void setNumeropartido(BigDecimal numeropartido) {
        this.numeropartido = numeropartido;
    }

    public String getEtapa() {
        return etapa;
    }

    public void setEtapa(String etapa) {
        this.etapa = etapa;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public BigInteger getCantidadAficionados() {
        return cantidadAficionados;
    }

    public void setCantidadAficionados(BigInteger cantidadAficionados) {
        this.cantidadAficionados = cantidadAficionados;
    }

    public BigInteger getMinReposicionTiempo1() {
        return minReposicionTiempo1;
    }

    public void setMinReposicionTiempo1(BigInteger minReposicionTiempo1) {
        this.minReposicionTiempo1 = minReposicionTiempo1;
    }

    public BigInteger getMinReposicionTiempo2() {
        return minReposicionTiempo2;
    }

    public void setMinReposicionTiempo2(BigInteger minReposicionTiempo2) {
        this.minReposicionTiempo2 = minReposicionTiempo2;
    }

    @XmlTransient
    public Collection<Persona> getPersonaCollection() {
        return personaCollection;
    }

    public void setPersonaCollection(Collection<Persona> personaCollection) {
        this.personaCollection = personaCollection;
    }

    @XmlTransient
    public Collection<Sedes> getSedesCollection() {
        return sedesCollection;
    }

    public void setSedesCollection(Collection<Sedes> sedesCollection) {
        this.sedesCollection = sedesCollection;
    }

    @XmlTransient
    public Collection<Persona> getPersonaCollection1() {
        return personaCollection1;
    }

    public void setPersonaCollection1(Collection<Persona> personaCollection1) {
        this.personaCollection1 = personaCollection1;
    }

    @XmlTransient
    public Collection<Persona> getPersonaCollection2() {
        return personaCollection2;
    }

    public void setPersonaCollection2(Collection<Persona> personaCollection2) {
        this.personaCollection2 = personaCollection2;
    }

    @XmlTransient
    public Collection<Persona> getPersonaCollection3() {
        return personaCollection3;
    }

    public void setPersonaCollection3(Collection<Persona> personaCollection3) {
        this.personaCollection3 = personaCollection3;
    }

    @XmlTransient
    public Collection<Persona> getPersonaCollection4() {
        return personaCollection4;
    }

    public void setPersonaCollection4(Collection<Persona> personaCollection4) {
        this.personaCollection4 = personaCollection4;
    }

    public Supervisa getSupervisa() {
        return supervisa;
    }

    public void setSupervisa(Supervisa supervisa) {
        this.supervisa = supervisa;
    }

    @XmlTransient
    public Collection<Juegan> getJueganCollection() {
        return jueganCollection;
    }

    public void setJueganCollection(Collection<Juegan> jueganCollection) {
        this.jueganCollection = jueganCollection;
    }

    public Estadisticas getEstadisticas() {
        return estadisticas;
    }

    public void setEstadisticas(Estadisticas estadisticas) {
        this.estadisticas = estadisticas;
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
        if (!(object instanceof Partido)) {
            return false;
        }
        Partido other = (Partido) object;
        if ((this.numeropartido == null && other.numeropartido != null) || (this.numeropartido != null && !this.numeropartido.equals(other.numeropartido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Clases.Principales.Partido[ numeropartido=" + numeropartido + " ]";
    }
    
}
