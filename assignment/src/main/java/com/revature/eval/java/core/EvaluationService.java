package com.revature.eval.java.core;

import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class EvaluationService {

	/**
	 * 1. Without using the StringBuilder or StringBuffer class, write a method that
	 * reverses a String. Example: reverse("example"); -> "elpmaxe"
	 * 
	 * @param string
	 * @return
	 */
	public String reverse(String string) {
		char[] reversed = new char[string.length()];
		for (int i = reversed.length - 1, j=0; i >= 0; i--, j++) {
			reversed[j] = string.charAt(i);
		}
		return new String(reversed);
	}

	/**
	 * 2. Convert a phrase to its acronym. Techies love their TLA (Three Letter
	 * Acronyms)! Help generate some jargon by writing a program that converts a
	 * long name like Portable Network Graphics to its acronym (PNG).
	 * 
	 * @param phrase
	 * @return
	 */
	public String acronym(String phrase) {
		// Separate string by spaces
		String[] arrPhrase = phrase.split("\\P{L}+");
		char[] arrAcronym = new char[arrPhrase.length];
		for(int i = 0; i < arrAcronym.length; i++) {
			// Grab first letter of each string in array
			arrAcronym[i] = Character.toUpperCase(arrPhrase[i].toCharArray()[0]);
		}
		return new String(arrAcronym);
	}

	/**
	 * 3. Determine if a triangle is equilateral, isosceles, or scalene. An
	 * equilateral triangle has all three sides the same length. An isosceles
	 * triangle has at least two sides the same length. (It is sometimes specified
	 * as having exactly two sides the same length, but for the purposes of this
	 * exercise we'll say at least two.) A scalene triangle has all sides of
	 * different lengths.
	 *
	 */
	static class Triangle {
		private double sideOne;
		private double sideTwo;
		private double sideThree;

		public Triangle() {
			super();
		}

		public Triangle(double sideOne, double sideTwo, double sideThree) {
			this();
			this.sideOne = sideOne;
			this.sideTwo = sideTwo;
			this.sideThree = sideThree;
		}

		public double getSideOne() {
			return sideOne;
		}

		public void setSideOne(double sideOne) {
			this.sideOne = sideOne;
		}

		public double getSideTwo() {
			return sideTwo;
		}

		public void setSideTwo(double sideTwo) {
			this.sideTwo = sideTwo;
		}

		public double getSideThree() {
			return sideThree;
		}

		public void setSideThree(double sideThree) {
			this.sideThree = sideThree;
		}

		public boolean isEquilateral() {
			return (this.sideOne == this.sideTwo && this.sideOne == this.sideThree) ? true : false;
		}

		public boolean isIsosceles() {
			return (this.sideOne == this.sideTwo && this.sideOne != this.sideThree)
						|| (this.sideOne == this.sideThree && this.sideOne != this.sideTwo)
						|| (this.sideTwo == this.sideThree && this.sideTwo != this.sideOne) ? true: false;
		}

		public boolean isScalene() {
			return (!isEquilateral() && !isIsosceles());
		}

	}

	/**
	 * 4. Given a word, compute the scrabble score for that word.
	 * 
	 * --Letter Values-- Letter Value A, E, I, O, U, L, N, R, S, T = 1; D, G = 2; B,
	 * C, M, P = 3; F, H, V, W, Y = 4; K = 5; J, X = 8; Q, Z = 10; Examples
	 * "cabbage" should be scored as worth 14 points:
	 * 
	 * 3 points for C, 1 point for A, twice 3 points for B, twice 2 points for G, 1
	 * point for E And to total:
	 * 
	 * 3 + 2*1 + 2*3 + 2 + 1 = 3 + 2 + 6 + 3 = 5 + 9 = 14
	 * 
	 * @param string
	 * @return
	 */
	public int getScrabbleScore(String string) {
		Map<Character, Integer> scoreMap = new HashMap<Character, Integer>();
		
		// O(1) to retrieve data after all key-value pairs are added
		scoreMap.put('a', 1);
		scoreMap.put('e', 1);
		scoreMap.put('i', 1);
		scoreMap.put('o', 1);
		scoreMap.put('u', 1);
		scoreMap.put('l', 1);
		scoreMap.put('n', 1);
		scoreMap.put('r', 1);
		scoreMap.put('s', 1);
		scoreMap.put('t', 1);
		scoreMap.put('d', 2);
		scoreMap.put('g', 2);
		scoreMap.put('b', 3);
		scoreMap.put('c', 3);
		scoreMap.put('m', 3);
		scoreMap.put('p', 3);
		scoreMap.put('f', 4);
		scoreMap.put('h', 4);
		scoreMap.put('v', 4);
		scoreMap.put('w', 4);
		scoreMap.put('y', 4);
		scoreMap.put('k', 5);
		scoreMap.put('j', 8);
		scoreMap.put('x', 8);
		scoreMap.put('q', 10);
		scoreMap.put('z', 10);
		
		int score = 0;
		
		for(int i = 0; i < string.length(); i++) {
			// Accumulate score for each key in map
			score += scoreMap.get(Character.toLowerCase(string.charAt(i)));
		}
		
		return score;
	}

	/**
	 * 5. Clean up user-entered phone numbers so that they can be sent SMS messages.
	 * 
	 * The North American Numbering Plan (NANP) is a telephone numbering system used
	 * by many countries in North America like the United States, Canada or Bermuda.
	 * All NANP-countries share the same international country code: 1.
	 * 
	 * NANP numbers are ten-digit numbers consisting of a three-digit Numbering Plan
	 * Area code, commonly known as area code, followed by a seven-digit local
	 * number. The first three digits of the local number represent the exchange
	 * code, followed by the unique four-digit number which is the subscriber
	 * number.
	 * 
	 * The format is usually represented as
	 * 
	 * 1 (NXX)-NXX-XXXX where N is any digit from 2 through 9 and X is any digit
	 * from 0 through 9.
	 * 
	 * Your task is to clean up differently formatted telephone numbers by removing
	 * punctuation and the country code (1) if present.
	 * 
	 * For example, the inputs
	 * 
	 * +1 (613)-995-0253 613-995-0253 1 613 995 0253 613.995.0253 should all produce
	 * the output
	 * 
	 * 6139950253
	 * 
	 * Note: As this exercise only deals with telephone numbers used in
	 * NANP-countries, only 1 is considered a valid country code.
	 */
	public String cleanPhoneNumber(String string) {
		// Filter out to numbers without any constraints
		String parsedString = "";
		for(int i = 0; i < string.length(); i++) {
			char currNum = string.charAt(i);
			if(currNum >= '0' && currNum <= '9') {
				parsedString += currNum;
			} 
			else if(currNum == '-' || currNum == '.' || currNum == '(' || currNum == ')' || currNum == ' ') { // Ignore these chars
				continue;
			}
			else {
				throw new IllegalArgumentException("Phone is invalid because it contains a non-numerical value");
			}
		}
		
		// System.out.println(parsedString);
		
		// Add constraints
		
		// Check number of digits
		if(parsedString.length() > 11) { // NANP-countries only have 11 digits (Country code inclusive) or 10.
			throw new IllegalArgumentException("Has more than 11 digits, not a NANP-country phone");
		}
		
		else if(parsedString.length() < 10) { // Not enough digits
			throw new IllegalArgumentException("Not enough digits to complete a valid NANP-country phone numbner");
		}
		
		else if(parsedString.length() == 10) { // Case where string should be NXX.NXX.XXXX (No country code)
			for(int i = 0; i < 10; i++) {
				char currNum = parsedString.charAt(i);
				if(i == 0 || i == 3 ) { // Index where value is the N-digit
					if(!(currNum >= '2' && currNum <= '9')) {
						throw new IllegalArgumentException("Invalid phone number");
					}
				}
			}
		}
		else if(parsedString.length() == 11) { // Case where string should be 1.NXX.NXX.XXXX
			for(int i = 0; i < 11; i++) {
				char currNum = parsedString.charAt(i);
				if(i == 0) { // Country code
					if(currNum != '1') {
						throw new IllegalArgumentException("Not a NANP-country code");
					}
				}
				if(i == 1 || i == 4 ) { // Index where value is the N-digit
					if(!(currNum >= '2' && currNum <= '9')) {
						throw new IllegalArgumentException("Invalid phone number");
					}
				}
			}
		}
		
		return parsedString;
	}

	/**
	 * 6. Given a phrase, count the occurrences of each word in that phrase.
	 * 
	 * For example for the input "olly olly in come free" olly: 2 in: 1 come: 1
	 * free: 1
	 * 
	 * @param string
	 * @return
	 */
	public Map<String, Integer> wordCount(String string) {
		Map<String, Integer> wordCounts = new HashMap<String, Integer>();
		
		String[] phrases = string.split("\\P{L}+");
		
		for(int i = 0; i < phrases.length; i++) {
			if(wordCounts.containsKey(phrases[i])) {
				wordCounts.put(phrases[i], wordCounts.get(phrases[i])+1);
			}
			else {
				wordCounts.put(phrases[i], 1);
			}
		}
		
		return wordCounts;
	}

	/**
	 * 7. Implement a binary search algorithm.
	 * 
	 * Searching a sorted collection is a common task. A dictionary is a sorted list
	 * of word definitions. Given a word, one can find its definition. A telephone
	 * book is a sorted list of people's names, addresses, and telephone numbers.
	 * Knowing someone's name allows one to quickly find their telephone number and
	 * address.
	 * 
	 * If the list to be searched contains more than a few items (a dozen, say) a
	 * binary search will require far fewer comparisons than a linear search, but it
	 * imposes the requirement that the list be sorted.
	 * 
	 * In computer science, a binary search or half-interval search algorithm finds
	 * the position of a specified input value (the search "key") within an array
	 * sorted by key value.
	 * 
	 * In each step, the algorithm compares the search key value with the key value
	 * of the middle element of the array.
	 * 
	 * If the keys match, then a matching element has been found and its index, or
	 * position, is returned.
	 * 
	 * Otherwise, if the search key is less than the middle element's key, then the
	 * algorithm repeats its action on the sub-array to the left of the middle
	 * element or, if the search key is greater, on the sub-array to the right.
	 * 
	 * If the remaining array to be searched is empty, then the key cannot be found
	 * in the array and a special "not found" indication is returned.
	 * 
	 * A binary search halves the number of items to check with each iteration, so
	 * locating an item (or determining its absence) takes logarithmic time. A
	 * binary search is a dichotomic divide and conquer search algorithm.
	 * 
	 */
	static class BinarySearch<T>{
		private List<? extends Comparable<T>> sortedList;

		public int indexOf(T t) {
			int min = 0;
			int max = sortedList.size() + 1;
			
			int middle = (max+min)/2;
			
			while(min != max) {
				middle = (max+min)/2;
				
				Comparable<T> item = sortedList.get(middle);
				
				if(item.compareTo(t) > 0) { // Search left
					max = middle;
				}
				else if(item.compareTo(t) < 0) { // Search right
					min = middle;
				}
				else {
					break;
				}
			}
			if(sortedList.get(middle) != t) {
				System.out.println("Not found");
			}
			
			
			return middle;
		}

		public BinarySearch(List<? extends Comparable<T>> sortedList) {
			super();
			this.sortedList = sortedList;
		}

		public List<? extends Comparable<T>> getSortedList() {
			return sortedList;
		}

		public void setSortedList(List<? extends Comparable<T>> sortedList) {
			this.sortedList = sortedList;
		}

	}

	/**
	 * 8. Implement a program that translates from English to Pig Latin.
	 * 
	 * Pig Latin is a made-up children's language that's intended to be confusing.
	 * It obeys a few simple rules (below), but when it's spoken quickly it's really
	 * difficult for non-children (and non-native speakers) to understand.
	 * 
	 * Rule 1: If a word begins with a vowel sound, add an "ay" sound to the end of
	 * the word. Rule 2: If a word begins with a consonant sound, move it to the end
	 * of the word, and then add an "ay" sound to the end of the word. There are a
	 * few more rules for edge cases, and there are regional variants too.
	 * 
	 * See http://en.wikipedia.org/wiki/Pig_latin for more details.
	 * 
	 * @param string
	 * @return
	 */
	public String toPigLatin(String string) {
		String pigLatinTotal = "";
		
		// Split strings in case there are punctuations and spaces
		String[] words = string.split("\\P{L}+");
		
		String[] pigLatinWords = new String[words.length];
		for(int i = 0; i < words.length; i++) {
			pigLatinWords[i] = toPigLatinSingle(words[i]);
			System.out.println(pigLatinWords[i]);
		}

		// Add punctuation back in
		int pigIndex = 0;
		for(int i = 0; i < string.length(); i++) {
			
			boolean charIsLetter = isAlphabetical(string.charAt(i));
			if(charIsLetter) {
				if(i+1 < string.length()) {
					if(!isAlphabetical(string.charAt(i+1))) {
						pigLatinTotal += pigLatinWords[pigIndex];
						pigIndex++;
					}
				}
				else if(i+1 == string.length()) {
					pigLatinTotal += pigLatinWords[pigIndex];
				}
			} else {
				pigLatinTotal += string.charAt(i);
			}
		}
		
		return pigLatinTotal;
		
	}
	
	// Swaps a string to pig latin by single words only.
	public String toPigLatinSingle(String string) {
		String pigLatin = "";
		// Case for beginning with vowel sound
		if(isVowel(string.charAt(0))) {
			pigLatin += string + "ay";
		}
		else {
			// Iterate until reach vowel
			int i = 0;
			String prefix = "";
			
			
			while(!isVowel(string.charAt(i)) || (i > 0 && string.charAt(i) == 'u' && string.charAt(i-1) == 'q')) {
				// Concatenate to prefix
				prefix += string.charAt(i);
				i++;
			}
			pigLatin += string.substring(i) + prefix + "ay";
		}
		
		return pigLatin;
	}
	
	public boolean isVowel(char letter) {
		letter = Character.toLowerCase(letter);
		if(letter == 'a' || letter == 'e' || letter == 'i' || letter == 'o' || letter == 'u') {
			return true;
		}
		return false;
	}
	
	public boolean isAlphabetical(char letter) {
		return Character.toLowerCase(letter) >= 'a' && Character.toLowerCase(letter) <= 'z' ? true : false;
	}
	

	/**
	 * 9. An Armstrong number is a number that is the sum of its own digits each
	 * raised to the power of the number of digits.
	 * 
	 * For example:
	 * 
	 * 9 is an Armstrong number, because 9 = 9^1 = 9 10 is not an Armstrong number,
	 * because 10 != 1^2 + 0^2 = 2 153 is an Armstrong number, because: 153 = 1^3 +
	 * 5^3 + 3^3 = 1 + 125 + 27 = 153 154 is not an Armstrong number, because: 154
	 * != 1^3 + 5^3 + 4^3 = 1 + 125 + 64 = 190 Write some code to determine whether
	 * a number is an Armstrong number.
	 * 
	 * @param input
	 * @return
	 */
	public boolean isArmstrongNumber(int input) {
		String inputString = Integer.toString(input);
		int inputDigits = inputString.length();
		
		int armstrongNumber = 0;
		for(int i = 0; i < inputString.length(); i++) {
			armstrongNumber += Math.pow(Integer.parseInt(String.valueOf(inputString.charAt(i))), inputDigits);
		}
	
		
		return armstrongNumber == input;
	}

	/**
	 * 10. Compute the prime factors of a given natural number.
	 * 
	 * A prime number is only evenly divisible by itself and 1.
	 * 
	 * Note that 1 is not a prime number.
	 * 
	 * @param l
	 * @return
	 */
	public List<Long> calculatePrimeFactorsOf(long l) {
		List<Long> primeFactors = new ArrayList<>();
		
		Long firstFactor = (long) 2;
		
		while(l%firstFactor != 0) {
			firstFactor++;
		}
		Long secondFactor = l/firstFactor;
		if(secondFactor == 1 || secondFactor == l) { // Prime factor
			primeFactors.add(l);
			return primeFactors;
		}
		else { // Divide and conquer recursion
			primeFactors.addAll(calculatePrimeFactorsOf(firstFactor));
			primeFactors.addAll(calculatePrimeFactorsOf(secondFactor));
		}
		return primeFactors;
	}


	/**
	 * 11. Create an implementation of the rotational cipher, also sometimes called
	 * the Caesar cipher.
	 * 
	 * The Caesar cipher is a simple shift cipher that relies on transposing all the
	 * letters in the alphabet using an integer key between 0 and 26. Using a key of
	 * 0 or 26 will always yield the same output due to modular arithmetic. The
	 * letter is shifted for as many values as the value of the key.
	 * 
	 * The general notation for rotational ciphers is ROT + <key>. The most commonly
	 * used rotational cipher is ROT13.
	 * 
	 * A ROT13 on the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: nopqrstuvwxyzabcdefghijklm It is
	 * stronger than the Atbash cipher because it has 27 possible keys, and 25
	 * usable keys.
	 * 
	 * Ciphertext is written out in the same formatting as the input including
	 * spaces and punctuation.
	 * 
	 * Examples: ROT5 omg gives trl ROT0 c gives c ROT26 Cool gives Cool ROT13 The
	 * quick brown fox jumps over the lazy dog. gives Gur dhvpx oebja sbk whzcf bire
	 * gur ynml qbt. ROT13 Gur dhvpx oebja sbk whzcf bire gur ynml qbt. gives The
	 * quick brown fox jumps over the lazy dog.
	 */
	static class RotationalCipher {
		private int key;

		char[] arr = new char[] {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
		
		
		public RotationalCipher(int key) {
			super();
			this.key = key;
		}

		public String rotate(String string) {
			int modKey = this.key%26;
			char[] charArray = string.toCharArray();
			StringBuilder encryptedMessage = new StringBuilder();
			for(char c : charArray) {
				char encryptedChar;
				if(c >= 'a' && c <= 'z') {
					encryptedChar = (char) (c + modKey) > 'z' ? (char) (c+modKey-'z'+'a'-1) : (char) (c+modKey);
				}
				else if(c >= 'A' && c <= 'Z') {
					encryptedChar = (char) (c + modKey) > 'Z' ? (char) (c+modKey-'Z'+'A'-1) : (char) (c+modKey);
				}
				else {
					encryptedChar = c;
				}
				encryptedMessage.append(encryptedChar);
			}
			return encryptedMessage.toString();
		}

	}

	/**
	 * 12. Given a number n, determine what the nth prime is.
	 * 
	 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see
	 * that the 6th prime is 13.
	 * 
	 * If your language provides methods in the standard library to deal with prime
	 * numbers, pretend they don't exist and implement them yourself.
	 * 
	 * @param i
	 * @return
	 */
	public int calculateNthPrime(int i) {
		if(i < 2) {
			throw new IllegalArgumentException();
		}
		
		HashSet<Integer> primeFactorsFound = new HashSet<Integer>();
		primeFactorsFound.add(2);
		
		int found = 1;
		
		int currentNumber = 2;
		
		while(found < i) {
			boolean addPrime = true;
			
			for(Integer prime: primeFactorsFound) {
				if(currentNumber%prime == 0) {
					addPrime = false;
				}
			}
			
			// Check if any pre-existing primes are prime factors of the current number
			if(addPrime) {
				primeFactorsFound.add(currentNumber);
				found++;
			}
			if(found < i) {
				currentNumber++;
			}
			
		}
		
		return currentNumber;
	}

	/**
	 * 13 & 14. Create an implementation of the atbash cipher, an ancient encryption
	 * system created in the Middle East.
	 * 
	 * The Atbash cipher is a simple substitution cipher that relies on transposing
	 * all the letters in the alphabet such that the resulting alphabet is
	 * backwards. The first letter is replaced with the last letter, the second with
	 * the second-last, and so on.
	 * 
	 * An Atbash cipher for the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: zyxwvutsrqponmlkjihgfedcba It is a
	 * very weak cipher because it only has one possible key, and it is a simple
	 * monoalphabetic substitution cipher. However, this may not have been an issue
	 * in the cipher's time.
	 * 
	 * Ciphertext is written out in groups of fixed length, the traditional group
	 * size being 5 letters, and punctuation is excluded. This is to make it harder
	 * to guess things based on word boundaries.
	 * 
	 * Examples Encoding test gives gvhg Decoding gvhg gives test Decoding gsvjf
	 * rxpyi ldmul cqfnk hlevi gsvoz abwlt gives thequickbrownfoxjumpsoverthelazydog
	 *
	 */
	static class AtbashCipher {

		/**
		 * Question 13
		 * 
		 * @param string
		 * @return
		 */
		public static String encode(String string) {
			final int total = 25;
			char[] charArray = string.toCharArray();
			
			for(char c : charArray) {
				
			}
			return null;
		}

		/**
		 * Question 14
		 * 
		 * @param string
		 * @return
		 */
		public static String decode(String string) {
			// TODO Write an implementation for this method declaration
			return null;
		}
	}

	/**
	 * 15. The ISBN-10 verification process is used to validate book identification
	 * numbers. These normally contain dashes and look like: 3-598-21508-8
	 * 
	 * ISBN The ISBN-10 format is 9 digits (0 to 9) plus one check character (either
	 * a digit or an X only). In the case the check character is an X, this
	 * represents the value '10'. These may be communicated with or without hyphens,
	 * and can be checked for their validity by the following formula:
	 * 
	 * (x1 * 10 + x2 * 9 + x3 * 8 + x4 * 7 + x5 * 6 + x6 * 5 + x7 * 4 + x8 * 3 + x9
	 * * 2 + x10 * 1) mod 11 == 0 If the result is 0, then it is a valid ISBN-10,
	 * otherwise it is invalid.
	 * 
	 * Example Let's take the ISBN-10 3-598-21508-8. We plug it in to the formula,
	 * and get:
	 * 
	 * (3 * 10 + 5 * 9 + 9 * 8 + 8 * 7 + 2 * 6 + 1 * 5 + 5 * 4 + 0 * 3 + 8 * 2 + 8 *
	 * 1) mod 11 == 0 Since the result is 0, this proves that our ISBN is valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isValidIsbn(String string) {
		// TODO Write an implementation for this method declaration
		return false;
	}

	/**
	 * 16. Determine if a sentence is a pangram. A pangram (Greek: παν γράμμα, pan
	 * gramma, "every letter") is a sentence using every letter of the alphabet at
	 * least once. The best known English pangram is:
	 * 
	 * The quick brown fox jumps over the lazy dog.
	 * 
	 * The alphabet used consists of ASCII letters a to z, inclusive, and is case
	 * insensitive. Input will not contain non-ASCII symbols.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isPangram(String string) {
		// TODO Write an implementation for this method declaration
		return false;
	}

	/**
	 * 17. Calculate the moment when someone has lived for 10^9 seconds.
	 * 
	 * A gigasecond is 109 (1,000,000,000) seconds.
	 * 
	 * @param given
	 * @return
	 */
	public Temporal getGigasecondDate(Temporal given) {
		// TODO Write an implementation for this method declaration
		return null;
	}

	/**
	 * 18. Given a number, find the sum of all the unique multiples of particular
	 * numbers up to but not including that number.
	 * 
	 * If we list all the natural numbers below 20 that are multiples of 3 or 5, we
	 * get 3, 5, 6, 9, 10, 12, 15, and 18.
	 * 
	 * The sum of these multiples is 78.
	 * 
	 * @param i
	 * @param set
	 * @return
	 */
	public int getSumOfMultiples(int i, int[] set) {
		// TODO Write an implementation for this method declaration
		return 0;
	}

	/**
	 * 19. Given a number determine whether or not it is valid per the Luhn formula.
	 * 
	 * The Luhn algorithm is a simple checksum formula used to validate a variety of
	 * identification numbers, such as credit card numbers and Canadian Social
	 * Insurance Numbers.
	 * 
	 * The task is to check if a given string is valid.
	 * 
	 * Validating a Number Strings of length 1 or less are not valid. Spaces are
	 * allowed in the input, but they should be stripped before checking. All other
	 * non-digit characters are disallowed.
	 * 
	 * Example 1: valid credit card number 1 4539 1488 0343 6467 The first step of
	 * the Luhn algorithm is to double every second digit, starting from the right.
	 * We will be doubling
	 * 
	 * 4_3_ 1_8_ 0_4_ 6_6_ If doubling the number results in a number greater than 9
	 * then subtract 9 from the product. The results of our doubling:
	 * 
	 * 8569 2478 0383 3437 Then sum all of the digits:
	 * 
	 * 8+5+6+9+2+4+7+8+0+3+8+3+3+4+3+7 = 80 If the sum is evenly divisible by 10,
	 * then the number is valid. This number is valid!
	 * 
	 * Example 2: invalid credit card number 1 8273 1232 7352 0569 Double the second
	 * digits, starting from the right
	 * 
	 * 7253 2262 5312 0539 Sum the digits
	 * 
	 * 7+2+5+3+2+2+6+2+5+3+1+2+0+5+3+9 = 57 57 is not evenly divisible by 10, so
	 * this number is not valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isLuhnValid(String string) {
		// TODO Write an implementation for this method declaration
		return false;
	}

	/**
	 * 20. Parse and evaluate simple math word problems returning the answer as an
	 * integer.
	 * 
	 * Add two numbers together.
	 * 
	 * What is 5 plus 13?
	 * 
	 * 18
	 * 
	 * Now, perform the other three operations.
	 * 
	 * What is 7 minus 5?
	 * 
	 * 2
	 * 
	 * What is 6 multiplied by 4?
	 * 
	 * 24
	 * 
	 * What is 25 divided by 5?
	 * 
	 * 5
	 * 
	 * @param string
	 * @return
	 */
	public int solveWordProblem(String string) {
		// TODO Write an implementation for this method declaration
		return 0;
	}

}
