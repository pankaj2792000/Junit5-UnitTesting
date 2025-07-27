package annotation;

import org.example.Calculator;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Disabled
public class DisabledCalculatorTest {

    @Test
    @Disabled
    void addTest(){
        Calculator calculator=new Calculator();
        int res= calculator.add(10,20);
        assertEquals(30,res);
    }

    @Test
    void substractTest(){
        Calculator calculator=new Calculator();
        int res= calculator.substract(20,10);
        assertEquals(10,res);
    }
}
