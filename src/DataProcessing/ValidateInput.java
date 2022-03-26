package DataProcessing;

import java.util.Arrays;
import java.util.Scanner;

public class ValidateInput {
    public int validateIntInput(int[] option, String msg) {
        int opt = 0;
        Scanner input = new Scanner(System.in);
        boolean catchE = false;
        do {
            System.out.print(msg);
            try {
                opt = input.nextInt();
                catchE = true;
            } catch (Exception e) {
                catchE = false;
                System.out.println("Invalid input. Please try again!\n");
                input.nextLine(); //stop looping infinitely and wait for input
            }

            if (!Arrays.asList(option).contains(opt)) {
                System.out.println("Invalid input! Please try again!\n");
            }
        } while (!Arrays.asList(option).contains(opt) || !catchE);
        return opt;
    }
}
