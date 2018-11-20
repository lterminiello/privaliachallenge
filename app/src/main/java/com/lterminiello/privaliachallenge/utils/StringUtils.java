package com.lterminiello.privaliachallenge.utils;

public abstract class  StringUtils {
	
	public final static String ASTERISK = "*";
	public final static String EMPTY = "";
	public final static String ELLIPSIS = "...";
	public final static String COMMA = ",";
	public final static String COMMA_WITH_SPACE = ", ";
	public final static String SPACE = " ";
	public final static String DASH = "-";
	public final static String SLASH = "/";
	public final static String DOT = ".";
	public final static String COLON = ":";
	public final static String NEW_LINE = "\n";
	public final static String UNDERSCORE = "_";
	public final static String BANG = "!";
	public final static String PIPE = "|";
	public final static String CREDIT_CARD_MASK_CHAR = "X";
	public final static String PLUS = "+";
	public final static String BULLET_POINT = "•";
	public final static String QUOTATION_MARK = "\"";

	private final static String ALPHANUMERIC_PATTERN = "([^\\w\\s])*";
	

	public static Boolean isEmpty(String text) {
		return text != null ? text.length() == 0 : true;
	}
	
	public static Boolean isNotEmpty(String text) {
		return !isEmpty(text);
	}
	

	public static boolean isBlank(String str) {
		int strLen;
		if ((str == null) || ((strLen = str.length()) == 0)) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((!Character.isWhitespace(str.charAt(i)))) {
				return false;
			}
		}
		return true;
	}

	public static boolean isNotBlank(String str) {
		return !StringUtils.isBlank(str);
	}



}