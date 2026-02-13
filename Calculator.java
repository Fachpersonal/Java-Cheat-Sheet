package dev.fachpersonal;

import java.util.Scanner;
import java.util.Stack;

public class Calculator {
    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;

        while(true) {
            System.out.println("Gib einen mathematischen Ausdruck ein:");
            input = scanner.nextLine().replaceAll("\\s+", "");

            if(input.equalsIgnoreCase("!exit"))
                return;

            try {
                double result = evaluate(input);
                System.out.println("Ergebnis: " + result);
            } catch (Exception e) {
                System.out.println("Fehlerhafter Ausdruck!");
            }

        }
    }

    public static double evaluate(String expression) {
        Stack<Double> values = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            // Zahl einlesen (auch mehrstellig)
            if (Character.isDigit(ch) || ch == '.') {
                StringBuilder sb = new StringBuilder();
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    sb.append(expression.charAt(i));
                    i++;
                }
                values.push(Double.parseDouble(sb.toString()));
                i--;
            }
            else if (ch == '(') { // Öffnende Klammer
                operators.push(ch);
            }
            else if (ch == ')') { // Schließende Klammer
                while (operators.peek() != '(') {
                    values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
                }
                operators.pop();
            }
            else if (isOperator(ch)) { // Operator
                while (!operators.isEmpty() && hasPrecedence(ch, operators.peek())) {
                    values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
                }
                operators.push(ch);
            }
        }

        // Restliche Operatoren berechnen
        while (!operators.isEmpty()) {
            values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
        }

        return values.pop();
    }

    private static boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }

    private static boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')')
            return false;
        return (op1 != '*' && op1 != '/') || (op2 != '+' && op2 != '-');
    }

    private static double applyOperator(char op, double b, double a) {
        return switch (op) {
            case '+' -> a + b;
            case '-' -> a - b;
            case '*' -> a * b;
            case '/' -> {
                if (b == 0)
                    throw new ArithmeticException("Division durch 0");
                yield a / b;
            }
            default -> 0;
        };
    }
}
