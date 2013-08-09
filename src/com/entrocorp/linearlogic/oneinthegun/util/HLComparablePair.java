package com.entrocorp.linearlogic.oneinthegun.util;

public class HLComparablePair<X extends Comparable<X>, Y extends Comparable<Y>> extends Pair<X, Y>
        implements Comparable<HLComparablePair<X, Y>> {

    public HLComparablePair(X x, Y y) {
        super(x, y);
    }

    /**
     * Determines whether the HLComparablePair should precede (-1 is returned) or succeed (1 is returned) another pair
     * in an ordered collection. The x values are checked first; as this is a high-low comparison, the pair with the
     * higher x value will come first. If the x values are the same, the y values are compared: this time, the pair with
     * the <i>lower</i> y value will come first.
     * <p>
     * <b>Note: </b>If the y values are also equivalent, the returned value is 1, not 0. This is to enable the contents
     * of {@link TriMap TriMaps} to be sorted based on value pairs.
     */
    @Override
    public int compareTo(HLComparablePair<X, Y> other) {
        int comp = x.compareTo(other.x);
        return comp != 0 ? (int) -Math.signum(comp) : (int) Math.signum(y.compareTo(other.y) < 0 ? -1 : 1);
    }
}
