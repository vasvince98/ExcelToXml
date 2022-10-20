package com.edti.exceltoxml.Models.QuestionTypes;

import com.edti.exceltoxml.Models.PropertyClasses.FieldProperties;
import com.edti.exceltoxml.Services.Interfaces.IImageService;
import com.edti.exceltoxml.Services.Interfaces.IStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
import java.io.StringWriter;

@XmlRootElement()
@XmlAccessorType(XmlAccessType.FIELD)
@Component
public abstract class Question {


    //region Fields
    @XmlAttribute(name="type")
    private String type;

    private String idnumber;

    //endregion


    //region Getters and Setters

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

    //endregion

    /**
     *
     * @return Return a .xml formatted string.
     * @throws JAXBException is thrown, when the marshalling fails.
     */
    public abstract String getXmlForm() throws JAXBException;

    protected String generateXmlForm(Class c, String qname, Object object) throws JAXBException {
        StringWriter sw = new StringWriter();
        JAXBContext ctx = JAXBContext.newInstance(c);
        Marshaller m = ctx.createMarshaller();
        m.setProperty(Marshaller.JAXB_FRAGMENT, true);
        m.marshal(new JAXBElement<>(new QName(qname), c, object), sw);
        return sw.toString();
    }

    @Override
    public String toString() {
        return "Question{" +
                "type='" + type + '\'' +
                ", idnumber='" + idnumber + '\'' +
                '}';
    }
}