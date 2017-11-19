/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases.Principales;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "PERSONA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Persona.findAll", query = "SELECT p FROM Persona p")
    , @NamedQuery(name = "Persona.findByNumeropasaporte", query = "SELECT p FROM Persona p WHERE p.numeropasaporte = :numeropasaporte")
    , @NamedQuery(name = "Persona.findByNombre", query = "SELECT p FROM Persona p WHERE p.nombre = :nombre")
    , @NamedQuery(name = "Persona.findByApellido1", query = "SELECT p FROM Persona p WHERE p.apellido1 = :apellido1")
    , @NamedQuery(name = "Persona.findByApellido2", query = "SELECT p FROM Persona p WHERE p.apellido2 = :apellido2")
    , @NamedQuery(name = "Persona.findByFechanacimiento", query = "SELECT p FROM Persona p WHERE p.fechanacimiento = :fechanacimiento")})
public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "NUMEROPASAPORTE")
    private BigDecimal numeropasaporte;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "APELLIDO1")
    private String apellido1;
    @Basic(optional = false)
    @Column(name = "APELLIDO2")
    private String apellido2;
    @Basic(optional = false)
    @Column(name = "FECHANACIMIENTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechanacimiento;
    @ManyToMany(mappedBy = "personaCollection")
    private Collection<Partido> partidoCollection;
    @ManyToMany(mappedBy = "personaCollection1")
    private Collection<Partido> partidoCollection1;
    @ManyToMany(mappedBy = "personaCollection2")
    private Collection<Partido> partidoCollection2;
    @ManyToMany(mappedBy = "personaCollection3")
    private Collection<Partido> partidoCollection3;
    @ManyToMany(mappedBy = "personaCollection4")
    private Collection<Partido> partidoCollection4;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "persona")
    private Collection<Jugador> jugadorCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "persona")
    private Arbrito arbrito;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "persona")
    private Federativos federativos;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "numeropasaporte")
    private Collection<Supervisa> supervisaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "numeropasaporte")
    private Collection<Estadisticas> estadisticasCollection;
    @JoinColumn(name = "CODIGOPAIS", referencedColumnName = "CODIGOPAIS")
    @ManyToOne(optional = false)
    private Pais codigopais;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "persona")
    private Entrenador entrenador;

    public Persona() {
    }

    public Persona(BigDecimal numeropasaporte) {
        this.numeropasaporte = numeropasaporte;
    }

    public Persona(BigDecimal numeropasaporte, String nombre, String apellido1, String apellido2, Date fechanacimiento) {
        this.numeropasaporte = numeropasaporte;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.fechanacimiento = fechanacimiento;
    }

    public BigDecimal getNumeropasaporte() {
        return numeropasaporte;
    }

    public void setNumeropasaporte(BigDecimal numeropasaporte) {
        this.numeropasaporte = numeropasaporte;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public Date getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(Date fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    @XmlTransient
    public Collection<Partido> getPartidoCollection() {
        return partidoCollection;
    }

    public void setPartidoCollection(Collection<Partido> partidoCollection) {
        this.partidoCollection = partidoCollection;
    }

    @XmlTransient
    public Collection<Partido> getPartidoCollection1() {
        return partidoCollection1;
    }

    public void setPartidoCollection1(Collection<Partido> partidoCollection1) {
        this.partidoCollection1 = partidoCollection1;
    }

    @XmlTransient
    public Collection<Partido> getPartidoCollection2() {
        return partidoCollection2;
    }

    public void setPartidoCollection2(Collection<Partido> partidoCollection2) {
        this.partidoCollection2 = partidoCollection2;
    }

    @XmlTransient
    public Collection<Partido> getPartidoCollection3() {
        return partidoCollection3;
    }

    public void setPartidoCollection3(Collection<Partido> partidoCollection3) {
        this.partidoCollection3 = partidoCollection3;
    }

    @XmlTransient
    public Collection<Partido> getPartidoCollection4() {
        return partidoCollection4;
    }

    public void setPartidoCollection4(Collection<Partido> partidoCollection4) {
        this.partidoCollection4 = partidoCollection4;
    }

    @XmlTransient
    public Collection<Jugador> getJugadorCollection() {
        return jugadorCollection;
    }

    public void setJugadorCollection(Collection<Jugador> jugadorCollection) {
        this.jugadorCollection = jugadorCollection;
    }

    public Arbrito getArbrito() {
        return arbrito;
    }

    public void setArbrito(Arbrito arbrito) {
        this.arbrito = arbrito;
    }

    public Federativos getFederativos() {
        return federativos;
    }

    public void setFederativos(Federativos federativos) {
        this.federativos = federativos;
    }

    @XmlTransient
    public Collection<Supervisa> getSupervisaCollection() {
        return supervisaCollection;
    }

    public void setSupervisaCollection(Collection<Supervisa> supervisaCollection) {
        this.supervisaCollection = supervisaCollection;
    }

    @XmlTransient
    public Collection<Estadisticas> getEstadisticasCollection() {
        return estadisticasCollection;
    }

    public void setEstadisticasCollection(Collection<Estadisticas> estadisticasCollection) {
        this.estadisticasCollection = estadisticasCollection;
    }

    public Pais getCodigopais() {
        return codigopais;
    }

    public void setCodigopais(Pais codigopais) {
        this.codigopais = codigopais;
    }

    public Entrenador getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numeropasaporte != null ? numeropasaporte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Persona)) {
            return false;
        }
        Persona other = (Persona) object;
        if ((this.numeropasaporte == null && other.numeropasaporte != null) || (this.numeropasaporte != null && !this.numeropasaporte.equals(other.numeropasaporte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Clases.Principales.Persona[ numeropasaporte=" + numeropasaporte + " ]";
    }
    
}
