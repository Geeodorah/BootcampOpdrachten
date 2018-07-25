package opdrachten;

import org.testng.annotations.Test;

import java.util.Random;

public class AboutMethods {
    int outcome;
    String method;

    public int getRandomNumberInRange() {
        Random r = new Random();
        return r.nextInt((4 - 1) + 1) + 1;
    }

    public int multiply(int firstDigit, int secondDigit, int calculation) {
        if (calculation == 1) {
            outcome = firstDigit + secondDigit;
            method = "add";
        } else if (calculation == 2) {
            outcome = firstDigit - secondDigit;
            method = "subtract";
        }else if (calculation == 3) {
            outcome = firstDigit * secondDigit;
            method = "multiply";
        }else if (calculation == 4) {
            outcome = firstDigit / secondDigit;
            method = "divide";
        }
        return outcome;
    }

    public String getMethod(){
        return method;
    }

    @Test
    private void printProduct() {
        System.out.println(multiply(5, 25, getRandomNumberInRange()));
        System.out.println(getMethod());
    }
}
