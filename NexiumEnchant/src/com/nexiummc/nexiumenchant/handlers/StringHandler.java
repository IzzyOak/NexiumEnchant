package com.nexiummc.nexiumenchant.handlers;

/**
 * String handler.
 * @author searchndstroy
 */
public final class StringHandler {
	
	/**
	 * Splits a string in two.
	 * @param string String to split.
	 * @param character Character to split by.
	 * @param firstHalf Whether to return the first or second half.
	 * @return The first or second 'half' of the string.
	 */
	public static String split(String string, char character, boolean firstHalf) {
		int start = firstHalf ? 0 : string.indexOf(character);
		if (start > -1) {
			int end = string.indexOf(character, start);
			return string.substring(start, end);
		}
		return "";
	}
	
	private StringHandler() {
		
	}
}