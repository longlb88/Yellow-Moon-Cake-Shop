/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longlb.utils;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author Long Le
 */
public class DateHandler {
    public static boolean isValid(String dateStr) {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        try {
            sdf.parse(dateStr);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
    
    public static Date createDateObject(String dateStr) throws NumberFormatException{
	String[] splitStrings = dateStr.split("-");
	Date date;
	date = new Date(Integer.parseInt(splitStrings[0]) - 1900, 
			Integer.parseInt(splitStrings[1]) - 1, 
			Integer.parseInt(splitStrings[2]));
	return date;
    }
}
