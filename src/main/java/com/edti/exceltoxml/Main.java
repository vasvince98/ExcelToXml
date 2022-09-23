package com.edti.exceltoxml;


import com.edti.exceltoxml.Models.Q.AuxClasses.*;
import com.edti.exceltoxml.Models.Q.QuestionTypes.Cat;
import com.edti.exceltoxml.Models.Q.QuestionTypes.Multichoice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;


@SpringBootApplication
public class Main {

    public static void main(String[] args) throws JAXBException, FileNotFoundException {

        Multichoice m = new Multichoice();
        m.setType("multichoice");

        Name n = new Name();
        n.setText("kérdés");
        m.setName(n);

        Questiontext qt = new Questiontext();
        qt.setFormat("html");
        qt.setText("<![CDATA[<p dir=\"ltr\" style=\"text-align: left;\">kérdés szövege</p>]]>");
        m.setQuestiontext(qt);

        Generalfeedback gf = new Generalfeedback();
        gf.setFormat("html");
        gf.setText("");
        m.setGeneralfeedback(gf);

        m.setDefaultgrade("1.0000000");
        m.setPenalty("0.3333333");
        m.setHidden("0");
        m.setIdnumber("");
        m.setSingle("false");
        m.setShuffleanswers("true");
        m.setAnswernumbering("abc");
        m.setShowstandardinstruction("0");

        Correctfeedback cf = new Correctfeedback();
        cf.setFormat("html");
        cf.setText("Válasza helyes.");
        m.setCorrectfeedback(cf);

        Partiallycorrectfeedback pf = new Partiallycorrectfeedback();
        pf.setFormat("html");
        pf.setText("Válasza részben helyes.");
        m.setPartiallycorrectfeedback(pf);

        Incorrectfeedback inf = new Incorrectfeedback();
        inf.setFormat("html");
        inf.setText("Válasza helytelen.");
        m.setIncorrectfeedback(inf);

        m.setShownumcorrect("");



        //m.getXmlForm();

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        Cat cat = new Cat();
        cat.setType("category");

        Category category = new Category();
        category.setText("$course$/top/teszt5 alapbeállítása");

        cat.setCategory(category);

        Info i = new Info();
        i.setFormat("moodle_auto_format");
        i.setText("'teszt5' környezet megosztott kérdéseinek alapkategóriája");
        cat.setInfo(i);
        cat.setIdnumber("");

        cat.getXmlForm();

        SpringApplication.run(Main.class, args);
    }

}

