package com.projeto.api.service.Format;

import javax.swing.text.MaskFormatter;
import java.text.ParseException;

public class FormatString {

    public String format(String value, String pattern) {
        MaskFormatter mf;
        try {
            mf = new MaskFormatter(pattern);
            mf.setValueContainsLiteralCharacters(false);
            return mf.valueToString(value);
        } catch (ParseException ex) {
            return value;
        }
    }

    public String remove(String value, String pattern) {
        MaskFormatter mf;
        try {
            mf = new MaskFormatter(pattern);
            mf.setValueContainsLiteralCharacters(true);
            return mf.valueToString(value);
        } catch (ParseException ex) {
            return value;
        }
    }

}