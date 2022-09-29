package com.edti.exceltoxml.Services.QuestionObjectProviders;

import com.edti.exceltoxml.Models.Q.AuxClasses.Answer;
import com.edti.exceltoxml.Models.Q.AuxClasses.Category;
import com.edti.exceltoxml.Models.Q.AuxClasses.Name;
import com.edti.exceltoxml.Models.Q.AuxClasses.Questiontext;
import com.edti.exceltoxml.Models.Q.Enums.QType;
import com.edti.exceltoxml.Models.Q.Factories.QuestionFactory;
import com.edti.exceltoxml.Models.Q.QuestionTypes.Cat;
import com.edti.exceltoxml.Models.Q.QuestionTypes.Multichoice;
import com.edti.exceltoxml.Models.Q.QuestionTypes.RealQuestion;
import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MultichoiceQuestionProvider extends QuestionObjectProvider {

    //1. beállítom az ősosztály sheet mezejét
    //2. beállítom a multichoice kérdéstípusra jellemző mezők számát
    //3. A sheetből kiszedem egy gyűjteménybe a kérdéseket kategóriánként
    //4. Bejárom az összes kategóriát, és az azon belül lévő mező párokból Multichoice objektumokat készítek, amiket a visszatérő listához adok
    @Override
    public Map<Cat, List<RealQuestion>> objectListFromSheet(Sheet sheet) {
        HashMap<Cat, List<RealQuestion>> resultMap = new HashMap<>();
        this.sheet = sheet;
        setNumberOfFields(11);
        resultMap
        return null;
    }

    @Override
    protected HashMap<String, String> getQuestionMap() {
        return null;
    }

    @Override
    protected List<Answer> createAnswerList() {
        return null;
    }

    private Multichoice getQuestion() {
        HashMap<String, String> questionBaseDataMap = createQuestionBaseDataMap();
        return (Multichoice) QuestionFactory.getQuestion(QType.multichoice, questionBaseDataMap);
    }

    private HashMap<String, String> createQuestionBaseDataMap() {
        this.createQuestionMap().get("1.1");
    }
}
