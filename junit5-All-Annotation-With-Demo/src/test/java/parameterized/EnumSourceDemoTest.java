package parameterized;

import org.example.Days;
import org.example.MathUtil;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EnumSourceDemoTest {

    @ParameterizedTest
    @EnumSource(value = Days.class,names = {"MONDAY","TUESDAY","WEDNESDAY","THURSDAY","FRIDAY"})
    public  void isWeekDay(Days day){
        MathUtil mathUtil=new MathUtil();
        assertTrue(mathUtil.isWeekDay(day));


    }
}
