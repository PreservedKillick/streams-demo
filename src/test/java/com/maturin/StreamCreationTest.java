package com.maturin;

import com.google.common.base.MoreObjects;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamCreationTest {

    private enum BrakeType {
        caliper,
        disc;
    }

    private static class TestBike {

        int numberOfWheels;
        BrakeType brakes;

        private TestBike(int numberOfWheels, BrakeType brakes) {
            this.numberOfWheels = numberOfWheels;
            this.brakes = brakes;
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                .add("numberOfWheels", numberOfWheels)
                .add("brakes", brakes)
                .toString();
        }
    }

    @Test
    public void streamCreation() {
        List<String> coffeeDrinks = Arrays.asList("americano", "cappuccino", "espresso", "latte");
        Stream<String> coffeeStream = coffeeDrinks.stream();

        Stream<String> teaStream = Stream.of("hibiscus", "green", "oolong");

        // really good for creating utils to generate a bunch of test data
        List<TestBike> bikes = Stream
            // intermediate
            .generate(() -> new TestBike(2, ThreadLocalRandom.current().nextBoolean() ? BrakeType.caliper : BrakeType.disc))
            // intermediate.  max number of elements the stream should be limited to
            .limit(30)
            // terminal.  returns the Stream of bikes collected into a List of bikes
            .collect(Collectors.toList());

        System.out.println(bikes);
    }
}
