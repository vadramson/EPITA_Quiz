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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Alcatras
 */
@Entity
@Table(name = "mcqanswers", catalog = "Epita_Java_Quiz", schema = "public")
@NamedQueries({
    @NamedQuery(name = "Mcqanswers.findAll", query = "SELECT m FROM Mcqanswers m")})
public class Mcqanswers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idmcqanswers")
    private Integer idmcqanswers;
    @Column(name = "a")
    private String a;
    @Column(name = "b")
    private String b;
    @Column(name = "c")
    private String c;
    @Column(name = "d")
    private String d;
    @Column(name = "solution")
    private String solution;
    @JoinColumn(name = "idquestions", referencedColumnName = "idquestions")
    @ManyToOne(optional = false)
    private Questions idquestions;

    public Mcqanswers() {
    }

    public Mcqanswers(Integer idmcqanswers) {
        this.idmcqanswers = idmcqanswers;
    }

    public Integer getIdmcqanswers() {
        return idmcqanswers;
    }

    public void setIdmcqanswers(Integer idmcqanswers) {
        this.idmcqanswers = idmcqanswers;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public Questions getIdquestions() {
        return idquestions;
    }

    public void setIdquestions(Questions idquestions) {
        this.idquestions = idquestions;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmcqanswers != null ? idmcqanswers.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mcqanswers)) {
            return false;
        }
        Mcqanswers other = (Mcqanswers) object;
        if ((this.idmcqanswers == null && other.idmcqanswers != null) || (this.idmcqanswers != null && !this.idmcqanswers.equals(other.idmcqanswers))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "epita.fr.java.quiz.models.Mcqanswers[ idmcqanswers=" + idmcqanswers + " ]";
    }
    
}
