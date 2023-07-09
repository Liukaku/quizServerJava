package github.com.liukaku.javaQuiz;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

public class QuizTable {

    public String id;
    public String quizTitle;
    public String ownerId;

    public QuizTable(ArrayList<String> data) {
        this.id = data.get(0);
        this.quizTitle = data.get(1);
        this.ownerId = data.get(2);
    }

    @JsonIgnore
    public String everything(){
        return this.id;
    }

    @JsonIgnore
    public String asJson(){
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuizTitle() {
        return quizTitle;
    }

    public void setQuizTitle(String quizTitle) {
        this.quizTitle = quizTitle;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public String toString() {
        return "QuizTable{" +
                "id='" + id + '\'' +
                ", quizTitle='" + quizTitle + '\'' +
                ", ownerId='" + ownerId + '\'' +
                '}';
    }
}
