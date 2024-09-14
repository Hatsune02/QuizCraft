package com.navi.quizcraftweb.backend.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ModelUtils {
    public static Date stringToDate(String date) {
        Date d;
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd");
        try {
            d = format.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return d;
    }

    public static String dateToString(Date date) {
        if (date == null) {
            return "null";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
}
