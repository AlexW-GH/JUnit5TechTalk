package c_dynamic;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

public class DynamicCreatedTest {

    private int[] sequence = {1,1,2,3,5,8,13,21};

    @TestFactory
    public List<DynamicTest> exampleTestFactory() {
        ArrayList<DynamicTest> tests = Lists.newArrayList();
        for(int i=2; i< sequence.length; ++i){
            int left = sequence[i-2];
            int right = sequence[i-1];
            int result = sequence[i];
            tests.add(
                    dynamicTest(
                            String.format("%s + %s = %s", left, right, result),
                            () -> assertEquals(result, left + right))
            );
        }
        return tests;
    }
}
