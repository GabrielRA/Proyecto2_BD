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
public class JueganPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "NUMEROPARTIDO")
    private BigInteger numeropartido;
    @Basic(optional = false)
    @Column(name = "CODIGO_PAIS")
    private BigInteger codigoPais;

    public JueganPK() {
    }

    public JueganPK(BigInteger numeropartido, BigInteger codigoPais) {
        this.numeropartido = numeropartido;
        this.codigoPais = codigoPais;
    }

    public BigInteger getNumeropartido() {
        return numeropartido;
    }

    public void setNumeropartido(BigInteger numeropartido) {
        this.numeropartido = numeropartido;
    }

    public BigInteger getCodigoPais() {
        return codigoPais;
    }

    public void setCodigoPais(BigInteger codigoPais) {
        this.codigoPais = codigoPais;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numeropartido != null ? numeropartido.hashCode() : 0);
        hash += (codigoPais != null ? codigoPais.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JueganPK)) {
            return false;
        }
        JueganPK other = (JueganPK) object;
        if ((this.numeropartido == null && other.numeropartido != null) || (this.numeropartido != null && !this.numeropartido.equals(other.numeropartido))) {
            return false;
        }
        if ((this.codigoPais == null && other.codigoPais != null) || (this.codigoPais != null && !this.codigoPais.equals(other.codigoPais))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Clases.Principales.JueganPK[ numeropartido=" + numeropartido + ", codigoPais=" + codigoPais + " ]";
    }
    
}
