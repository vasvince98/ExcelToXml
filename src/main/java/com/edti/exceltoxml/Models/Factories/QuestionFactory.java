package com.edti.exceltoxml.Models.Factories;

import com.edti.exceltoxml.Models.Enums.QType;
import com.edti.exceltoxml.Models.QuestionTypes.Multichoice;
import com.edti.exceltoxml.Models.QuestionTypes.Question;
import com.edti.exceltoxml.Models.QuestionTypes.Truefalse;

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
            return new Truefalse(data);
        }

        return null;
    }
}