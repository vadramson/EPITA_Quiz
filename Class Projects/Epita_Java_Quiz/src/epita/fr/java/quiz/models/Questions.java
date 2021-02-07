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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Alcatras
 */
@Entity
@Table(name = "questions", catalog = "Epita_Java_Quiz", schema = "public")
@NamedQueries({
    @NamedQuery(name = "Questions.findAll", query = "SELECT q FROM Questions q")})
public class Questions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idquestions")
    private Integer idquestions;
    @Column(name = "difficulty")
    private Integer difficulty;
    @Column(name = "question")
    private String question;
    @Column(name = "questiontype")
    private String questiontype;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idquestions")
    private List<Mcqanswers> mcqanswersList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idquestions")
    private List<Answers> answersList;
    @JoinColumn(name = "idtopics", referencedColumnName = "idtopics")
    @ManyToOne(optional = false)
    private Topics idtopics;

    public Questions() {
    }

    public Questions(Integer idquestions) {
        this.idquestions = idquestions;
    }

    public Integer getIdquestions() {
        return idquestions;
    }

    public void setIdquestions(Integer idquestions) {
        this.idquestions = idquestions;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestiontype() {
        return questiontype;
    }

    public void setQuestiontype(String questiontype) {
        this.questiontype = questiontype;
    }

    public List<Mcqanswers> getMcqanswersList() {
        return mcqanswersList;
    }

    public void setMcqanswersList(List<Mcqanswers> mcqanswersList) {
        this.mcqanswersList = mcqanswersList;
    }

    public List<Answers> getAnswersList() {
        return answersList;
    }

    public void setAnswersList(List<Answers> answersList) {
        this.answersList = answersList;
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
        hash += (idquestions != null ? idquestions.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Questions)) {
            return false;
        }
        Questions other = (Questions) object;
        if ((this.idquestions == null && other.idquestions != null) || (this.idquestions != null && !this.idquestions.equals(other.idquestions))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "epita.fr.java.quiz.models.Questions[ idquestions=" + idquestions + " ]";
    }
    
}
