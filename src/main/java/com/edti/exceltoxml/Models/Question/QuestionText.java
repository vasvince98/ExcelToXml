package com.edti.exceltoxml.Models.Question;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlValue;

public class QuestionText extends Text {

    @XmlAttribute
    private String format;

    private File file;

    @XmlTransient
    private boolean isPicture;

    public QuestionText(String format) {
        this.format = format;
    }

    public QuestionText(String format, File file) {
        super("<![CDATA[<p dir=\"ltr\" style=\"text-align: left;\"><img src=\"@@PLUGINFILE@@/imageName\" alt=\"\" role=\"presentation\" class=\"img-fluid\"><br></p>]]>");
        this.format = format;
        this.file = file;
    }

    public QuestionText(String format, String text) {
        super(text);
        this.format = format;
    }


    public void setFile(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public void setFormat(String format) {
        this.format = format;
    }

}
