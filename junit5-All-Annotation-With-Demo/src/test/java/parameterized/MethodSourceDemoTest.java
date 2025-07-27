package parameterized;

import org.example.MathUtil;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MethodSourceDemoTest {


    @ParameterizedTest
    @MethodSource("argumentsStream")
    void factorialTest(int number,int expected){
        MathUtil mathUtil=new MathUtil();
        assertEquals(expected,mathUtil.factorial(number));

    }

    public static Stream<Arguments> argumentsStream(){
        return Stream.of(
                Arguments.arguments(0,1),
                Arguments.arguments(1,1),
                Arguments.arguments(2,2),
                Arguments.arguments(3,6)

        );
    }
}
