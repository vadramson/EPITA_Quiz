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
@Table(name = "answers", catalog = "Epita_Java_Quiz", schema = "public")
@NamedQueries({
    @NamedQuery(name = "Answers.findAll", query = "SELECT a FROM Answers a")})
public class Answers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idanswers")
    private Integer idanswers;
    @Column(name = "answer")
    private String answer;
    @JoinColumn(name = "idquestions", referencedColumnName = "idquestions")
    @ManyToOne(optional = false)
    private Questions idquestions;

    public Answers() {
    }

    public Answers(Integer idanswers) {
        this.idanswers = idanswers;
    }

    public Integer getIdanswers() {
        return idanswers;
    }

    public void setIdanswers(Integer idanswers) {
        this.idanswers = idanswers;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
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
        hash += (idanswers != null ? idanswers.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Answers)) {
            return false;
        }
        Answers other = (Answers) object;
        if ((this.idanswers == null && other.idanswers != null) || (this.idanswers != null && !this.idanswers.equals(other.idanswers))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "epita.fr.java.quiz.models.Answers[ idanswers=" + idanswers + " ]";
    }
    
}
