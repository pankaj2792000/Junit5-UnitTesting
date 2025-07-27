package annotation;

import org.example.Calculator;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RepeatedTestDemoClassTest {


@BeforeAll
    public static void setUpBeforeClass(){
        System.out.println("setUpBeforeClass calling");
    }
    @BeforeEach
    public  void setUp(){
        System.out.println("Before each annotation calling");
    }

    @AfterEach
    public  void tearDown(){
        System.out.println("After each annotation calling");
    }

    @AfterAll
    public static void tearDownAfterClass(){
        System.out.println("tearDownAfterClass calling");
    }

    @RepeatedTest(value = 5,name = RepeatedTest.DISPLAY_NAME_PLACEHOLDER)
    @DisplayName("Addition repeatedly")
    public  void addTest(){
        System.out.println("Addtest is calling");
        Calculator calculator=new Calculator();
       int actualRes= calculator.add(2,3);
       assertEquals(5,actualRes);
    }
}
