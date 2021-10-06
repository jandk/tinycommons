package be.twofold.common;

import com.squareup.javapoet.*;

import javax.lang.model.element.*;
import java.io.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public final class ArrayUtilsGenerator {

    private static final ClassName CHECK = ClassName.get("be.twofold.common", "Check");

    public static void main(String[] args) throws IOException {
        MethodSpec constructor = MethodSpec.constructorBuilder()
            .addModifiers(Modifier.PRIVATE)
            .addStatement("throw new UnsupportedOperationException()")
            .build();

        TypeSpec.Builder arrayUtils = TypeSpec.classBuilder("ArrayUtils")
            .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
            .addMethod(constructor);

        Stream.<Function<Primitive, MethodSpec>>of(
            ArrayUtilsGenerator::generateContains,
            ArrayUtilsGenerator::generateIndexOf,
            ArrayUtilsGenerator::generateIndexOfFromTo,
            ArrayUtilsGenerator::generateLastIndexOf,
            ArrayUtilsGenerator::generateLastIndexOfFromTo,
            ArrayUtilsGenerator::generateReverse,
            ArrayUtilsGenerator::generateReverseFromTo,
            ArrayUtilsGenerator::generateEquals,
            ArrayUtilsGenerator::generateHashCode,
            ArrayUtilsGenerator::generateToString
        ).forEach(generator -> generateForPrimitives(arrayUtils, generator));

        JavaFile javaFile = JavaFile.builder("be.twofold.common", arrayUtils.build())
            .skipJavaLangImports(true)
            .indent("    ")
            .build();

        javaFile.writeTo(System.out);
    }

    private static void generateForPrimitives(TypeSpec.Builder arrayUtils, Function<Primitive, MethodSpec> generator) {
        Arrays.stream(Primitive.values())
            .map(generator)
            .forEach(arrayUtils::addMethod);
    }

    private static MethodSpec generateContains(Primitive primitive) {
        return MethodSpec.methodBuilder("contains")
            .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
            .addParameter(primitive.arrayType, "array")
            .addParameter(primitive.primitiveType, "value")
            .returns(boolean.class)
            .addStatement("return indexOf(array, value) >= 0")
            .build();
    }

    private static MethodSpec generateIndexOf(Primitive primitive) {
        return MethodSpec.methodBuilder("indexOf")
            .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
            .addParameter(primitive.arrayType, "array")
            .addParameter(primitive.primitiveType, "value")
            .returns(int.class)
            .addStatement("return indexOf(array, value, 0, array.length)")
            .build();
    }

    private static MethodSpec generateIndexOfFromTo(Primitive primitive) {
        return MethodSpec.methodBuilder("indexOf")
            .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
            .addParameter(primitive.arrayType, "array")
            .addParameter(primitive.primitiveType, "value")
            .addParameter(int.class, "fromIndex")
            .addParameter(int.class, "toIndex")
            .returns(int.class)
            .addStatement("$T.positions(fromIndex, toIndex, array.length)", CHECK)
            .addCode("\n")
            .beginControlFlow("for (int i = fromIndex; i < toIndex; i++)")
            .beginControlFlow("if (" + generateComparison("array[i]", "value", primitive) + ")")
            .addStatement("return i")
            .endControlFlow()
            .endControlFlow()
            .addStatement("return -1")
            .build();
    }

    private static MethodSpec generateLastIndexOf(Primitive primitive) {
        return MethodSpec.methodBuilder("lastIndexOf")
            .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
            .addParameter(primitive.arrayType, "array")
            .addParameter(primitive.primitiveType, "value")
            .returns(int.class)
            .addStatement("return lastIndexOf(array, value, 0, array.length)")
            .build();
    }

    private static MethodSpec generateLastIndexOfFromTo(Primitive primitive) {
        return MethodSpec.methodBuilder("lastIndexOf")
            .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
            .addParameter(primitive.arrayType, "array")
            .addParameter(primitive.primitiveType, "value")
            .addParameter(int.class, "fromIndex")
            .addParameter(int.class, "toIndex")
            .returns(int.class)
            .addStatement("$T.positions(fromIndex, toIndex, array.length)", CHECK)
            .addCode("\n")
            .beginControlFlow("for (int i = toIndex - 1; i >= fromIndex; i--)")
            .beginControlFlow("if (" + generateComparison("array[i]", "value", primitive) + ")")
            .addStatement("return i")
            .endControlFlow()
            .endControlFlow()
            .addStatement("return -1")
            .build();
    }

    private static MethodSpec generateReverse(Primitive primitive) {
        return MethodSpec.methodBuilder("reverse")
            .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
            .addParameter(primitive.arrayType, "array")
            .addStatement("reverse(array, 0, array.length)")
            .build();
    }

    private static MethodSpec generateReverseFromTo(Primitive primitive) {
        return MethodSpec.methodBuilder("reverse")
            .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
            .addParameter(primitive.arrayType, "array")
            .addParameter(int.class, "fromIndex")
            .addParameter(int.class, "toIndex")
            .addStatement("$T.positions(fromIndex, toIndex, array.length)", CHECK)
            .addCode("\n")
            .beginControlFlow("for (int i = fromIndex, j = toIndex - 1; i < j; i++, j--)")
            .addStatement("$T temp = array[i]", primitive.primitiveType)
            .addStatement("array[i] = array[j]")
            .addStatement("array[j] = temp")
            .endControlFlow()
            .build();
    }

    private static MethodSpec generateEquals(Primitive primitive) {
        return MethodSpec.methodBuilder("equals")
            .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
            .addParameter(primitive.arrayType, "a")
            .addParameter(int.class, "aFromIndex")
            .addParameter(int.class, "aToIndex")
            .addParameter(primitive.arrayType, "b")
            .addParameter(int.class, "bFromIndex")
            .addParameter(int.class, "bToIndex")
            .returns(boolean.class)
            .addStatement("$T.positions(aFromIndex, aToIndex, a.length)", CHECK)
            .addStatement("$T.positions(bFromIndex, bToIndex, b.length)", CHECK)
            .addCode("\n")
            .addStatement("int aLength = aToIndex - aFromIndex")
            .addStatement("int bLength = bToIndex - bFromIndex")
            .beginControlFlow("if (aLength != bLength)")
            .addStatement("return false")
            .endControlFlow()
            .addCode("\n")
            .beginControlFlow("for (int i = 0; i < aLength; i++)")
            .beginControlFlow("if (" + generateComparison("a[aFromIndex + i]", "b[bFromIndex + i]", primitive) + ")")
            .addStatement("return false")
            .endControlFlow()
            .endControlFlow()
            .addStatement("return true")
            .build();
    }

    private static MethodSpec generateHashCode(Primitive primitive) {
        return MethodSpec.methodBuilder("hashCode")
            .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
            .addParameter(primitive.arrayType, "array")
            .addParameter(int.class, "fromIndex")
            .addParameter(int.class, "toIndex")
            .returns(int.class)
            .addStatement("$T.positions(fromIndex, toIndex, array.length)", CHECK)
            .addCode("\n")
            .addStatement("int result = 1")
            .beginControlFlow("for (int i = fromIndex; i < toIndex; i++)")
            .addStatement("result = 31 * result + $T.hashCode(array[i])", primitive.objectType)
            .endControlFlow()
            .addStatement("return result")
            .build();
    }

    private static MethodSpec generateToString(Primitive primitive) {
        return MethodSpec.methodBuilder("toString")
            .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
            .addParameter(primitive.arrayType, "array")
            .addParameter(int.class, "fromIndex")
            .addParameter(int.class, "toIndex")
            .returns(String.class)
            .addStatement("$T.positions(fromIndex, toIndex, array.length)", CHECK)
            .beginControlFlow("if (fromIndex == toIndex)")
            .addStatement("return $S", "[]")
            .endControlFlow()
            .addCode("\n")
            .addStatement("$1T builder = new $1T()", StringBuilder.class)
            .addStatement("builder.append($S).append(array[fromIndex])", "[")
            .beginControlFlow("for (int i = fromIndex + 1; i < toIndex; i++)")
            .addStatement("builder.append($S).append(array[i])", ", ")
            .endControlFlow()
            .addStatement("return builder.append($S).toString()", "]")
            .build();
    }

    private static String generateComparison(String left, String right, Primitive primitive) {
        switch (primitive) {
            case FLOAT:
                return "Float.compare(" + left + ", " + right + ") == 0";
            case DOUBLE:
                return "Double.compare(" + left + ", " + right + ") == 0";
            default:
                return left + " == " + right;
        }
    }

    private enum Primitive {
        BYTE(byte.class, Byte.class, byte[].class),
        SHORT(short.class, Short.class, short[].class),
        INT(int.class, Integer.class, int[].class),
        LONG(long.class, Long.class, long[].class),
        FLOAT(float.class, Float.class, float[].class),
        DOUBLE(double.class, Double.class, double[].class),
        CHAR(char.class, Character.class, char[].class),
        BOOLEAN(boolean.class, Boolean.class, boolean[].class);

        private final Class<?> primitiveType;
        private final Class<?> objectType;
        private final Class<?> arrayType;

        Primitive(Class<?> primitiveType, Class<?> objectType, Class<?> arrayType) {
            this.primitiveType = primitiveType;
            this.objectType = objectType;
            this.arrayType = arrayType;
        }

        public boolean isIntegral() {
            return this == BYTE || this == SHORT || this == INT || this == LONG;
        }

        public boolean isFloating() {
            return this == FLOAT || this == DOUBLE;
        }
    }

}
