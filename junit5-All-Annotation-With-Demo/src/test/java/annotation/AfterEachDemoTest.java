package annotation;

import org.example.Calculator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AfterEachDemoTest {

    private Calculator calculator;
    @BeforeEach
    void setUp(){
        System.out.println("BeforeEach called");
        calculator=new Calculator();
    }

    @AfterEach
    void tearDown(){
//we can assign null to calculator and it can be reassing by before each
        calculator=null;
        System.out.println("AfterEach called");
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
