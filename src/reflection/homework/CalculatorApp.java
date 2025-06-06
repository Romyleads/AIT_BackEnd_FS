package src.reflection.homework;

import src.reflection.homework.infra.CalculatorUtils;

public class CalculatorApp {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java CalculatorApp <a> <b> <action: Add +,Subtract -,Multiply *, Divide :>");
            return;
        }
        CalculatorUtils.caller(args[2], args[0], args[1]);
    }
}
