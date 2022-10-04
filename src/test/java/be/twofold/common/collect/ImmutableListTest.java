package be.twofold.common.collect;

import com.google.common.collect.testing.*;
import com.google.common.collect.testing.features.*;
import junit.framework.*;

import java.util.*;

public class ImmutableListTest {

    public static Test suite() {
        TestSuite suite = new TestSuite("be.twofold.common.collect.ImmutableListTest");
        suite.addTest(testImmutableListOf());
        return suite;
    }

    public static Test testImmutableListOf() {
        return ListTestSuiteBuilder
            .using(new TestStringListGenerator() {
                @Override
                protected List<String> create(String[] elements) {
                    return ImmutableList.of(elements);
                }
            })
            .named("ImmutableList.of")
            .withFeatures(
                CollectionSize.ANY
            )
            .createTestSuite();
    }

}
