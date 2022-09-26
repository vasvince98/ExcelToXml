package com.edti.exceltoxml.Models.Q.QuestionTypes;

import com.edti.exceltoxml.Models.Q.AuxClasses.Answer;
import com.edti.exceltoxml.Models.Q.AuxClasses.Generalfeedback;
import com.edti.exceltoxml.Models.Q.AuxClasses.Name;
import com.edti.exceltoxml.Models.Q.AuxClasses.Questiontext;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class Truefalse extends RealQuestion{


    @Override
    public String getXmlForm() throws JAXBException, FileNotFoundException {
        return this.generateXmlForm(Truefalse.class, this.getClass().getSuperclass().getSuperclass().getSimpleName().toLowerCase(), this);
    }

    private void initInstance(HashMap<String, String> data) {
        this.setType("truefalse");
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
    }
}