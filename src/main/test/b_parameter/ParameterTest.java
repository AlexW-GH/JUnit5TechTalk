package b_parameter;

import com.google.common.base.Strings;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParameterTest {





    @ParameterizedTest(name = "Test if {0} > 0")
    @ValueSource(ints = {1,2,3,4,5})
    void valueTest(int value){
        assertTrue(value > 0);
    }





    @ParameterizedTest(name = "Test if {0} is null or empty")
    @NullAndEmptySource
    //@NullSource
    //@EmptySource
    void emptyAndNullTest(String text){
        assertTrue(Strings.isNullOrEmpty(text), String.format("String '%s' is not null or empty", text));
    }





    private enum Weekday{
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY,
        SUNDAY;

        public int dayNumber(){
            switch(this) {
                case MONDAY: return 1;
                case TUESDAY: return 2;
                case WEDNESDAY: return 3;
                case THURSDAY: return 4;
                case FRIDAY: return 5;
                case SATURDAY: return 6;
                case SUNDAY: return 7;
                default: return Integer.MAX_VALUE;
            }
        }

        public boolean isGood(){
            switch(this) {
                case SATURDAY:
                case SUNDAY:
                    return true;
                default: return false;
            }
        }
    }

    @ParameterizedTest
    @EnumSource(Weekday.class)
    void thereShouldNotBeMoreThen7Days(Weekday weekday){
        assertTrue(weekday.dayNumber() <= 7);
    }





    @ParameterizedTest(name = "Test if {0} is good")
    @EnumSource(value = Weekday.class, names = {"SATURDAY", "SUNDAY"})
    void weekendIsGood(Weekday weekday){
        assertTrue(weekday.isGood());
    }





    @ParameterizedTest(name = "Test if {0}+{1}={2}")
    @CsvSource({"1,9,10", "2,8,10", "3,7,10", "4,6,10", "5,5,10",})
    void csvTest(int left, int right, int result){
        assertEquals(left+right, result);
    }




    static Stream<Arguments> provideForMethodTest(){
        return Stream.of(
                Arguments.of(1,9,10),
                Arguments.of(2,8,10),
                Arguments.of(3,7,10),
                Arguments.of(4,6,10),
                Arguments.of(5,5,10)
        );
    }

    @ParameterizedTest
    @MethodSource("provideForMethodTest")
    void methodTest(int left, int right, int result){
        assertEquals(left+right, result);
    }




    private static class AdditionArgumentProvider implements ArgumentsProvider{

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
            return Stream.of(
                    Arguments.of(1,9,10),
                    Arguments.of(2,8,10),
                    Arguments.of(3,7,10),
                    Arguments.of(4,6,10),
                    Arguments.of(5,5,10)
            );
        }
    }

    @ParameterizedTest
    @ArgumentsSource(AdditionArgumentProvider.class)
    void argumentProviderTest(int left, int right, int result){
        assertEquals(left+right, result);
    }
}
