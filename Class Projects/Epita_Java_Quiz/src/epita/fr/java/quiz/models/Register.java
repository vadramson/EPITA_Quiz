/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epita.fr.java.quiz.models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Alcatras
 */
@Entity
@Table(name = "register", catalog = "Epita_Java_Quiz", schema = "public")
@NamedQueries({
    @NamedQuery(name = "Register.findAll", query = "SELECT r FROM Register r")})
public class Register implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idregister")
    private Integer idregister;
    @Column(name = "studentmatricule")
    private String studentmatricule;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idregister")
    private List<Results> resultsList;

    public Register() {
    }

    public Register(Integer idregister) {
        this.idregister = idregister;
    }

    public Integer getIdregister() {
        return idregister;
    }

    public void setIdregister(Integer idregister) {
        this.idregister = idregister;
    }

    public String getStudentmatricule() {
        return studentmatricule;
    }

    public void setStudentmatricule(String studentmatricule) {
        this.studentmatricule = studentmatricule;
    }

    public List<Results> getResultsList() {
        return resultsList;
    }

    public void setResultsList(List<Results> resultsList) {
        this.resultsList = resultsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idregister != null ? idregister.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Register)) {
            return false;
        }
        Register other = (Register) object;
        if ((this.idregister == null && other.idregister != null) || (this.idregister != null && !this.idregister.equals(other.idregister))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "epita.fr.java.quiz.models.Register[ idregister=" + idregister + " ]";
    }
    
}
