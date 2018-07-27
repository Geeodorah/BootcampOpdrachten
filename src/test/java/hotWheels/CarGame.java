package hotWheels;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CarGame {

    @Test
    public void testAuto() {
        Auto auto = new Auto();
        auto.setColour("Purple");
        auto.setBrand("Yugo");
        auto.setDoors(3);
        auto.setEngine("Chrysler", 120, 8000);
    }

    @Test
    public void testAdvancedAuto() {
        AutoAdvanced autoAdvanced = new AutoAdvanced("Yugo", 150, 3000);
    }
}
