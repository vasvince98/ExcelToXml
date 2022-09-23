package com.edti.exceltoxml.Models.Q.QuestionTypes;

import com.edti.exceltoxml.Models.Q.AuxClasses.*;
import org.apache.poi.ss.formula.functions.Na;

import javax.xml.bind.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

//@XmlRootElement
public class Multichoice extends RealQuestion {
    //region Fields
    private String single;
    private String shuffleanswers;
    private String answernumbering;
    private String showstandardinstruction;
    Correctfeedback correctfeedback;
    Partiallycorrectfeedback partiallycorrectfeedback;
    Incorrectfeedback incorrectfeedback;
    private String shownumcorrect;

    public Multichoice(HashMap<String, String> data) {
        initInstance(data);
    }
    //endregion

    //region Getters && Setters
    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Questiontext getQuestiontext() {
        return questiontext;
    }

    public void setQuestiontext(Questiontext questiontext) {
        this.questiontext = questiontext;
    }

    public Generalfeedback getGeneralfeedback() {
        return generalfeedback;
    }

    public void setGeneralfeedback(Generalfeedback generalfeedback) {
        this.generalfeedback = generalfeedback;
    }

    public String getSingle() {
        return single;
    }

    public void setSingle(String single) {
        this.single = single;
    }

    public String getShuffleanswers() {
        return shuffleanswers;
    }

    public void setShuffleanswers(String shuffleanswers) {
        this.shuffleanswers = shuffleanswers;
    }

    public String getAnswernumbering() {
        return answernumbering;
    }

    public void setAnswernumbering(String answernumbering) {
        this.answernumbering = answernumbering;
    }

    public String getShowstandardinstruction() {
        return showstandardinstruction;
    }

    public void setShowstandardinstruction(String showstandardinstruction) {
        this.showstandardinstruction = showstandardinstruction;
    }

    public Correctfeedback getCorrectfeedback() {
        return correctfeedback;
    }

    public void setCorrectfeedback(Correctfeedback correctfeedback) {
        this.correctfeedback = correctfeedback;
    }

    public Partiallycorrectfeedback getPartiallycorrectfeedback() {
        return partiallycorrectfeedback;
    }

    public void setPartiallycorrectfeedback(Partiallycorrectfeedback partiallycorrectfeedback) {
        this.partiallycorrectfeedback = partiallycorrectfeedback;
    }

    public Incorrectfeedback getIncorrectfeedback() {
        return incorrectfeedback;
    }

    public void setIncorrectfeedback(Incorrectfeedback incorrectfeedback) {
        this.incorrectfeedback = incorrectfeedback;
    }

    public String getShownumcorrect() {
        return shownumcorrect;
    }

    public void setShownumcorrect(String shownumcorrect) {
        this.shownumcorrect = shownumcorrect;
    }

    //endregion

    public String getXmlForm() throws JAXBException, FileNotFoundException {
        return this.generateXmlForm(Multichoice.class, this.getClass().getSuperclass().getSuperclass().getSimpleName().toLowerCase(), this);
    }

    private void initInstance(HashMap<String, String> data){
        this.setType("multichoice");
        Name n = new Name();
        n.setText(data.get("name"));
        this.setName(n);

        Questiontext qt = new Questiontext();
        qt.setFormat("html");
        qt.setText(data.get("questiontext"));
        this.setQuestiontext(qt);

        Generalfeedback gf = new Generalfeedback();
        gf.setFormat("html");
        gf.setText(data.get("generalfeedback"));
        this.setGeneralfeedback(gf);

        this.setDefaultgrade(data.get("defaultgrade"));
        this.setPenalty(data.get("penalty"));
        this.setHidden(data.get("hidden"));
        this.setIdnumber("");
        this.setSingle(data.get("single"));
        this.setShuffleanswers(data.get("shuffleanswers"));
        this.setAnswernumbering(data.get("answernumbering"));
        this.setShowstandardinstruction(data.get("showstandardinstruction"));

        Correctfeedback cf = new Correctfeedback();
        cf.setFormat("html");
        cf.setText(data.get("correctfeedback"));
        this.setCorrectfeedback(cf);

        Partiallycorrectfeedback pf = new Partiallycorrectfeedback();
        pf.setFormat("html");
        pf.setText(data.get("partiallycorrectfeedback"));
        this.setPartiallycorrectfeedback(pf);

        Incorrectfeedback inf = new Incorrectfeedback();
        inf.setFormat("html");
        inf.setText(data.get("incorrectfeedback"));
        this.setIncorrectfeedback(inf);
        this.setShownumcorrect("");
    }

}