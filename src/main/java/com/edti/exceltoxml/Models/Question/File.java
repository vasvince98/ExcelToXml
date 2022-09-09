package com.edti.exceltoxml.Models.Question;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

public class File {

    @XmlAttribute
    private String encoding;
    @XmlAttribute
    private String name;
    @XmlAttribute
    private String path;

    @XmlValue
    private String texty;

    public File() {}

    public File(String encoding, String texty) {
        this.encoding = encoding;
        this.name = "imageName";
        this.texty = texty;
        this.path = "/";
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public void setTexty(String texty) {
        this.texty = texty;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
