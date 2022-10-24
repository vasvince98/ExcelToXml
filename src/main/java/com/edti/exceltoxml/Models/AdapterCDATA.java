package com.edti.exceltoxml.Models;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class AdapterCDATA extends XmlAdapter<String, String> {

    @Override
    public String marshal(String arg0) throws Exception {
        return "<![CDATA[<img src=\"data:image/png;base64," + arg0 + "\"/>]]>";
    }
    @Override
    public String unmarshal(String arg0) throws Exception {
        return arg0;
    }

}
