package be.twofold.common.collect;

import com.google.common.collect.testing.*;
import com.google.common.collect.testing.features.*;
import junit.framework.*;

import java.util.*;

public class ImmutableMapTest {

    public static Test suite() {
        TestSuite suite = new TestSuite("be.twofold.common.collect.ImmutableMapTest");
        suite.addTest(testImmutableMapOfEntries());
        return suite;
    }

    public static Test testImmutableMapOfEntries() {
        return MapTestSuiteBuilder
            .using(new TestStringMapGenerator() {
                @Override
                protected Map<String, String> create(Map.Entry<String, String>[] entries) {
                    return ImmutableMap.ofEntries(entries);
                }
            })
            .named("ImmutableMap.ofEntries")
            .withFeatures(
                CollectionSize.ANY
            )
            .createTestSuite();
    }

}
