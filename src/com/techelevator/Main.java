package com.techelevator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    private static HashMap<Character, Integer> map = new HashMap<>();

    public static void main(String[] args) {
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        Character[] chars = map.keySet().toArray(new Character[map.size()]);

        /*
        for (Character ch : map.keySet())
            System.out.println(ch + "=" + map.get(ch));

         */


        List<Map.Entry<Character, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());
//        list.forEach(System.out::println);


        List<Character> sortedRomanNumerals = new ArrayList<>();

        sortedRomanNumerals.add('I');
        sortedRomanNumerals.add('V');
        sortedRomanNumerals.add('X');
        sortedRomanNumerals.add('L');
        sortedRomanNumerals.add('C');
        sortedRomanNumerals.add('D');
        sortedRomanNumerals.add('M');

        printRomanNumerals(sortedRomanNumerals, "", sortedRomanNumerals.size(), 3);

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
