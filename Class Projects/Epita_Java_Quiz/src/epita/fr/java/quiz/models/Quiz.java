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
@Table(name = "quiz", catalog = "Epita_Java_Quiz", schema = "public")
@NamedQueries({
    @NamedQuery(name = "Quiz.findAll", query = "SELECT q FROM Quiz q")})
public class Quiz implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idquiz")
    private Integer idquiz;
    @Column(name = "quizcode")
    private String quizcode;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idquiz")
    private List<Topicquiz> topicquizList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idquiz")
    private List<Results> resultsList;

    public Quiz() {
    }

    public Quiz(Integer idquiz) {
        this.idquiz = idquiz;
    }

    public Integer getIdquiz() {
        return idquiz;
    }

    public void setIdquiz(Integer idquiz) {
        this.idquiz = idquiz;
    }

    public String getQuizcode() {
        return quizcode;
    }

    public void setQuizcode(String quizcode) {
        this.quizcode = quizcode;
    }

    public List<Topicquiz> getTopicquizList() {
        return topicquizList;
    }

    public void setTopicquizList(List<Topicquiz> topicquizList) {
        this.topicquizList = topicquizList;
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
        hash += (idquiz != null ? idquiz.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Quiz)) {
            return false;
        }
        Quiz other = (Quiz) object;
        if ((this.idquiz == null && other.idquiz != null) || (this.idquiz != null && !this.idquiz.equals(other.idquiz))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "epita.fr.java.quiz.models.Quiz[ idquiz=" + idquiz + " ]";
    }
    
}
