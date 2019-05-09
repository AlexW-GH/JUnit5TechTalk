package a_extension;

import a_extension.extension.ExampleParameterExtension;
import a_extension.extension.ExampleParameterExtension.Value;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InjectionTest {

    @Test
    @DisplayName("Short Test")
    @ExtendWith(ExampleParameterExtension.class)
    public void shortParam(@Value short value){
        assertEquals(-1, value);
    }

    @Test
    @DisplayName("Int Test")
    @ExtendWith(ExampleParameterExtension.class)
    public void intParam(@Value int value){
        assertEquals(0, value);
    }

    @Test
    @DisplayName("Long Test")
    @ExtendWith(ExampleParameterExtension.class)
    public void longParam(@Value long value){
        assertEquals(1, value);
    }



    @Nested
    @DisplayName("Different Tests 😱")
    @ExtendWith(ExampleParameterExtension.class)
    public class NestedTest{

        @Test
        public void differentTest(@Value short value1, @Value long value2){
            assertEquals(value1, value2);
        }

        @RepeatedTest(value = 5, name = "{displayName} Versuch {currentRepetition}/{totalRepetitions}")
                public void tryFiveTimes(@Value short value1, @Value long value2){
            assertTrue(value1 + value2 == 0);
        }
    }

}
