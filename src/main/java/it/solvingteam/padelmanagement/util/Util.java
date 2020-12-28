package it.solvingteam.padelmanagement.util;

import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

	public static boolean isInteger(String numString) {
		try {
			Integer.parseInt(numString);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
	
	public static boolean isLong(String numString) {
		try {
			Long.parseLong(numString);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
	
	public static boolean isDouble(String numString) {
		try {
			Double.parseDouble(numString);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}	
	
	public static boolean isDate(String dateString) {
		try {
		
			new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
			
		} catch (Exception e) {
			return false;
		}
		return true;
	}	

	public static boolean isEmptyOrNull(String value) {
		return value == null || value == "";
	}
	
//	public static boolean validateMail(String mail) {
//        Pattern p = Pattern.compile(".+@.+.[a-z]+", Pattern.CASE_INSENSITIVE);
//        Matcher m = p.matcher(mail);
//        boolean matchFound = m.matches();
//        return matchFound;
//    }

	public void Util(){
		
	}
	
}
