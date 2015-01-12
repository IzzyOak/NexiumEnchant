package com.nexiummc.nexiumenchant.handlers;

import org.apache.commons.lang.Validate;

/**
 * This class is used to convert Roman numerals from integer form to Roman numeral form, and vice versa.
 * There are two arrays inside this class, {@link #numbers} an {@link #numerals}.
 * <p>
 * These arrays are sorted in an order that
 * @author searchndstroy
 */
public final class RomanNumeralHandler {
	
	/**
	 * Valid Roman numerals that have been converted from numeral to whole number.
	 * <p> <code> numbers[n] == numerals[n] </code> (Converted.) </p>
	 */
	private static final short[] numbers = {
		900, 1000, 400, 500, 90, 100, 40, 50, 9, 10, 4, 5, 1
	};
	
	/**
	 * Valid Roman numeral literals.
	 * <p> <code> numerals[n] == numbers[n] </code> (Converted.) </p>
	 */
	private static final String[] numerals = {
		"CM",  "M",  "CD",  "D", "XC",  "C", "XL", "L",  "IX",  "X", "IV",  "V", "I"
	};
	
	/**
	 * Method to convert Roman numeral strings to integers.
	 * <p>
	 * 
	 * Sets a temporary Roman numeral equal to romanNumeral, but, applying {@link String#toUpperCase()} on the romanNumeral. Then,
	 * an integer called total is defined. This integer is the romanNumeral converted to an integer.
	 * 
	 * <p> <code> String tempRomanNumeral = romanNumeral.toUpperCase();
	 * <p> int total = 0; </code> <p>
	 * 
	 * While iterating through all the valid Roman numerals, three integers are defined: before, after, and amount.
	 * 'before' is set to the tempRomanNumeral's length, while after and amount are left undefined.
	 * 
	 * <p> <code> int before = tempRomanNumeral.length(), after, amount; </code> <p>
	 * 
	 * Where numeral is the current Roman numeral (String), this method is invoked:
	 * {@link String#replaceAll(String, String)} where the second argument is "". This is invoked to replace all occurences of the numeral
	 * inside the string.
	 * 
	 * <p> <code> tempRomanNumeral = tempRomanNumeral.replaceAll(numeral, ""); </code> <p>
	 * 
	 * Then, 'after' is set to the tempRomanNumeral's length. This is to tell how many occurences of the numeral were inside
	 * the string.
	 * 
	 * <p> <code> after = tempRomanNumeral.length(); </code> <p>
	 * 
	 * The total amount of occurences is set by first subtracting before and after, and then dividing by the numeral length. Dividing by
	 * the numeral length makes sure that a numeral with two characters (For example, "IV") is not counted twice, since 2 characters would be
	 * replaced and we need to make sure that we don't count it as two numerals.
	 * 
	 * <p> <code> amount = (before - after) / numeral.length(); </code> <p>
	 * 
	 * 
	 * 
	 * @param romanNumeral String of Roman numerals to convert into an integer.
	 * dw
	 * @return Returns an integer from the string of Roman numerals.
	 * 
	 * @throws IllegalArgumentException If the Roman numeral is not valid.
	 * An invalid Roman numeral is a numeral that may have characters left inside the tempRomanNumeral
	 * after convertering and/or no numerals at all were found inside the tempRomanNumeral.
	 * @throws NullPointerException If the Roman numeral is null.
	 * 
	 * @see {@link String#toUpperCase()}
	 * @see {@link String#length()}
	 * @see {@link String#replaceAll(String, String)}
	 * @see {@link String#equals(Object)}
	 */
	public static short romanToInt(String romanNumeral) throws IllegalArgumentException, NullPointerException {
		Validate.notNull(romanNumeral, "Roman numeral cannot be null!");
		String tempRomanNumeral = romanNumeral.toUpperCase();
		short total = 0;
		for (int i = 0; i < numerals.length; i++) {
			short number = numbers[i];
			String numeral = numerals[i];
			int before = tempRomanNumeral.length(), after, amount;
			tempRomanNumeral = tempRomanNumeral.replaceAll(numeral, "");
			after = tempRomanNumeral.length();
			amount = (before - after) / numeral.length();
			total += amount * number;
		}
		if ((total < 1) || !tempRomanNumeral.equals("")) {
			throw new IllegalArgumentException("Roman numeral '" + romanNumeral + "' is invalid!");
		}
		return total;
	}
	
	
	/**
	 * @param numberToConvert Number to convert into a Roman numeral.
	 * @return If the number to convert was less than one, null will be returned. Otherwise, a valid string of Roman numerals will be returned.
	 * <p> <code> intToRoman(2014) -> MMXIV </code> </p>
	 * @throws IllegalArgumentException If the number is not positive.
	 */
	public static String intToRoman(short numberToConvert) throws IllegalArgumentException {
		StringBuilder toReturn = new StringBuilder();
		short toConvert = numberToConvert;
		if (numberToConvert < 1) {
			throw new IllegalArgumentException("Number to convert must be positive!");
		}
		int counter = 0;
		boolean first = true;
		for (int i = 1; i < (numbers.length - 1);) {
			if (!first) {
				switch (counter % 2) {
					case 0:
						i += 3;
						break;
					case 1:
						i--;
						break;
				}
			} else {
				first = false;
			}
			if (i >= numbers.length) {
				i = numbers.length - 1;
			}
			int number = numbers[i];
			String numeral = numerals[i];
			int amount = toConvert / number;
			toConvert -= amount * number;
			for (int count = 0; count < amount; count++) {
				toReturn.append(numeral);
			}
			counter++;
		}
		return toReturn.toString();
	}
	
	private RomanNumeralHandler() {
		
	}
}