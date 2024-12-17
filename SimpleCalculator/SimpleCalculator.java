package SimpleCalculator;
import java.util.Scanner;

public class SimpleCalculator {
    /**
     * main method which is a simple calculator
     * @param args
     * @throws IllegalArgumentException if the choice is invalid
     */
    public static void main(String[] args) {
        final double var1;
        final double var2;
        final int choice;

        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the first number: ");
        var1 = scan.nextDouble();

        System.out.println("Enter the second number: ");
        var2 = scan.nextDouble();

        SimpleCalculator calc = new SimpleCalculator();
        calc.addition(var1, var2);
        calc.subtraction(var1, var2);
        calc.multiplication(var1, var2);
        calc.division(var1, var2);
        calc.modulus(var1, var2);

        System.out.println("Choose an operation \n (1): Addition \n (2): Subtration \n (3): Multiplikation \n (4): Division \n (5): Modulus");
        choice = scan.nextInt();
        if (choice == 1) {
            calc.addition(var1, var2);
            System.out.println("The addition of " + var1 + " and " + var2 + " is: " + (var1 + var2));
        } else if (choice == 2) {
            calc.subtraction(var1, var2);
            System.out.println("The subtraction of " + var1 + " and " + var2 + " is: " + (var1 - var2));
        } else if (choice == 3) {
            calc.multiplication(var1, var2);
            System.out.println("The multipllication of " + var1 + " and " + var2 + " is: " + (var1 * var2));
        } else if (choice == 4) {
            calc.division(var1, var2);
            System.out.println("The division of " + var1 + " and " + var2 + " is: " + (var1 / var2));
        } else if (choice == 5) {
            calc.modulus(var1, var2);
            System.out.println("The modulus of " + var1 + " and " + var2 + " is: " + (var1 % var2));
        } else {
            throw new IllegalArgumentException("Invalid choice");
        }
    }
    public void addition(final double var1, final double var2) {
        final double result = var1 + var2;
         // System.out.println("The Addition is: " + result);
    }
    public void subtraction(final double var1, final double var2) {
        final double result = var1 - var2;
        // System.out.println("The Subtraction is: " + result);
    }
    public void multiplication(final double var1, final double var2) {
        final double result = var1 * var2;
        // System.out.println("The Multiplication is: " + result);
    }
    public void division(final double var1, final double var2) {
        final double result = var1 / var2;
        // System.out.println("The Division is: " + result);
    }
    public void modulus(final double var1, final double var2) {
        final double result = var1 % var2;
        // System.out.println("The Modulus is: " + result);
    }

}

