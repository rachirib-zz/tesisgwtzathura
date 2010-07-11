package  com.clientesjson.server.util;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;

public class Utilities {

	public static boolean isNumeric(String word) {
		boolean ret = false;
		Pattern pat = Pattern.compile("[^0-9',.\\s]");
		Matcher mat = pat.matcher(word);

		if (!mat.find()) {
			ret = true;
		}

		return ret;
	}

	/**
	 * 
	 * @param word
	 * @return Expresion regular "(\\d){1,10}\\.(\\d){1,10}" (\\d)digito
	 *         {1,10}de 1 a 10 caracteres \\. punto
	 * 
	 */
	public static boolean isDecimal(String word) {
		boolean ret = false;
		Pattern pat = Pattern.compile("(\\d){1,8}\\.(\\d){0,2}");
		Matcher mat = pat.matcher(word);

		if (!mat.find()) {
			ret = true;
		}

		return ret;

		//		DoubleValidator doubleValidator = new DoubleValidator();
		//		return doubleValidator.isValid(word);

	}

	public static boolean checkNumberAndCheckWithPrecisionAndScale(
			String fieldValue, Integer precision, Integer scale) {

		boolean ret = false;

		if (fieldValue != null && precision != null && scale != null) {

			fieldValue = fieldValue.replace(".", "%");

			String[] spitedFieldValue = fieldValue.split("%");

			if (spitedFieldValue.length == 2 && precision != 0) {
				String precisionTmp = spitedFieldValue[0];
				String scaleTmp = spitedFieldValue[1];

				if (!isNumeric(precisionTmp)) {
					return false;
				}
				if (!isNumeric(scaleTmp)) {
					return false;
				}

				if ((precisionTmp.length() <= precision)
						&& (scaleTmp.length() <= scale)) {
					ret = true;
				}
			} else {
				if (spitedFieldValue.length == 1 && precision != 0
						&& scale == 0) {
					String precisionTmp = spitedFieldValue[0];

					if (!isNumeric(precisionTmp)) {
						return false;
					}

					if ((precisionTmp.length() <= precision)) {
						ret = true;
					}
				} else {
					return false;
				}
			}
		}

		return ret;
	}

	public static boolean checkWordAndCheckWithlength(String word, Integer length) {
	boolean ret = false;
		if (word.length()<=length) {
			ret = true;
		}

		return ret;
	}

	public static boolean isOnlyLetters(String word) {
		boolean ret = false;
				Pattern pat = Pattern.compile("[^A-Za-z0-9',.\\s]");
		Matcher mat = pat.matcher(word);
		if (!mat.find()) {
			ret = true;
		}
		return ret;
	}
	
	public static String formatDateWithoutTimeInAStringForBetweenWhere(
			Date fecha) {
		int year = fecha.getYear() + 1900;
		int month = fecha.getMonth() + 1;
		int day = fecha.getDate();

		String date = "" + year + "-" + month + "-" + day;

		return date;
	}
	
		public static boolean validationsList(List list) {
		if (list != null) {
			if (!list.isEmpty() && list.size() > 0) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
		
}