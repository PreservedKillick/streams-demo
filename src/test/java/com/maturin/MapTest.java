package com.maturin;

import static com.maturin.Bike.BrakeType.caliper;
import static com.maturin.Bike.BrakeType.cantilever;
import static com.maturin.Bike.BrakeType.disc;
import static com.maturin.MotorizedBike.Type.dirtbike;
import static com.maturin.MotorizedBike.Type.electric;
import static com.maturin.MotorizedBike.Type.motorcycle;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class MapTest {

    List<Bike> bikes = Arrays.asList(
        new Bike("Sam", 1, "green", disc),
        new Bike("Leroy", 2, "blue", cantilever),
        new Bike("Nadiya", 2, "blue", caliper),
        new Bike("Mary", 2, "red", cantilever),
        new Bike("Tamal", 3, "green", caliper)
    );

    @Test
    public void getFasterBikes() {
        List<MotorizedBike> fasterBikes = bikes.stream()
            // for the cantilever bikes
            .filter(bike -> bike.brakes.equals(cantilever))
            // lets give them electric bikes
            .map(bike -> new MotorizedBike(bike.owner, electric, bike.color))
            .collect(Collectors.toList());

        System.out.println(fasterBikes);
    }

    List<String> gears = Arrays.asList("gear_x", "gear_y", "gear_z");

    List<MotorizedBike> motorizedBikes = Arrays.asList(
        new MotorizedBike("Sam", electric, "green", gears),
        new MotorizedBike("Leroy", motorcycle, "blue", gears),
        new MotorizedBike("Nadiya", dirtbike, "blue", gears),
        new MotorizedBike("Mary", electric, "red", gears),
        new MotorizedBike("Tamal", motorcycle, "green", gears)
    );

    @Test
    public void flatMapGears() {
        // just flatMap in action

        motorizedBikes.stream()
            .flatMap(bike -> bike.gears.stream())
            .forEach(gear -> System.out.println(gear));
    }

    @Test
    public void flatMapToListOfDifferentGears() {
        System.out.println(gears);

        Set<String> newGears = motorizedBikes.stream()
            .flatMap(bike -> bike.gears.stream())
            .map(gear -> gear.substring(0,5))
            .map(gear -> gear + ThreadLocalRandom.current().nextInt(1,5))
            // no duplicates, thanks
            .collect(Collectors.toSet());

        System.out.println(newGears);
    }
}
