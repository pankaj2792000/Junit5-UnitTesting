package annotation;

import org.example.Calculator;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Calculator Test Operations")
class CalculatorTest {


    @Test
    @DisplayName("Test addition of two number")
   void addTest(){
        Calculator calculator=new Calculator();
       int res= calculator.add(10,20);
       assertEquals(30,res);
    }

    @Test
    @DisplayName("Test Substraction of two number")
    void substractTest(){
        Calculator calculator=new Calculator();
        int res= calculator.substract(20,10);
        assertEquals(10,res);
    }
}