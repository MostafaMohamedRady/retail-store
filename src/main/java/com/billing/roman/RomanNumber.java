package com.billing.roman;

import org.springframework.stereotype.Component;

public class RomanNumber {
    private int number;
    private static final int MAX_VALUE = 3000;

    public RomanNumber(int number) {
        if (number <= 0 || number > MAX_VALUE) {
            //replace it with custom exception
            throw new IllegalArgumentException("RomanNumber only supports numbers up to 3000");
        }
        this.number = number;
    }

    public void setNumber(String number) {
        try {
            this.number = Integer.parseInt(number);
        } catch (NumberFormatException e){
            throw new IllegalArgumentException("Invalid input - {} is not a number");
        }
        if (this.number <= 0 || this.number > MAX_VALUE) {
            throw new IllegalArgumentException("RomanNumber only supports numbers up to 3000");
        }
    }

    public String convert() {
        StringBuilder result = new StringBuilder();
        try {
            int thousands = this.number / 1000;
            result.append(times(thousands, "M"));
            int hundreds = this.number / 100 % 10;
            result.append(times(hundreds, "C", "D", "M"));
            int tens = this.number / 10 % 10;
            result.append(times(tens, "X", "L", "C"));
            int ones = this.number % 10;
            result.append(times(ones, "I", "V", "X"));
        } catch (Exception ex) {
            System.out.println("An error occurred");
        }
        return result.toString();
    }

    private String times(int number, String character) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < number; i++) {
            result.append(character);
        }
        return result.toString();
    }

    private String times(int number, String o, String f, String t) {
        switch (number) {
            case 0:
                return "";
            case 1:
            case 2:

            case 3:
                return times(number, o);
            case 4:
                return o + f;
            case 5:
            case 6:
            case 7:
            case 8:
                return f + times(number - 5, o);
            case 9:
                return o + t;
            default:
                throw new IllegalArgumentException("Only single digits allowed -not " + number);
        }
    }
}
