package nl.esciencecenter.rocket.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Objects;

public class Correlation<K extends Comparable<? super K>, R> implements Serializable, Comparable<Correlation<K, R>> {
    private static final long serialVersionUID = -1044784217213847480L;
    private static final InternPool<Object> POOL = new InternPool();

    private K i;
    private K j;
    private R coefficient;

    public Correlation(K i, K j, R coefficient) {
        this.i = i;
        this.j = j;
        this.coefficient = coefficient;
    }

    public K getI() {
        return i;
    }

    public K getJ() {
        return j;
    }

    public R getCoefficient() {
        return coefficient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Correlation<?, ?> that = (Correlation<?, ?>) o;
        return Objects.equals(i, that.i) &&
                Objects.equals(j, that.j) &&
                Objects.equals(coefficient, that.coefficient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(i, j, coefficient);
    }

    @Override
    public String toString() {
        return "Correlation{" +
                "i=" + i +
                ", j=" + j +
                ", coefficient=" + coefficient +
                '}';
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();

        synchronized (POOL) {
            i = (K) POOL.intern(i);
            j = (K) POOL.intern(j);
        }
    }

    @Override
    public int compareTo(Correlation<K, R> that) {
        int c = this.i.compareTo(that.i);
        if (c == 0) c = this.j.compareTo(that.j);
        return c;
    }
}

