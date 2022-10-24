package com.edti.exceltoxml.Models.QuestionTypes;

import com.edti.exceltoxml.Models.AuxClasses.Generalfeedback;
import com.edti.exceltoxml.Models.AuxClasses.Name;
import com.edti.exceltoxml.Models.AuxClasses.Questiontext;
import com.edti.exceltoxml.Models.PropertyClasses.FieldProperties;
import com.edti.exceltoxml.Services.Interfaces.IImageService;
import com.edti.exceltoxml.Services.Interfaces.IStateService;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlTransient;
import java.io.IOException;
import java.util.HashMap;


public class Truefalse extends RealQuestion {

    //region Fields

    @XmlTransient
    private final FieldProperties fieldProperties;
    @XmlTransient
    private final IStateService stateService;
    @XmlTransient
    private final IImageService imageService;

    //endregion

    //region Constructor


    public Truefalse(HashMap<String, String> data,
                     FieldProperties fieldProperties,
                     IStateService stateService,
                     IImageService imageService) throws IOException {
        this.fieldProperties = fieldProperties;
        this.stateService = stateService;
        this.imageService = imageService;
        if (stateService.getState()) {
            initImageInstance(data);
        } else {
            initSimpleInstance(data);
        }

    }
    //endregion

    @Override
    public String getXmlForm() throws JAXBException {
        return this.generateXmlForm(Truefalse.class, this.getClass().getSuperclass().getSuperclass().getSimpleName().toLowerCase(), this);
    }

    @Override
    protected void initSimpleInstance(HashMap<String, String> data) {

        initBaseData(data);

        //Question text
        Questiontext qt = new Questiontext();
        qt.setFormat("html");
        qt.setSimpleText(data.get("Kérdés szövege"));
        this.setQuestiontext(qt);

    }

    @Override
    protected void initImageInstance(HashMap<String, String> data) throws IOException {
        initBaseData(data);

        //Question text
        Questiontext qt = new Questiontext();
        qt.setFormat("html");
        qt.setImageText(imageService.transformStringToBase64(data.get("Kérdés szövege")));
        this.setQuestiontext(qt);
    }

    @Override
    protected void initBaseData(HashMap<String, String> data) {
        this.setType("truefalse");

        //Name
        Name n = new Name();
        n.setSimpleText(data.get("Kérdés neve"));
        this.setName(n);

        //General feedback
        Generalfeedback gf = new Generalfeedback();
        gf.setFormat("html");
        gf.setSimpleText(data.get("Általános visszajelzés"));
        this.setGeneralfeedback(gf);

        //Default grade
        this.setDefaultgrade(data.get("Pont"));
        //Penalty
        this.setPenalty(data.get("Rossz válasz esetén mínusz pont"));
        //Hidden
        this.setHidden(data.get("Elrejtve?"));
        //Idnumber
        this.setIdnumber(data.get("id"));
    }

    @Override
    public String toString() {
        return "Truefalse{" +
                "name=" + name +
                ", questiontext=" + questiontext +
                ", generalfeedback=" + generalfeedback +
                ", idNumber=" + getIdnumber() +
                '}';
    }
}