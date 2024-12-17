package timeConverter;

import java.util.Scanner;

public class timeConverter {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the day in seconds: ");
        int days = scanner.nextInt();
        int seconds = days * 24 * 3600;
        System.out.println("The day in seconds is: " + seconds);
    }
}
