package be.twofold.common.collect;

import com.google.common.collect.testing.*;
import com.google.common.collect.testing.features.*;
import junit.framework.*;

import java.util.*;

public class ImmutableSetTest {

    public static Test suite() {
        TestSuite suite = new TestSuite("be.twofold.common.collect.ImmutableSetTest");
        suite.addTest(testImmutableSetOf());
        return suite;
    }

    public static Test testImmutableSetOf() {
        return SetTestSuiteBuilder
            .using(new TestStringSetGenerator() {
                @Override
                protected Set<String> create(String[] elements) {
                    return ImmutableSet.of(elements);
                }
            })
            .named("ImmutableSet.of")
            .withFeatures(
                CollectionSize.ANY,
                CollectionFeature.REJECTS_DUPLICATES_AT_CREATION
            )
            .createTestSuite();
    }

}
