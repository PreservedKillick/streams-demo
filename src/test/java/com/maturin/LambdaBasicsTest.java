package com.maturin;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LambdaBasicsTest {

//    public interface Comparator {
//        public int commpare(String a, String b);
//    }

    @Test
    public void badOldDays() {
        List<String> names = Arrays.asList("joe", "sam", "amy", "zeus");

        names.sort(new ForRealComparator());
        System.out.println(names);
    }

    public class ForRealComparator implements Comparator<String> {

        @Override
        public int compare(String thing1, String thing2) {
            // #compareTo returns 0 if the thing2 is equal to thing1,
            // a value less than 0 if thing1 is lexigraphically less than thing2,
            // and a value greater than 0 if thing1 is greater than thing 2
            return thing1.compareTo(thing2);
        }
    }

    @Test
    public void java7Days() {
        // use an anonymous inner class to avoid having to create an entire class just to define one lil method that you only need to use once

        List<String> names = Arrays.asList("joe", "sam", "amy", "zeus");
        names.sort(new Comparator<String>() {
            @Override
            public int compare(String thing1, String thing2) {
                return thing1.compareTo(thing2);
            }
        });

        System.out.println(names);
    }

    @Test
    public void helloLambda() {
        List<String> names = Arrays.asList("joe", "sam", "amy", "zeus");

        names.sort((String thing1, String thing2) -> {
            return thing1.compareTo(thing2);
        });

        System.out.println(names);
    }

    @Test
    public void helloLambdaShorter() {
        // get rid of the curly braces and return statement when it's a one line method

        List<String> names = Arrays.asList("joe", "sam", "amy", "zeus");

        names.sort((String thing1, String thing2) -> thing1.compareTo(thing2));

        System.out.println(names);
    }

    @Test
    public void helloLambdaEvenShorter() {
        List<String> names = Arrays.asList("joe", "sam", "amy", "zeus");

        names.sort((thing1, thing2) -> thing1.compareTo(thing2));

        System.out.println(names);
    }

    @Test
    public void helloLambdaShortest() {
        List<String> names = Arrays.asList("joe", "sam", "amy", "zeus");

        names.sort(String::compareTo);

        System.out.println(names);
    }
}
