package com.edti.exceltoxml.Models.QuestionTypes;

import com.edti.exceltoxml.Models.AuxClasses.*;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class Matching extends RealQuestion {

    //region Fields
    private String shuffleanswers;
    Correctfeedback correctfeedback;
    Partiallycorrectfeedback partiallycorrectfeedback;
    Incorrectfeedback incorrectfeedback;
    private String shownumcorrect;

    //endregion

    //region Constructors

    public Matching() {}

    public Matching(HashMap<String, String> data) {
        initInstance(data);
    }

    //endregion

    //region Getters and Setters

    public String getShuffleanswers() {
        return shuffleanswers;
    }

    public void setShuffleanswers(String shuffleanswers) {
        this.shuffleanswers = shuffleanswers;
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

    @Override
    public String getXmlForm() throws JAXBException, FileNotFoundException {
        return this.generateXmlForm(Matching.class, this.getClass().getSuperclass().getSuperclass().getSimpleName().toLowerCase(), this);
    }

    private void initInstance(HashMap<String, String> data) {
        this.setType("matching");

        Name n = new Name();
        //question name
        n.setText(data.get("Kérdés neve"));
        this.setName(n);

        Questiontext qt = new Questiontext();
        qt.setFormat("html");
        //questiontext
        qt.setText(data.get("Kérdés szövege"));
        this.setQuestiontext(qt);

        Generalfeedback gf = new Generalfeedback();
        gf.setFormat("html");
        //general feedback
        gf.setText(data.get("Általános visszajelzés"));
        this.setGeneralfeedback(gf);

        //Pont
        this.setDefaultgrade(data.get("Pont"));
        //Penalty
        this.setPenalty(data.get("Rossz válasz esetén mínusz pont"));
        //Hidden
        this.setHidden(data.get("Elrejtve?"));


    }

    @Override
    public String toString() {
        return "Matching{" +
                "shuffleanswers='" + shuffleanswers + '\'' +
                ", correctfeedback=" + correctfeedback +
                ", partiallycorrectfeedback=" + partiallycorrectfeedback +
                ", incorrectfeedback=" + incorrectfeedback +
                ", shownumcorrect='" + shownumcorrect + '\'' +
                ", name=" + name +
                ", questiontext=" + questiontext +
                ", generalfeedback=" + generalfeedback +
                '}';
    }
}
