package hotWheels;

public class Auto {

    public void setBrand(String brand) {
        System.out.println("The car is of the brand " + brand);
    }

    public void setColour(String colour) {
        System.out.println("This car is the colour " + colour + " super nice!");
    }

    public void setDoors(int amountOfDoors) {
        System.out.println("This car has " + amountOfDoors + " doors");
    }

    public void setEngine(String type, int force, int rpm) {
        System.out.println("This car has a " + type + " engine, and has a torque of " + getCalculateTorque(force, rpm));
    }

    public int getCalculateTorque(int force, int rpm) {
        return (force * 5252) / rpm;

    }

}
