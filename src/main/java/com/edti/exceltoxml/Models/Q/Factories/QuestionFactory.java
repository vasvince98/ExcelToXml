package com.edti.exceltoxml.Models.Q.Factories;

import com.edti.exceltoxml.Models.Q.Enums.QType;
import com.edti.exceltoxml.Models.Q.QuestionTypes.IQuestion;
import com.edti.exceltoxml.Models.Q.QuestionTypes.Multichoice;
import com.edti.exceltoxml.Models.Q.QuestionTypes.Question;
import com.edti.exceltoxml.Models.Q.QuestionTypes.Truefalse;

import java.util.HashMap;

public class QuestionFactory {

    public static Question getQuestion(QType type, HashMap<String, String> data){

        if (type == null){
            return null;
        }

        if (type.equals(QType.multichoice)){
            return new Multichoice(data);
        }
        else if(type.equals(QType.truefalse)){
            return null;
        }

        return null;
    }
}