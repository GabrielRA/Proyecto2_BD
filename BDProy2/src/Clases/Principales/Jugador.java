/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases.Principales;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "JUGADOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Jugador.findAll", query = "SELECT j FROM Jugador j")
    , @NamedQuery(name = "Jugador.findByNumeropasaporte", query = "SELECT j FROM Jugador j WHERE j.jugadorPK.numeropasaporte = :numeropasaporte")
    , @NamedQuery(name = "Jugador.findByCamisa", query = "SELECT j FROM Jugador j WHERE j.jugadorPK.camisa = :camisa")})
public class Jugador implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected JugadorPK jugadorPK;
    @JoinColumn(name = "NUMEROPASAPORTE", referencedColumnName = "NUMEROPASAPORTE", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Persona persona;

    public Jugador() {
    }

    public Jugador(JugadorPK jugadorPK) {
        this.jugadorPK = jugadorPK;
    }

    public Jugador(BigInteger numeropasaporte, BigInteger camisa) {
        this.jugadorPK = new JugadorPK(numeropasaporte, camisa);
    }

    public JugadorPK getJugadorPK() {
        return jugadorPK;
    }

    public void setJugadorPK(JugadorPK jugadorPK) {
        this.jugadorPK = jugadorPK;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (jugadorPK != null ? jugadorPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Jugador)) {
            return false;
        }
        Jugador other = (Jugador) object;
        if ((this.jugadorPK == null && other.jugadorPK != null) || (this.jugadorPK != null && !this.jugadorPK.equals(other.jugadorPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Clases.Principales.Jugador[ jugadorPK=" + jugadorPK + " ]";
    }
    
}
