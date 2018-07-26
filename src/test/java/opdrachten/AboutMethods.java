package opdrachten;

import org.testng.annotations.Test;

import java.util.Random;

public class AboutMethods extends Parameters {
    int outcome;
    String method;

    public int getRandomNumberInRange(int variant) {
        Random r = new Random();
        int valueToReturn = 0;
        if (variant == 1) {
            valueToReturn = r.nextInt((4 - 1) + 1) + 1;
        } else if (variant == 2) {
            valueToReturn = r.nextInt((100 - 1) + 1) + 1;
        }
        return valueToReturn;
    }


    public int multiply(int firstDigit, int secondDigit, int calculation) {
        if (calculation == 1) {
            outcome = firstDigit + secondDigit;
            method = "add";
        } else if (calculation == 2) {
            outcome = firstDigit - secondDigit;
            method = "subtract";
        } else if (calculation == 3) {
            outcome = firstDigit * secondDigit;
            method = "multiply";
        } else if (calculation == 4) {
            outcome = firstDigit / secondDigit;
            method = "divide";
        }
        return outcome;
    }

    public String getMethod() {
        return method;
    }

    @Test
    private void printProduct() {
        int firstNumber = getRandomNumberInRange(RANDOM_NUMBER);
        int secondNumber = getRandomNumberInRange(RANDOM_NUMBER);
        System.out.println(multiply(firstNumber, secondNumber,
                getRandomNumberInRange(RANDOM_MATH_METHOD)));
        System.out.println("Method " + getMethod() + " First number " + firstNumber + " Second number " + secondNumber );
    }
}
