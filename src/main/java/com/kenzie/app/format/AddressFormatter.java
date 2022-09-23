package com.kenzie.app.format;

import java.util.HashMap;

public class AddressFormatter {
    //declare properties
    private static HashMap<String,String> codeTable = new HashMap<>();
    //static initializer-runs automatically when class loaded
    static {
        codeTable.put("ST", "STREET");
        codeTable.put("ST.", "STREET");
        codeTable.put("St", "STREET");
        codeTable.put("St.", "STREET");
        codeTable.put("RD", "ROAD");
        codeTable.put("RD.", "ROAD");
        codeTable.put("Rd", "ROAD");
        codeTable.put("Rd.", "ROAD");
        codeTable.put("AVE", "AVENUE");
        codeTable.put("AVE.", "AVENUE");
        codeTable.put("Ave", "AVENUE");
        codeTable.put("Ave.", "AVENUE");

    }
    public static void initCodeTable(){
        //enter values into hashmap
        codeTable.put("ST", "STREET");
        codeTable.put("ST.", "STREET");
        codeTable.put("St", "STREET");
        codeTable.put("St.", "STREET");
        codeTable.put("RD", "ROAD");
        codeTable.put("RD.", "ROAD");
        codeTable.put("Rd", "ROAD");
        codeTable.put("Rd.", "ROAD");
        codeTable.put("AVE", "AVENUE");
        codeTable.put("AVE.", "AVENUE");
        codeTable.put("Ave", "AVENUE");
        codeTable.put("Ave.", "AVENUE");
    }
    //example input: 123 Main St.
    //output: 123 MAIN STREET
    public static String replaceAbbreviation(String input){
        String result = "";
        //1. for each entry in hashmap, search key, replace with value
        for (String currentKey: codeTable.keySet()){
            if (input.contains(currentKey)){
            String currentValue = codeTable.get(currentKey);
            result = input.replace(currentKey,currentValue);
            }
        }
        return result;
    }
    public static String formatAddress(String input){
        return input.toUpperCase();
    }
    public static String formatStreetAddress(String input){
        return formatAddress(replaceAbbreviation(input));
    }

    public static void main(String[] args) {
        initCodeTable();
        String testAddress = "123 Main St.";
        System.out.println(AddressFormatter.replaceAbbreviation(testAddress));
    }
}
