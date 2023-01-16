package com.company;
import java.util.Scanner;

public class Main {

    private static char action;
    private static String[] data;

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        String exp = scn.nextLine();

        if (exp.contains("\"")) {
                if (exp.contains(" + ")) {
                    additionStrings(exp);
                } else if (exp.contains(" - ")) {
                    subtractionStrings(exp);
                } else if (exp.contains(" * ")) {
                    multiplicationStrings(exp);
                } else if (exp.contains(" / ")) {
                    divisionStrings(exp);
                } else {
                    throw new Exception("Некорректный знак действия");
                }
                if (action == '*' || action == '/') {
                    if (data[1].contains("\"")) {
                        throw new Exception("Строчку можно делить или умножать только на число");
                    }
                }
                for (int i = 0; i < data.length; i++) {
                    data[i] = data[i].replace("\"", "");
                }

                if (action == '+') {
                    printInQuotes(data[0] + data[1]);
                } else if (action == '*') {
                    int multiplier = Integer.parseInt(data[1]);
                    String result = "";
                    for (int i = 0; i < multiplier; i++) {
                        result += data[0];
                    }
                    printInQuotes(result);
                } else if (action == '-') {
                    int index = data[0].indexOf(data[1]);
                    if (index == -1) {
                        printInQuotes(data[0]);
                    } else {
                        String result = data[0].substring(0, index);
                        result += data[0].substring(index + data[1].length());
                        printInQuotes(result);
                    }
                } else {
                    int newLen = data[0].length() / Integer.parseInt(data[1]);
                    String result = data[0].substring(0, newLen);
                    printInQuotes(result);
                }

        }
        else {
            throw new Exception("Строчки пишутся только в кавычках!");
        }
    }
    static void printInQuotes(String text){
        String result = text;
        if (text.length() > 40) {
            result = text.substring(0, 39) + "...";
        }
        System.out.println("\"" + result + "\"");
    }
    static void additionStrings(String text) throws Exception {
        data = text.split(" \\+ ");
        if (data[0].contains("\"")) {
            if ((data[0].length() <= 10) && (data[1].length() <= 10)) {
                action = '+';
            } else {
                throw new Exception("В строках указывается не более 10-ти знаков!");
            }
        } else {
                throw new Exception("Первое значение должно содержать строку!");
            }
    }
    static void subtractionStrings(String text) {
        data = text.split(" - ");
        action = '-';
    }
    static void multiplicationStrings(String text) {
        data = text.split(" \\* ");
        action = '*';
    }
    static void divisionStrings(String text) {
        data = text.split(" / ");
        action = '/';
    }
}
