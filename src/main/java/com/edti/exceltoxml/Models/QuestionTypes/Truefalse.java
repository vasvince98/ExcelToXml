package com.edti.exceltoxml.Models.QuestionTypes;

import com.edti.exceltoxml.Models.AuxClasses.Generalfeedback;
import com.edti.exceltoxml.Models.AuxClasses.Name;
import com.edti.exceltoxml.Models.AuxClasses.Questiontext;
import com.edti.exceltoxml.Models.PropertyClasses.FieldProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.HashMap;


public class Truefalse extends RealQuestion {



    //region Constructor


    public Truefalse(HashMap<String, String> data, FieldProperties fieldProperties) {
        initInstance(data);
    }
    //endregion

    @Override
    public String getXmlForm() throws JAXBException, FileNotFoundException {
        return this.generateXmlForm(Truefalse.class, this.getClass().getSuperclass().getSuperclass().getSimpleName().toLowerCase(), this);
    }

    private void initInstance(HashMap<String, String> data) {
        this.setType("truefalse");

        //Name
        Name n = new Name();
        n.setText(data.get("Kérdés neve"));
        this.setName(n);

        //Question text
        Questiontext qt = new Questiontext();
        qt.setFormat("html");
        qt.setText(data.get("Kérdés szövege"));
        this.setQuestiontext(qt);

        //General feedback
        Generalfeedback gf = new Generalfeedback();
        gf.setFormat("html");
        gf.setText(data.get("Általános visszajelzés"));
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