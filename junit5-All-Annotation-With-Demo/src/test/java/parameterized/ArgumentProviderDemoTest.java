package parameterized;

import org.example.MathUtil;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArgumentProviderDemoTest {


    @ParameterizedTest
    @ArgumentsSource(ArgumentsProviderclass.class)
    public  void factorial(int number ,int expected){

        assertEquals(expected,new MathUtil().factorial(number));

    }
}
