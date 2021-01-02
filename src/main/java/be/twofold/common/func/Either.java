package be.twofold.common.func;

import java.util.*;

public abstract class Either<L, R> {

    Either() {
    }

    public static <L, R> Either<L, R> left(L left) {
        return new Left<>(left);
    }

    public static <L, R> Either<L, R> right(R right) {
        return new Right<>(right);
    }

    public abstract L getLeft();

    public abstract R getRight();

    public boolean isLeft() {
        return this instanceof Left;
    }

    public boolean isRight() {
        return this instanceof Left;
    }

    static final class Left<L, R> extends Either<L, R> {
        private final L left;

        Left(L left) {
            this.left = left;
        }

        @Override
        public L getLeft() {
            return left;
        }

        @Override
        public R getRight() {
            throw new NoSuchElementException();
        }

        @Override
        public boolean equals(Object obj) {
            return this == obj || obj instanceof Left
                && Objects.equals(left, ((Left<?, ?>) obj).left);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(left);
        }

        @Override
        public String toString() {
            return "Left(" + left + ")";
        }
    }

    static final class Right<L, R> extends Either<L, R> {
        private final R right;

        Right(R right) {
            this.right = right;
        }

        @Override
        public L getLeft() {
            throw new NoSuchElementException();
        }

        @Override
        public R getRight() {
            return right;
        }

        @Override
        public boolean equals(Object obj) {
            return this == obj || obj instanceof Right
                && Objects.equals(right, ((Right<?, ?>) obj).right);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(right);
        }

        @Override
        public String toString() {
            return "Right(" + right + ")";
        }
    }

}
