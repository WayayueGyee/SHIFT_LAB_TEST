package com.example.cft_test.util;

public record Pair<A, B>(A a, B b) {
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        @SuppressWarnings("rawtypes")
        Pair other = (Pair) obj;
        if (a == null) {
            if (other.a != null) {
                return false;
            }
        } else if (!a.equals(other.a)) {
            return false;
        }

        if (b == null) {
            return other.b == null;
            
        } else return b.equals(other.b);
    }

    public boolean isInstance(Class<?> classA, Class<?> classB) {
        return classA.isInstance(a) && classB.isInstance(b);
    }

    @SuppressWarnings("unchecked")
    public static <P, Q> Pair<P, Q> cast(Pair<?, ?> pair, Class<P> pClass, Class<Q> qClass) {
        if (pair.isInstance(pClass, qClass)) {
            return (Pair<P, Q>) pair;
        }

        throw new ClassCastException();
    }

}