package com.edti.exceltoxml.Models.Factories;

import com.edti.exceltoxml.Models.Enums.QType;
import com.edti.exceltoxml.Models.PropertyClasses.FieldProperties;
import com.edti.exceltoxml.Models.QuestionTypes.*;
import com.edti.exceltoxml.Services.Interfaces.IImageService;
import com.edti.exceltoxml.Services.Interfaces.IStateService;
import com.edti.exceltoxml.Services.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;

@Component
public class QuestionFactory {

    private static FieldProperties fieldProperties;
    private static IStateService stateService;
    private static IImageService imageService;

    @Autowired
    public QuestionFactory(FieldProperties fieldProperties,
                           IStateService stateService,
                           IImageService imageService) {
        QuestionFactory.fieldProperties = fieldProperties;
        QuestionFactory.stateService = stateService;
        QuestionFactory.imageService = imageService;
    }


    public static Question getQuestion(QType type, HashMap<String, String> data) throws IOException {

        if (type == null){
            return null;
        }

        if (type.equals(QType.multichoice)) {
            return new Multichoice(data, fieldProperties, stateService, imageService);
        } else if(type.equals(QType.truefalse)){
            return new Truefalse(data, fieldProperties, stateService, imageService);
        } else if (type.equals(QType.matching)) {
            return new Matching(data, fieldProperties, stateService, imageService);
        } else if(type.equals(QType.ddwtos)) {
            return new Ddwtos(data, fieldProperties, stateService, imageService);
        }

        return null;
    }
}