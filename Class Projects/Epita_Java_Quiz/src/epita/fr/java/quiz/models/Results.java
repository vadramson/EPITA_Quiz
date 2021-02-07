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
@Table(name = "results", catalog = "Epita_Java_Quiz", schema = "public")
@NamedQueries({
    @NamedQuery(name = "Results.findAll", query = "SELECT r FROM Results r")})
public class Results implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idresults")
    private Integer idresults;
    @Column(name = "quizscore")
    private Integer quizscore;
    @JoinColumn(name = "idquiz", referencedColumnName = "idquiz")
    @ManyToOne(optional = false)
    private Quiz idquiz;
    @JoinColumn(name = "idregister", referencedColumnName = "idregister")
    @ManyToOne(optional = false)
    private Register idregister;

    public Results() {
    }

    public Results(Integer idresults) {
        this.idresults = idresults;
    }

    public Integer getIdresults() {
        return idresults;
    }

    public void setIdresults(Integer idresults) {
        this.idresults = idresults;
    }

    public Integer getQuizscore() {
        return quizscore;
    }

    public void setQuizscore(Integer quizscore) {
        this.quizscore = quizscore;
    }

    public Quiz getIdquiz() {
        return idquiz;
    }

    public void setIdquiz(Quiz idquiz) {
        this.idquiz = idquiz;
    }

    public Register getIdregister() {
        return idregister;
    }

    public void setIdregister(Register idregister) {
        this.idregister = idregister;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idresults != null ? idresults.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Results)) {
            return false;
        }
        Results other = (Results) object;
        if ((this.idresults == null && other.idresults != null) || (this.idresults != null && !this.idresults.equals(other.idresults))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "epita.fr.java.quiz.models.Results[ idresults=" + idresults + " ]";
    }
    
}
