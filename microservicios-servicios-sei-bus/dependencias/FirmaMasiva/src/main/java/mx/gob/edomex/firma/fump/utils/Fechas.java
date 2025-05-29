package mx.gob.edomex.firma.fump.utils;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author draco
 */
public class Fechas {

    public static XMLGregorianCalendar getXMLCalendar() throws Exception {
        Calendar sDate = Calendar.getInstance();
        DatatypeFactory dtf = DatatypeFactory.newInstance();
        XMLGregorianCalendar calendar;

        // Dates (CCYY-MM-DD)
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("d/M/yyyy");

        calendar = dtf.newXMLGregorianCalendar();
        calendar.setYear(sDate.get(Calendar.YEAR));
        calendar.setDay(sDate.get(Calendar.DAY_OF_MONTH));
        calendar.setMonth(sDate.get(Calendar.MONTH) + 1);
        calendar.setHour(0);
        calendar.setMinute(0);
        calendar.setSecond(0);
        calendar.setMillisecond(0);


        return calendar;
    }
}
