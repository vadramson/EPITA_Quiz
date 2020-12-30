package epita.fr.java.quiz.models;

public class Topics {
    private int idTopics;
    private String topic;

//    public Topics(int idTopics, String topic) {
//        this.idTopics = idTopics;
//        this.topic = topic;
//    }

    public int getIdTopics() {
        return idTopics;
    }

    public void setIdTopics(int idTopics) {
        this.idTopics = idTopics;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Override
    public String toString() {
        return "Topics{" +
                "idTopics=" + idTopics +
                ", topic='" + topic + '\'' +
                '}';
    }
}
