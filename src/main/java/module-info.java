module tinycommons {
    requires java.base;

    // Only used by the ArrayUtilsGenerator in test
    requires java.compiler;

    exports be.twofold.common;
    exports be.twofold.common.func;
    exports be.twofold.common.func.fi;
    exports be.twofold.common.seq;
    exports be.twofold.common.text;
    exports be.twofold.common.tuple;
}
