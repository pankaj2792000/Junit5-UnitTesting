package parameterized;

import org.example.MathUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class ValueSourceDemoTest {

    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5})
    void isEvenTest(int number){
        MathUtil mathUtil=new MathUtil();
        assertTrue(mathUtil.isEven(number));

    }

    @ParameterizedTest
    @ValueSource(strings = {"Java","c"})

    public void nameTest(String param){
        assertNotNull(param);
    }
}
