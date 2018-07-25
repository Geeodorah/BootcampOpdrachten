package opdrachten;


import org.testng.annotations.Test;

public class HelloWorldTest extends AboutMethods{
//    AboutMethods aboutMethods = new AboutMethods();

    @Test
    public void printText() {
        System.out.println("Hey Harry");
    }

    @Test
    public void testTwo(){
        System.out.println(multiply(12,6,getRandomNumberInRange()));
        System.out.println(getMethod());
    }
}
