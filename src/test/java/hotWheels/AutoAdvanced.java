package hotWheels;

import java.security.PublicKey;

public class AutoAdvanced {
    public String brand;

    public AutoAdvanced(String brand, int force, int rpm) {
        this.brand = brand;
        int torque = getCalculateTorque(force, rpm);
        System.out.println("Brand of the car is " + brand + ", woah! this car has some torque: " + torque);
    }


    public int getCalculateTorque(int force, int rpm) {
        return (force * 5252) / rpm;

    }
}
