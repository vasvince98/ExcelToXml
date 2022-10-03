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

    //region Constructors


    public Multichoice() {}

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
        this.setIdnumber("");
        //Single
        this.setSingle(data.get("Egy válasz a jó?"));
        //Shuffleanswers
        this.setShuffleanswers(data.get("Keverje a válaszokat?"));
        //Answernumbering
        //todo: get from excel, but I think this should be default like this:
        this.setAnswernumbering("abc");
        this.setShowstandardinstruction(data.get("showstandardinstruction"));

        Correctfeedback cf = new Correctfeedback();
        cf.setFormat("html");
        //Correct feedback
        cf.setText(data.get("Visszajelzés helyes válasz esetén"));
        this.setCorrectfeedback(cf);

        Partiallycorrectfeedback pf = new Partiallycorrectfeedback();
        pf.setFormat("html");
        //Partially correct feedback
        pf.setText(data.get("Visszajelzés részben helyes válasz esetén"));
        this.setPartiallycorrectfeedback(pf);

        Incorrectfeedback inf = new Incorrectfeedback();
        inf.setFormat("html");
        //Incorrect feedback
        inf.setText(data.get("Visszajelzés rossz válasz esetén"));
        this.setIncorrectfeedback(inf);
        this.setShownumcorrect("");
    }

    @Override
    public String toString() {
        return "Multichoice{" +
                "single='" + single + '\'' +
                ", shuffleanswers='" + shuffleanswers + '\'' +
                ", answernumbering='" + answernumbering + '\'' +
                ", showstandardinstruction='" + showstandardinstruction + '\'' +
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