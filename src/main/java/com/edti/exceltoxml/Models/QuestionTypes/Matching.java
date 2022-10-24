package com.edti.exceltoxml.Models.QuestionTypes;

import com.edti.exceltoxml.Models.AuxClasses.*;
import com.edti.exceltoxml.Models.PropertyClasses.FieldProperties;
import com.edti.exceltoxml.Services.Interfaces.IImageService;
import com.edti.exceltoxml.Services.Interfaces.IStateService;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlTransient;
import java.io.IOException;
import java.util.HashMap;

public class Matching extends RealQuestion {

    //region Fields
    private String shuffleanswers;
    Correctfeedback correctfeedback;
    Partiallycorrectfeedback partiallycorrectfeedback;
    Incorrectfeedback incorrectfeedback;
    private String shownumcorrect;

    @XmlTransient
    private final FieldProperties fieldProperties;
    @XmlTransient
    private final IStateService stateService;
    @XmlTransient
    private final IImageService imageService;

    //endregion


    //region Constructors


    public Matching(HashMap<String, String> data,
                    FieldProperties fieldProperties,
                    IStateService stateService,
                    IImageService imageService) throws IOException {
        this.fieldProperties = fieldProperties;
        this.stateService = stateService;
        this.imageService = imageService;
        if (stateService.getState().equals("on")) {
            initImageInstance(data);
        } else {
            initSimpleInstance(data);
        }
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
    public String getXmlForm() throws JAXBException {
        return this.generateXmlForm(Matching.class, this.getClass().getSuperclass().getSuperclass().getSimpleName().toLowerCase(), this);
    }

    @Override
    protected void initSimpleInstance(HashMap<String, String> data) {

        initBaseData(data);

        Questiontext qt = new Questiontext();
        qt.setFormat("html");
        //questiontext
        qt.setImageText(data.get(fieldProperties.getQuestionText()));
        this.setQuestiontext(qt);
    }

    @Override
    protected void initImageInstance(HashMap<String, String> data) throws IOException {
        initBaseData(data);

        Questiontext qt = new Questiontext();
        qt.setFormat("html");
        //questiontext
        qt.setImageText(imageService.transformStringToBase64(data.get(fieldProperties.getQuestionText())));
        this.setQuestiontext(qt);

    }

    @Override
    protected void initBaseData(HashMap<String, String> data) {
        this.setType("matching");

        Name n = new Name();
        //question name
        n.setImageText(data.get(fieldProperties.getQuestionName()));
        this.setName(n);

        Generalfeedback gf = new Generalfeedback();
        gf.setFormat("html");
        //general feedback
        gf.setImageText(data.get(fieldProperties.getGeneralFeedback()));
        this.setGeneralfeedback(gf);

        //Pont
        this.setDefaultgrade(data.get(fieldProperties.getDefaultGrade()));
        //Penalty
        this.setPenalty(data.get(fieldProperties.getPenalty()));
        //Hidden
        this.setHidden(data.get(fieldProperties.getHidden()));
        //IdNumber
        this.setIdnumber(data.get(fieldProperties.getIdNumber()));
        //Shuffle answers
        this.setShuffleanswers(data.get(fieldProperties.getShuffleAnswers()).toLowerCase());

        Correctfeedback cf = new Correctfeedback();
        cf.setFormat("html");
        //Correct feedback
        cf.setImageText(data.get(fieldProperties.getCorrectFeedback()));
        this.setCorrectfeedback(cf);

        Partiallycorrectfeedback pf = new Partiallycorrectfeedback();
        pf.setFormat("html");
        //Partially correct feedback
        pf.setImageText(data.get(fieldProperties.getPartiallyCorrectFeedback()));
        this.setPartiallycorrectfeedback(pf);

        Incorrectfeedback inf = new Incorrectfeedback();
        inf.setFormat("html");
        //Incorrect feedback
        inf.setImageText(data.get(fieldProperties.getIncorrectFeedback()));
        this.setIncorrectfeedback(inf);
        this.setShownumcorrect(fieldProperties.getShowNumCorrect());
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
