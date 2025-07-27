package parameterized;

import org.example.MathUtil;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CSVSourceDemoTest {

    @ParameterizedTest
    @CsvSource({"0,1",
            "1,1",
            "2,2",
            "3,6",
            "4,24"
    })
    public void factorial(int number ,int expected){
        MathUtil mathUtil=new MathUtil();

        assertEquals(expected,mathUtil.factorial(number));

    }


    @ParameterizedTest
   @CsvFileSource(resources = "/factorialdata.csv")
    public void factorialTest(int number ,int expected){
        MathUtil mathUtil=new MathUtil();

        assertEquals(expected,mathUtil.factorial(number));

    }
}
