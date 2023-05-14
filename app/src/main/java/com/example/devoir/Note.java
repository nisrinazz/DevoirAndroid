package com.example.devoir;

public class Note {
   private String label ;
    private Double score ;

    public String getLabel() {
        return label;
    }

    public Double getScore() {
        return score;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setScore(Double score) {
        this.score = score;
    }
    public Note(String label , Double score){
        this.label = label ;
        this.score = score ;
    }
}
