package annotation;

import org.example.Calculator;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AfterAllDemoTest {

    private static Calculator calculator;
    @BeforeAll
    public static void setUpBforeClass(){
        System.out.println("before all is calling");
        calculator=new Calculator();
    }
    @AfterAll
    public static void setUpAfterClass(){
        System.out.println("After all is calling");

        //calculator is shared resources so we can clean up
        //see before all we are sharing it with every method
        calculator=null;
    }
    @Test
    void addTest(){
        // Calculator calculator=new Calculator();
        int res= calculator.add(10,20);
        assertEquals(30,res);
    }

    @Test
    void substractTest(){
        // Calculator calculator=new Calculator();
        int res= calculator.substract(20,10);
        assertEquals(10,res);
    }

    @Test
    void divideTest(){
        //  Calculator calculator=new Calculator();
        int res= calculator.division(20,10);
        assertEquals(2,res);
    }

    @Test
    void multiplyTest(){
        //  Calculator calculator=new Calculator();
        int res= calculator.multiply(20,10);
        assertEquals(200,res);
    }
}
