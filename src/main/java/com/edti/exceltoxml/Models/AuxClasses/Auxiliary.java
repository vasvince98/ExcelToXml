package com.edti.exceltoxml.Models.AuxClasses;

import com.edti.exceltoxml.Models.AdapterCDATA;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement()
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Auxiliary {

//    private String text;
    public abstract String getText();
    public abstract void setText(String text);

}
