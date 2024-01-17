package com.mindflytech.education.immutability;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class HashMapMutableKeyScrewUpExample {
    private static final int ORIGINAL_MAP_SIZE = 10;
    private static final int SHIFT_TIMES = 10;

    public static void main(String[] args) {
        HashMapMutableKeyScrewUpExample mutableKey = new HashMapMutableKeyScrewUpExample();
        mutableKey.runExample();
    }

    private void runExample() {
        Map<MutableKey, String> map = new HashMap<>();
        for(int i = 0; i < SHIFT_TIMES; i++) {
            fillInMap(map);
            System.out.println("-- map filled in: " + stringifyMap(map));
            shiftMap(map);
            System.out.println("=========================================================================");
            System.out.println("-- map after shift, map size: " + map.keySet().size() + ", map: " + stringifyMap(map));
            System.out.println("=========================================================================");
        }
    }

    private void fillInMap(Map<MutableKey, String> map) {
        for(int i = 0; i < ORIGINAL_MAP_SIZE; i++) {
            String value = "generated_value_" + System.currentTimeMillis();
            map.put(MutableKey.of(i), value);
        }
    }

    private String stringifyMap(Map<MutableKey, String> map) {
        String toString = map.entrySet()
                .stream().map((entry) -> {
                    MutableKey key = entry.getKey();
                    String value = entry.getValue();
                    return key.toString() + "=" + value;
                })
                .collect(Collectors.joining(";\n\t"));
        return "[" + toString + "]";
    }

    private void shiftMap(Map<MutableKey, String> map) {
        for(MutableKey key : map.keySet()) {
            int original = key.getKey();
            int shifted = original + ORIGINAL_MAP_SIZE;
            key.setKey(shifted);
        }
    }
}

class MutableKey {
    private int key;

    public MutableKey(int key) {
        this.key = key;
    }

    public static MutableKey of(int key) {
        return new MutableKey(key);
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MutableKey that = (MutableKey) o;
        return key == that.key;
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }

    @Override
    public String toString() {
        return "MutableKey{" +
                "key=" + key +
                '}';
    }
}

final class ClonableObject implements Cloneable {
    public ClonableObject copy() {
        try {
            return (ClonableObject) this.clone();
        } catch (CloneNotSupportedException e) {
            System.err.println("Clone not supported!");
            throw new RuntimeException(e);
        }
    }
}

final class ImmutableKey {
    private final ClonableObject key;

    ImmutableKey(ClonableObject key) {
        ClonableObject copyKey = key.copy();
        this.key = copyKey;
    }

    public ClonableObject getKey() {
        return key.copy();
    }
}