/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases.Principales;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author M Express
 */
@Embeddable
public class JugadorPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "NUMEROPASAPORTE")
    private BigInteger numeropasaporte;
    @Basic(optional = false)
    @Column(name = "CAMISA")
    private BigInteger camisa;

    public JugadorPK() {
    }

    public JugadorPK(BigInteger numeropasaporte, BigInteger camisa) {
        this.numeropasaporte = numeropasaporte;
        this.camisa = camisa;
    }

    public BigInteger getNumeropasaporte() {
        return numeropasaporte;
    }

    public void setNumeropasaporte(BigInteger numeropasaporte) {
        this.numeropasaporte = numeropasaporte;
    }

    public BigInteger getCamisa() {
        return camisa;
    }

    public void setCamisa(BigInteger camisa) {
        this.camisa = camisa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numeropasaporte != null ? numeropasaporte.hashCode() : 0);
        hash += (camisa != null ? camisa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JugadorPK)) {
            return false;
        }
        JugadorPK other = (JugadorPK) object;
        if ((this.numeropasaporte == null && other.numeropasaporte != null) || (this.numeropasaporte != null && !this.numeropasaporte.equals(other.numeropasaporte))) {
            return false;
        }
        if ((this.camisa == null && other.camisa != null) || (this.camisa != null && !this.camisa.equals(other.camisa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Clases.Principales.JugadorPK[ numeropasaporte=" + numeropasaporte + ", camisa=" + camisa + " ]";
    }
    
}
