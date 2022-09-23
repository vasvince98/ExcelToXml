package com.edti.exceltoxml.Models.Q.QuestionTypes;

import com.edti.exceltoxml.Models.Q.AuxClasses.Generalfeedback;
import com.edti.exceltoxml.Models.Q.AuxClasses.Name;
import com.edti.exceltoxml.Models.Q.AuxClasses.Questiontext;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.namespace.QName;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.StringWriter;

@XmlRootElement()
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Question {

    @XmlAttribute(name="type")
    private String type;

    private String idnumber;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    public abstract String getXmlForm() throws JAXBException, FileNotFoundException;

    protected String generateXmlForm(Class c, String qname, Object object) throws JAXBException, FileNotFoundException {
        StringWriter sw = new StringWriter();
        JAXBContext ctx = JAXBContext.newInstance(c);
        Marshaller m = ctx.createMarshaller();
        m.setProperty(Marshaller.JAXB_FRAGMENT, true);
        m.marshal(new JAXBElement<>(new QName(qname), c, object), new FileOutputStream("example.xml"));
        return sw.toString();
    }
}