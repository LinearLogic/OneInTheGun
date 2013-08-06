package com.entrocorp.linearlogic.oneinthechamber.util;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

public class TriMap<K, X, Y> {

    private HashMap<K, Pair<X, Y>> map = new HashMap<K, Pair<X, Y>>();

    public Set<K> keySet() {
        return map.keySet();
    }

    public X getX(K key) {
        return map.get(key).getX();
    }

    @SuppressWarnings("unchecked")
    public X[] getXValues() {
        X[] output = (X[]) new Object[map.size()];
        int index = 0;
        for (Pair<X, Y> p : map.values())
            output[index++] = p.getX();
        return output;
    }

    public boolean setX(K key, X x) {
        if (!map.containsKey(key))
            return false;
        map.get(key).setX(x);
        return true;
    }

    public Y getY(K key) {
        return map.get(key).getY();
    }

    @SuppressWarnings("unchecked")
    public Y[] getYValues() {
        Y[] output = (Y[]) new Object[map.size()];
        int index = 0;
        for (Pair<X, Y> p : map.values())
            output[index++] = p.getY();
        return output;
    }

    public boolean setY(K key, Y y) {
        if (!map.containsKey(key))
            return false;
        map.get(key).setY(y);
        return true;
    }

    public Pair<X, Y> getPair(K key) {
        return map.get(key);
    }

    @SuppressWarnings("unchecked")
    public Pair<X, Y>[] getPairs() {
        return map.values().toArray((Pair<X, Y>[]) new Object[map.size()]);
    }

    public Set<Entry<K, Pair<X, Y>>> entrySet() {
        return map.entrySet();
    }

    public boolean containsKey(K key) {
        return map.containsKey(key);
    }

    public Pair<X, Y> put(K key, X x, Y y) {
        return map.put(key, new Pair<X, Y>(x, y));
    }

    public Pair<X, Y> remove(K key) {
        return map.remove(key);
    }

    public void clear() {
        map.clear();
    }

    public int size() {
        return map.size();
    }
}
