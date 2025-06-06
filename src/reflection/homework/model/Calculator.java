package src.reflection.homework.model;

public class Calculator {
    public void operate(String a, String b, String action) {
        double num1 = Double.parseDouble(a);
        double num2 = Double.parseDouble(b);

        double result = 0;
        switch (action) {
            case "Add":
                result = num1 + num2;
                break;
            case "Subtract":
                result = num1 - num2;
                break;
            case "Multiply":
                result = num1 * num2;
                break;
            case "Divide":
                if (num2 == 0) {
                    System.out.println("Error: Division by zero!");
                    return;
                }
                result = num1 / num2;
                break;
            default:
                System.out.println("Unknown action: " + action);
                return;
        }
        System.out.println((int)result);
    }
}
