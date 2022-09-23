package com.edti.exceltoxml;


import com.edti.exceltoxml.Models.Q.AuxClasses.*;
import com.edti.exceltoxml.Models.Q.Enums.QType;
import com.edti.exceltoxml.Models.Q.Factories.QuestionFactory;
import com.edti.exceltoxml.Models.Q.QuestionTypes.Cat;
import com.edti.exceltoxml.Models.Q.QuestionTypes.Multichoice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;


@SpringBootApplication
public class Main {
    public static void main(String[] args) throws JAXBException, FileNotFoundException {
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //Feeding our Factory

        Multichoice m = (Multichoice) QuestionFactory.getQuestion(QType.multichoice, SimulateDataLoadingProcess());
        m.setAnswer(SimulateAnswerLoadingProcess());
        m.getXmlForm();

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        SpringApplication.run(Main.class, args);
    }

    // A hashmap-et kell feltölteni az excel adataival.
    private static HashMap<String, String> SimulateDataLoadingProcess(){
        HashMap<String, String> tmp = new HashMap<>();
        tmp.put("name", "kérdés neve");
        tmp.put("questiontext", "a kérdés szövege");
        tmp.put("generalfeedback", "ügyes vagy!");
        tmp.put("defaultgrade", "1.0000000");
        tmp.put("penalty", "0.3333333");
        tmp.put("hidden", "0");
        tmp.put("idnumber", "");
        tmp.put("single", "false");
        tmp.put("shuffleanswers", "true");
        tmp.put("answernumbering", "abc");
        tmp.put("showstandardinstruction", "0");
        tmp.put("correctfeedback", "Válasza helyes.");
        tmp.put("partiallycorrectfeedback", "Válasza részben helyes.");
        tmp.put("incorrectfeedback", "Válasza helytelen.");
        tmp.put("shownumcorrect", "");
        return tmp;
    }

    //A válaszokat nyilván szintén az excel adataival kellene feltölteni. NEM EGYESÉVEL -> Metódusokba szervezni mindent!!!
    private static ArrayList<Answer> SimulateAnswerLoadingProcess(){

        ArrayList<Answer> answers = new ArrayList<>();

        Answer a1 = new Answer();
        a1.setText("Első válasz");
        a1.setFormat("html");
        a1.setFraction("50");

        Feedback f1 = new Feedback();
        f1.setFormat("html");
        f1.setText("");
        a1.setFeedback(f1);

        answers.add(a1);

        Answer a2 = new Answer();
        a2.setText("Második válasz");
        a2.setFormat("html");
        a2.setFraction("50");
        a2.setFeedback(new Feedback());

        Feedback f2 = new Feedback();
        f2.setFormat("html");
        f2.setText("");
        a1.setFeedback(f2);

        answers.add(a2);

        Answer a3 = new Answer();
        a3.setText("Harmadik válasz");
        a3.setFormat("html");
        a3.setFraction("0");
        a3.setFeedback(new Feedback());

        Feedback f3 = new Feedback();
        f3.setFormat("html");
        f3.setText("");
        a1.setFeedback(f3);

        answers.add(a3);


        Answer a4 = new Answer();
        a4.setText("Negyedik válasz");
        a4.setFormat("html");
        a4.setFraction("0");
        a4.setFeedback(new Feedback());

        Feedback f4 = new Feedback();
        f4.setFormat("html");
        f4.setText("");
        a1.setFeedback(f4);

        answers.add(a4);

        return answers;
    }

}

