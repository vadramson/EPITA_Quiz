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
@Table(name = "topicquiz", catalog = "Epita_Java_Quiz", schema = "public")
@NamedQueries({
    @NamedQuery(name = "Topicquiz.findAll", query = "SELECT t FROM Topicquiz t")})
public class Topicquiz implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idquetionquiz")
    private Integer idquetionquiz;
    @Column(name = "questiondifficulty")
    private Integer questiondifficulty;
    @JoinColumn(name = "idquiz", referencedColumnName = "idquiz")
    @ManyToOne(optional = false)
    private Quiz idquiz;
    @JoinColumn(name = "idtopics", referencedColumnName = "idtopics")
    @ManyToOne(optional = false)
    private Topics idtopics;

    public Topicquiz() {
    }

    public Topicquiz(Integer idquetionquiz) {
        this.idquetionquiz = idquetionquiz;
    }

    public Integer getIdquetionquiz() {
        return idquetionquiz;
    }

    public void setIdquetionquiz(Integer idquetionquiz) {
        this.idquetionquiz = idquetionquiz;
    }

    public Integer getQuestiondifficulty() {
        return questiondifficulty;
    }

    public void setQuestiondifficulty(Integer questiondifficulty) {
        this.questiondifficulty = questiondifficulty;
    }

    public Quiz getIdquiz() {
        return idquiz;
    }

    public void setIdquiz(Quiz idquiz) {
        this.idquiz = idquiz;
    }

    public Topics getIdtopics() {
        return idtopics;
    }

    public void setIdtopics(Topics idtopics) {
        this.idtopics = idtopics;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idquetionquiz != null ? idquetionquiz.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Topicquiz)) {
            return false;
        }
        Topicquiz other = (Topicquiz) object;
        if ((this.idquetionquiz == null && other.idquetionquiz != null) || (this.idquetionquiz != null && !this.idquetionquiz.equals(other.idquetionquiz))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "epita.fr.java.quiz.models.Topicquiz[ idquetionquiz=" + idquetionquiz + " ]";
    }
    
}
