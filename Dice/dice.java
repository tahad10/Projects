package Dice;
import java.util.Random;

public class dice {
    /**
     * main method which generates a random number between 1 and 6
     * @param args
     */
    public static void main(String[] args) {
        //for (int i = 0; i < 10; i++) {
            Random random = new Random();
            int dice = random.nextInt(6) + 1;
            System.out.println(dice);
       // }
    }
}
