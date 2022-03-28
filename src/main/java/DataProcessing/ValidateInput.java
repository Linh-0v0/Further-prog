package DataProcessing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ValidateInput {
    /* Validate input option */
    public int validateIntInput(int[] option, String msg) {
        int opt = 0;
        Scanner input = new Scanner(System.in);
        boolean catchE = false;
        boolean found = false;
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
            for (int o : option) {
                if (o == opt) {
                    found = true;
                    break;
                }
            }
            if (!found) {System.out.println("Invalid input. Please try again!\n");}
        } while (!found || !catchE);
        return opt;
    }
}
