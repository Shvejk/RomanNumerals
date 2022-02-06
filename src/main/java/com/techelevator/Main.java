package com.techelevator;

import java.util.*;

public class Main {
    private static HashMap<String, Integer> romanNumeralToDecimal = new HashMap<>();
    private static ArrayList<String> allPossibleRomanNumerals = new ArrayList<>();
    private static HashMap<Integer, String> decimalToRomanNumeral = new HashMap<>();
    private static List<String> validRomanNumerals = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Populate romanNumeralToDecimal
        romanNumeralToDecimal.put("I", 1);
        romanNumeralToDecimal.put("IV", 4);
        romanNumeralToDecimal.put("V", 5);
        romanNumeralToDecimal.put("IX", 9);
        romanNumeralToDecimal.put("X", 10);
        romanNumeralToDecimal.put("XL", 40);
        romanNumeralToDecimal.put("L", 50);
        romanNumeralToDecimal.put("XC", 90);
        romanNumeralToDecimal.put("C", 100);
        romanNumeralToDecimal.put("CD", 400);
        romanNumeralToDecimal.put("D", 500);
        romanNumeralToDecimal.put("CM", 900);
        romanNumeralToDecimal.put("M", 1000);

        // Populate decimalToRomanNumeral
        decimalToRomanNumeral.put(1, "I");
        decimalToRomanNumeral.put(4, "IV");
        decimalToRomanNumeral.put(5, "V");
        decimalToRomanNumeral.put(9, "IX");
        decimalToRomanNumeral.put(10, "X");
        decimalToRomanNumeral.put(40, "XL");
        decimalToRomanNumeral.put(50, "L");
        decimalToRomanNumeral.put(90, "XC");
        decimalToRomanNumeral.put(100, "C");
        decimalToRomanNumeral.put(400, "CD");
        decimalToRomanNumeral.put(500, "D");
        decimalToRomanNumeral.put(900, "CM");
        decimalToRomanNumeral.put(1000, "M");


        // Make histogram of roman numeral length
        // Populate Valid Roman Numerals ArrayList
        int[] romanNumeralLengthsHistogram = new int[15]; // Longest roman numerals less than 3399 is 14, zero-ith index is skipped
        for (int i = 1; i <= 3399; i++) {
            String romanNumeral = convertDecimalToRomanNumeral(i);
            validRomanNumerals.add(romanNumeral);
            //System.out.println(i + "\t" + romanNumeral + "\t" + isOnlyValidRomanNumeralSymbols(romanNumeral);
            romanNumeralLengthsHistogram[romanNumeral.length()]++;
//            if (romanNumeral.length() == 14)
//                System.out.println(romanNumeral + "\t" + convert(romanNumeral));
        }

        String input;
        do {
            System.out.print("Enter a roman numeral (enter 'q' to quit): ");
            input = scanner.nextLine();
            System.out.println(input + " is valid Roman Numeral: " + isValidRomanNumeral(input));
        } while (!input.equals("q"));
        //System.out.println(validRomanNumerals.size());
        /*
        int verifyCorrectNumberOfRomanNumerals = 0; // Expected 3399
        for (int i = 0; i < romanNumeralLengthsHistogram.length; i++) {
            System.out.println("Roman numeral of length " + i + ": " + romanNumeralLengthsHistogram[i]);
            verifyCorrectNumberOfRomanNumerals += romanNumeralLengthsHistogram[i];
        }
        System.out.println(verifyCorrectNumberOfRomanNumerals);



        //Change map to char array for printRomanNumerals()
        Character[] chars = romanNumeralToDecimal.keySet().toArray(new Character[romanNumeralToDecimal.size()]);


        // Orders Roman Numerals for printing
        // Should use TreeMap instead to maintain sorted order by values
        List<Character> sortedRomanNumerals = new ArrayList<>();

        sortedRomanNumerals.add('I');
        sortedRomanNumerals.add('V');
        sortedRomanNumerals.add('X');
        sortedRomanNumerals.add('L');
        sortedRomanNumerals.add('C');
        sortedRomanNumerals.add('D');
        sortedRomanNumerals.add('M');

        //printRomanNumerals(sortedRomanNumerals, "", sortedRomanNumerals.size(), 13);

        System.out.println(convert("MMMCCCXXXVIII"));
        // MMMCCCXXXVIII - 3338

         */
    }

    // Assumes valid input for Roman numeral
    public static int convert(String romanNumeral) {
        int number = 0;
        int lastIndex = romanNumeral.length() - 1;
        for (int i = lastIndex; i >= 0; i--) {
            char ch = romanNumeral.charAt(i);
            number += romanNumeralToDecimal.get(String.valueOf(ch));
            // Avoid index out of bounds
            if (i != lastIndex) {
                // Compare to next character
                char prevCh = romanNumeral.charAt(i + 1);
                if (romanNumeralToDecimal.get(String.valueOf(ch)) < romanNumeralToDecimal.get(String.valueOf(prevCh)))
                    number -= (romanNumeralToDecimal.get(String.valueOf(ch)) * 2);
            }
        }
        return number;
    }


    // Recursive N Loop formula
    // Parameters: Need an array of characters
    // Need String to concatenate each combination
    // Need length of string
    // Need Total length of character set
    // Implementation borrowed from https://www.geeksforgeeks.org/print-all-combinations-of-given-length/
    public static void printRomanNumerals(List list, String prefix, int setLength, int stringLength) {
        //Print a string of length 10 (largest)
        if (stringLength == 0) {
            //System.out.println(prefix);
            allPossibleRomanNumerals.add(prefix);
            return;
        }
        String newPrefix = prefix;
        //Iterate over char set
        for (int i = 0; i < setLength; i++) {
            newPrefix = prefix + list.get(i);

            // Iterate over as long as you want the prefix String to be
            printRomanNumerals(list, newPrefix, list.size(), stringLength - 1);
        }
    }

    public static String convertDecimalToRomanNumeral(int num) {
        StringBuilder romanNumeral = new StringBuilder();
        while (num != 0) {
            if (num >= 1000) {
                romanNumeral.append('M');
                num -= 1000;
            } else if (num >= 900) {
                romanNumeral.append('C');
                romanNumeral.append('M');
                num -= 900;
            } else if (num >= 500) {
                romanNumeral.append('D');
                num -= 500;
            } else if (num >= 400) {
                romanNumeral.append('C');
                romanNumeral.append('D');
                num -= 400;
            } else if (num >= 100) {
                romanNumeral.append('C');
                num -= 100;
            } else if (num >= 90) {
                romanNumeral.append('X');
                romanNumeral.append('C');
                num -= 90;
            } else if (num >= 50) {
                romanNumeral.append('L');
                num -= 50;
            } else if (num >= 40) {
                romanNumeral.append('X');
                romanNumeral.append('L');
                num -= 40;
            } else if (num >= 10) {
                romanNumeral.append('X');
                num -= 10;
            } else if (num >= 9) {
                romanNumeral.append('I');
                romanNumeral.append('X');
                num -= 9;
            } else if (num >= 5) {
                romanNumeral.append('V');
                num -= 5;
            } else if (num >= 4) {
                romanNumeral.append('I');
                romanNumeral.append('V');
                num -= 4;
            } else {
                romanNumeral.append('I');
                num -= 1;
            }
        }
        return romanNumeral.toString();
    }

    public static boolean isValidRomanNumeral(String romanNumeral) {
        return validRomanNumerals.contains(romanNumeral);
    }
}
