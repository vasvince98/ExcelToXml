package com.edti.exceltoxml.Models.Question;

import com.edti.exceltoxml.Models.Category.Category;
import com.edti.exceltoxml.Models.Category.Info;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


@XmlType(propOrder = {"category", "info", "name", "questiontext", "generalfeedback", "defaultgrade", "penalty",
"hidden", "idnumber", "single", "shuffleanswers", "answernumbering", "showstandardinstruction", "correctfeedback",
"partiallycorrectfeedback", "incorrectfeedback", "subquestion","answer", "dragbox"})
public class Question {



    private Category category;
    private Info info;
    private String type;
    private Name name;
    private QuestionText questiontext;
    private GeneralFeedback generalfeedback;
    private double defaultgrade;
    private double penalty;
    private int hidden;
    private String idnumber;
    private boolean single;
    private boolean shuffleanswers;
    private String answernumbering;
    private int showstandardinstruction;
    private CorrectFeedback correctfeedback;
    private PartiallyCorrectFeedback partiallycorrectfeedback;
    private IncorrectFeedback incorrectfeedback;
    private List<SubQuestion> subquestion;
    private List<Answer> answer;
    private List<Dragbox> dragbox;

    public Question() {}

    public Question(String type, Category category, Info info, String idnumber) {
        this.category = category;
        this.info = info;
        this.idnumber = idnumber;
        this.type = type;
    }

    public Question(String type, Name name, QuestionText questiontext, GeneralFeedback generalfeedback,
                    double defaultgrade, double penalty, int hidden, String idnumber,
                    boolean single, boolean shuffleanswers, String answernumbering,
                    int showstandardinstruction, CorrectFeedback correctfeedback,
                    PartiallyCorrectFeedback partiallyCorrectFeedback,
                    IncorrectFeedback incorrectFeedback) {
        this.type = type;
        this.name = name;
        this.questiontext = questiontext;
        this.generalfeedback = generalfeedback;
        this.defaultgrade = defaultgrade;
        this.penalty = penalty;
        this.hidden = hidden;
        this.idnumber = idnumber;
        this.single = single;
        this.shuffleanswers = shuffleanswers;
        this.answernumbering = answernumbering;
        this.showstandardinstruction = showstandardinstruction;
        this.correctfeedback = correctfeedback;
        this.partiallycorrectfeedback = partiallyCorrectFeedback;
        this.incorrectfeedback = incorrectFeedback;
        this.subquestion = new ArrayList<>();
        this.answer = new ArrayList<>();
        this.dragbox = new ArrayList<>();
    }


    public List<Dragbox> getDragbox() {
        return dragbox;
    }

    public void setDragbox(List<Dragbox> dragbox) {
        this.dragbox = dragbox;
    }

    public List<SubQuestion> getSubquestion() {
        return subquestion;
    }

    public void setSubquestion(List<SubQuestion> subquestion) {
        this.subquestion = subquestion;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    @XmlAttribute
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public QuestionText getQuestiontext() {
        return questiontext;
    }

    public void setQuestiontext(QuestionText questiontext) {
        this.questiontext = questiontext;
    }

    public GeneralFeedback getGeneralfeedback() {
        return generalfeedback;
    }

    public void setGeneralfeedback(GeneralFeedback generalfeedback) {
        this.generalfeedback = generalfeedback;
    }

    public double getDefaultgrade() {
        return defaultgrade;
    }

    public void setDefaultgrade(double defaultgrade) {
        this.defaultgrade = defaultgrade;
    }

    public double getPenalty() {
        return penalty;
    }

    public void setPenalty(double penalty) {
        this.penalty = penalty;
    }

    public int getHidden() {
        return hidden;
    }

    public void setHidden(int hidden) {
        this.hidden = hidden;
    }

    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    public boolean isSingle() {
        return single;
    }

    public void setSingle(boolean single) {
        this.single = single;
    }

    public boolean isShuffleanswers() {
        return shuffleanswers;
    }

    public void setShuffleanswers(boolean shuffleanswers) {
        this.shuffleanswers = shuffleanswers;
    }

    public String getAnswernumbering() {
        return answernumbering;
    }

    public void setAnswernumbering(String answernumbering) {
        this.answernumbering = answernumbering;
    }

    public int getShowstandardinstruction() {
        return showstandardinstruction;
    }

    public void setShowstandardinstruction(int showstandardinstruction) {
        this.showstandardinstruction = showstandardinstruction;
    }

    public CorrectFeedback getCorrectfeedback() {
        return correctfeedback;
    }

    public void setCorrectfeedback(CorrectFeedback correctfeedback) {
        this.correctfeedback = correctfeedback;
    }

    public PartiallyCorrectFeedback getPartiallycorrectfeedback() {
        return partiallycorrectfeedback;
    }

    public void setPartiallycorrectfeedback(PartiallyCorrectFeedback partiallycorrectfeedback) {
        this.partiallycorrectfeedback = partiallycorrectfeedback;
    }

    public IncorrectFeedback getIncorrectfeedback() {
        return incorrectfeedback;
    }

    public void setIncorrectfeedback(IncorrectFeedback incorrectfeedback) {
        this.incorrectfeedback = incorrectfeedback;
    }

    public List<Answer> getAnswer() {
        return answer;
    }

    public void setAnswer(List<Answer> answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Question{" +
                "name=" + name +
                ", questionText=" + questiontext +
                ", generalFeedback=" + generalfeedback +
                ", defaultGrade=" + defaultgrade +
                ", penalty=" + penalty +
                ", hidden=" + hidden +
                ", idNumber='" + idnumber + '\'' +
                ", single=" + single +
                ", shuffleAnswers=" + shuffleanswers +
                ", answerNumbering='" + answernumbering + '\'' +
                ", showStandardInstruction=" + showstandardinstruction +
                ", correctFeedback=" + correctfeedback +
                ", partiallyCorrectFeedback=" + partiallycorrectfeedback +
                ", incorrectFeedback=" + incorrectfeedback +
                ", answers=" + answer +
                '}';
    }
}
