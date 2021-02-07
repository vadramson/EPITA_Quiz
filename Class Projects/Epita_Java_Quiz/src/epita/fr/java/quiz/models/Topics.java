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
@Table(name = "topics", catalog = "Epita_Java_Quiz", schema = "public")
@NamedQueries({
    @NamedQuery(name = "Topics.findAll", query = "SELECT t FROM Topics t")})
public class Topics implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idtopics")
    private Integer idtopics;
    @Column(name = "topic")
    private String topic;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idtopics")
    private List<Topicquiz> topicquizList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idtopics")
    private List<Questions> questionsList;

    public Topics() {
    }

    public Topics(Integer idtopics) {
        this.idtopics = idtopics;
    }

    public Integer getIdtopics() {
        return idtopics;
    }

    public void setIdtopics(Integer idtopics) {
        this.idtopics = idtopics;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public List<Topicquiz> getTopicquizList() {
        return topicquizList;
    }

    public void setTopicquizList(List<Topicquiz> topicquizList) {
        this.topicquizList = topicquizList;
    }

    public List<Questions> getQuestionsList() {
        return questionsList;
    }

    public void setQuestionsList(List<Questions> questionsList) {
        this.questionsList = questionsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtopics != null ? idtopics.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Topics)) {
            return false;
        }
        Topics other = (Topics) object;
        if ((this.idtopics == null && other.idtopics != null) || (this.idtopics != null && !this.idtopics.equals(other.idtopics))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "epita.fr.java.quiz.models.Topics[ idtopics=" + idtopics + " ]";
    }
    
}
