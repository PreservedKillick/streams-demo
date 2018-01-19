package com.maturin;

import static com.maturin.Bike.BrakeType.caliper;
import static com.maturin.Bike.BrakeType.cantilever;
import static com.maturin.Bike.BrakeType.disc;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MiscTest {
    List<Bike> bikes = Arrays.asList(
        new Bike("Sam", 1, "green", disc),
        new Bike("Leroy", 2, "blue", cantilever),
        new Bike("Nadiya", 2, "blue", caliper),
        new Bike("Mary", 2, "red", cantilever),
        new Bike("Tamal", 3, "green", caliper)
    );

    @Test
    public void count() {
        long total = bikes.stream()
            .filter(bike -> bike.color.equals("blue"))
            .count();

        System.out.println(total);
    }

    @Test
    public void findFirst() {
        Optional<Bike> blueBike = bikes.stream()
            .filter(bike -> {
                System.out.println("filter: " + bike);
                return bike.brakes.equals(caliper);
            })
            .findAny();

        blueBike.ifPresent(bike -> System.out.println(bike));
    }

    @Test
    public void peek() {
        List<Bike> greenBikes = bikes.stream()
            .filter(bike -> bike.color.equals("green"))
            .peek(bike -> System.out.println(bike))
            .collect(Collectors.toList());

        System.out.println(greenBikes);
    }
}
