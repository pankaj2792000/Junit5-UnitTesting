package annotation;

import org.example.Calculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NestedCalculatorTest {
//we will share this varaible for all the nested class
    private Calculator calculator;

    //this method will be called before each
    // test method even for nested class test method

    @BeforeEach
    public void setup(){
        calculator=new Calculator();
    }

    @Nested
    class  AdditionTest{

        @Test
        public  void addPositiveNumberTest(){
            assertEquals(5,calculator.add(2,3));
        }

        @Test
        public  void addNegativeNumberTest(){
            assertEquals(-5,calculator.add(-2,-3));
        }

        @Test
        public  void addPositivieAndNegativeNumberTest(){
            assertEquals(-1,calculator.add(2,-3));
        }

    }

    @Nested
    class SubstractionTest{

        @Test
        public  void substractPositiveNumberTest(){
            assertEquals(-1,calculator.substract(2,3));
        }

        @Test
        public  void  substractNegativeNumberTest(){
            assertEquals(1,calculator.substract(-2,-3));
        }

        @Test
        public  void  substractPositivieAndNegativeNumberTest(){
            assertEquals(8,calculator.substract(5,-3));
        }

        @Nested
        class EdgeCase{

            @Test
            public void substractZero() {

                assertEquals(5, calculator.substract(5, 0));
            }

        }
    }
}
