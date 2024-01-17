package com.mindflytech.education.datastructure;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MapForEachModificationTest {
    public static void main(String[] args) {
        MapForEachModificationTest runner = new MapForEachModificationTest();
        runner.test();
    }

    private void test() {
        givenIterator_whenRemoveWorks_thenCorrect();
        givenforEach_whenRemoveWorks_thenCorrect();
    }

    //This method is from https://www.baeldung.com/java-hashmap-advanced
    public void givenIterator_whenRemoveWorks_thenCorrect() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "baeldung");
        map.put("type", "blog");

        Set<String> keys = map.keySet();
        Iterator<String> it = keys.iterator();

        while (it.hasNext()) {
            it.next();
            it.remove();
        }

        System.out.println("size of the map: " + map.size() + ", expected: 0");
    }

    //let's see the same with forEach loop
    /*
        Exception in thread "main" java.util.ConcurrentModificationException
        at java.base/java.util.HashMap$HashIterator.nextNode(HashMap.java:1605)
        at java.base/java.util.HashMap$KeyIterator.next(HashMap.java:1628)
        at com.mindflytech.education/com.mindflytech.education.datastructure.MapForEachModificationTest.givenforEach_whenRemoveWorks_thenCorrect(MapForEachModificationTest.java:45)
        at com.mindflytech.education/com.mindflytech.education.datastructure.MapForEachModificationTest.test(MapForEachModificationTest.java:16)
        at com.mindflytech.education/com.mindflytech.education.datastructure.MapForEachModificationTest.main(MapForEachModificationTest.java:11)

        Java HashMap uses Iterator under the hood, but I did not use the iterator's remove method, therefore it threw an exception
     */
    public void givenforEach_whenRemoveWorks_thenCorrect() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "baeldung");
        map.put("type", "blog");

        Set<String> keys = map.keySet();

        for(String key : keys) {
            map.remove(key);
        }

        System.out.println("size of the map: " + map.size() + ", expected: 0");
    }
}
