package sample.model;

import java.util.List;

public class Paper {

    private int id;
    private String question;
    private List<String> answars;

    public Paper(int id, String question, List<String> answars) {
        this.id = id;
        this.question = question;
        this.answars = answars;
    }

    public Paper() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getAnswars() {
        return answars;
    }

    public void setAnswars(List<String> answars) {
        this.answars = answars;
    }
}
