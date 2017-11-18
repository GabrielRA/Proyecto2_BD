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
@Table(name = "JUEGAN")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Juegan.findAll", query = "SELECT j FROM Juegan j")
    , @NamedQuery(name = "Juegan.findByNumeropartido", query = "SELECT j FROM Juegan j WHERE j.jueganPK.numeropartido = :numeropartido")
    , @NamedQuery(name = "Juegan.findByCodigoPais", query = "SELECT j FROM Juegan j WHERE j.jueganPK.codigoPais = :codigoPais")})
public class Juegan implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected JueganPK jueganPK;
    @JoinColumn(name = "NUMEROPARTIDO", referencedColumnName = "NUMEROPARTIDO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Partido partido;

    public Juegan() {
    }

    public Juegan(JueganPK jueganPK) {
        this.jueganPK = jueganPK;
    }

    public Juegan(BigInteger numeropartido, BigInteger codigoPais) {
        this.jueganPK = new JueganPK(numeropartido, codigoPais);
    }

    public JueganPK getJueganPK() {
        return jueganPK;
    }

    public void setJueganPK(JueganPK jueganPK) {
        this.jueganPK = jueganPK;
    }

    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (jueganPK != null ? jueganPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Juegan)) {
            return false;
        }
        Juegan other = (Juegan) object;
        if ((this.jueganPK == null && other.jueganPK != null) || (this.jueganPK != null && !this.jueganPK.equals(other.jueganPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Clases.Principales.Juegan[ jueganPK=" + jueganPK + " ]";
    }
    
}
