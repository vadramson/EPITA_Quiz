/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epita.fr.java.quiz.models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Alcatras
 */
@Entity
@Table(name = "admincodes", catalog = "Epita_Java_Quiz", schema = "public")
@NamedQueries({
    @NamedQuery(name = "Admincodes.findAll", query = "SELECT a FROM Admincodes a")})
public class Admincodes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idadmincodes")
    private Integer idadmincodes;
    @Column(name = "code")
    private Integer code;

    public Admincodes() {
    }

    public Admincodes(Integer idadmincodes) {
        this.idadmincodes = idadmincodes;
    }

    public Integer getIdadmincodes() {
        return idadmincodes;
    }

    public void setIdadmincodes(Integer idadmincodes) {
        this.idadmincodes = idadmincodes;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idadmincodes != null ? idadmincodes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Admincodes)) {
            return false;
        }
        Admincodes other = (Admincodes) object;
        if ((this.idadmincodes == null && other.idadmincodes != null) || (this.idadmincodes != null && !this.idadmincodes.equals(other.idadmincodes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "epita.fr.java.quiz.models.Admincodes[ idadmincodes=" + idadmincodes + " ]";
    }
    
}
