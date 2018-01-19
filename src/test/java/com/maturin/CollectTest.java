package com.maturin;

import static com.maturin.Bike.BrakeType.caliper;
import static com.maturin.Bike.BrakeType.cantilever;
import static com.maturin.Bike.BrakeType.disc;

import com.maturin.Bike.BrakeType;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class CollectTest {

    List<Bike> bikes = Arrays.asList(
        new Bike("Sam", 1, "green", disc),
        new Bike("Leroy", 2, "blue", caliper),
        new Bike("Nadiya", 2, "blue", caliper),
        new Bike("Mary", 2, "red", cantilever),
        new Bike("Tamal", 3, "green", caliper)
    );

    @Test
    public void simpleCollection() {
//        List<Bike> discBreakBikes = bikes.stream()
//            .filter(bike -> bike.brakes.equals(disc))
//            .collect(Collectors.toList());

        Set<Bike> discBreakBikes = bikes.stream()
            .filter(bike -> bike.brakes.equals(disc))
            .collect(Collectors.toSet());

        System.out.println(discBreakBikes);
    }

    @Test
    public void collectAndGroupBy() {
        Map<BrakeType, List<Bike>> groupedByBrakes = bikes.stream()
            .collect(Collectors.groupingBy(bike -> bike.brakes));

        groupedByBrakes
            .forEach((brake, bikes) -> System.out.println(brake + ": " + bikes + "\n"));
    }

    @Test
    public void collectAndJoin() {
        String phrase = bikes.stream()
            .filter(bike -> !bike.brakes.equals(caliper))
            .map(bike -> bike.owner)
            .collect(Collectors.joining(" and ", "Of all the bikes, ", " have the best"));

        System.out.println(phrase);
    }
}
