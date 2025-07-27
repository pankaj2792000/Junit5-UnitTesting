package annotation;

import org.example.Calculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BeforeEachDemo {

    //instance of calculator class is repeated in all test cases
private  Calculator calculator;
    @BeforeEach
    void setUp(){
     calculator=new Calculator();
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
