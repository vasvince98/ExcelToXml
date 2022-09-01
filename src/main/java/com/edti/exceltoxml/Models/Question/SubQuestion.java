package com.edti.exceltoxml.Models.Question;

public class SubQuestion extends Text {
    private Answer answer;
    private String format;

    public SubQuestion() {}

    public SubQuestion(Answer answer, String format) {
        this.answer = answer;
        this.format = format;
    }

    public SubQuestion(String text, Answer answer, String format) {
        super(text);
        this.answer = answer;
        this.format = format;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    @Override
    public String toString() {
        return "SubQuestion{" +
                "answer=" + answer +
                ", format='" + format + '\'' +
                '}';
    }
}
