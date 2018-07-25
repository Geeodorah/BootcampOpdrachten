package opdrachten;

import org.assertj.core.api.Assertions;
import org.assertj.core.data.Percentage;
import org.testng.annotations.Test;

import java.awt.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.atIndex;

public class Controles {
    String firstName = "Ben";
    String seperator = "_";
    String lastName = "Brugman";

    float testing = 1.1f;

    @Test
    private void createName() {
        String[] nameList = new String[]{firstName, seperator, lastName};
        String fullName = firstName + " " + lastName;

        assertThat(nameList).contains(" ", atIndex(1));
        System.out.println(fullName);
    }

    @Test
    private void checkNumber(){
        assertThat(testing).isCloseTo(1f,Percentage.withPercentage(20));
    }




}
