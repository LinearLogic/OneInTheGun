package com.entrocorp.linearlogic.oneinthegun.util;

public class HLComparablePair<X extends Comparable<X>, Y extends Comparable<Y>> extends Pair<X, Y>
        implements Comparable<HLComparablePair<X, Y>> {

    public HLComparablePair(X x, Y y) {
        super(x, y);
    }

    @Override
    public int compareTo(HLComparablePair<X, Y> other) {
        int comp = x.compareTo(other.x);
        return comp != 0 ? (int) -Math.signum(comp) : (int) Math.signum(y.compareTo(other.y));
    }
}
