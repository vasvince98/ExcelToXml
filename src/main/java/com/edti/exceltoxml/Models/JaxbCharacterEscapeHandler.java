package com.edti.exceltoxml.Models;

import com.sun.xml.bind.marshaller.CharacterEscapeHandler;

import java.io.IOException;
import java.io.Writer;

public class JaxbCharacterEscapeHandler implements CharacterEscapeHandler {
    @Override
    public void escape(char[] buf, int start, int len, boolean b, Writer out) throws IOException {
        for (int i = start; i < start + len; i++) {
            char ch = buf[i];
            out.write(ch);
        }

    }
}
