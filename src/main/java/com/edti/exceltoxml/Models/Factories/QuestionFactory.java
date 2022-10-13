package com.edti.exceltoxml.Models.Factories;

import com.edti.exceltoxml.Models.Enums.QType;
import com.edti.exceltoxml.Models.PropertyClasses.FieldProperties;
import com.edti.exceltoxml.Models.QuestionTypes.Matching;
import com.edti.exceltoxml.Models.QuestionTypes.Multichoice;
import com.edti.exceltoxml.Models.QuestionTypes.Question;
import com.edti.exceltoxml.Models.QuestionTypes.Truefalse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class QuestionFactory {

    private static FieldProperties fieldProperties;

    @Autowired
    public QuestionFactory(FieldProperties fieldProperties) {
        QuestionFactory.fieldProperties = fieldProperties;
    }

    //    public void setFieldProperties(FieldProperties fieldProperties) {
//        QuestionFactory.fieldProperties = fieldProperties;
//    }

    public static Question getQuestion(QType type, HashMap<String, String> data){

        if (type == null){
            return null;
        }

        if (type.equals(QType.multichoice)) {
            return new Multichoice(data, fieldProperties);
        } else if(type.equals(QType.truefalse)){
            return new Truefalse(data, fieldProperties);
        } else if (type.equals(QType.matching)) {
            return new Matching(data, fieldProperties);
        }

        return null;
    }
}