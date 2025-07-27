package annotation;

import org.example.Calculator;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderCalculatorClass {


    @Order(1)
    @Test
    public  void addTest(){
        Calculator calculator=new Calculator();
                assertEquals(5,calculator.add(2,3));
    }

    @Order(2)
    @Test

    public  void substractTest(){
        Calculator calculator=new Calculator();
        assertEquals(-1,calculator.substract(2,3));
    }

    @Order(3)
    @Test

    public  void multiplyTest(){
        Calculator calculator=new Calculator();
        assertEquals(6,calculator.multiply(2,3));
    }

    @Order(4)
    @Test
    public  void divideTest(){
        Calculator calculator=new Calculator();
        assertEquals(2,calculator.division(6,3));
    }
}
