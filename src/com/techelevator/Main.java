package com.techelevator;

import java.util.*;

public class Main {
    private static HashMap<Character, Integer> map = new HashMap<>();

    public static void main(String[] args) {
        // Populate map for convert()
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        //Change map to char array for printRomanNumerals()
        Character[] chars = map.keySet().toArray(new Character[map.size()]);


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

        printRomanNumerals(sortedRomanNumerals, "", sortedRomanNumerals.size(), 5);

    }

    // Assumes valid input for Roman numeral
    public static int convert(String romanNumeral) {
        int number = 0;
        int lastIndex = romanNumeral.length() - 1;
        for (int i = lastIndex; i >= 0; i--) {
            char ch = romanNumeral.charAt(i);
            number += map.get(ch);
            // Avoid index out of bounds
            if (i != lastIndex) {
                // Compare to next character
                char prevCh = romanNumeral.charAt(i + 1);
                if (map.get(ch) < map.get(prevCh))
                    number -= (map.get(ch) * 2);
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
            System.out.println(prefix);
            return;
        }
        String newPrefix = prefix;
        //Iterate over char set
        for (int i = 0; i < setLength; i++) {
            newPrefix = prefix + list.get(i);

            //Iterate over as long as you want the prefix String to be
            printRomanNumerals(list, newPrefix, list.size(), stringLength - 1);
        }
    }
}
