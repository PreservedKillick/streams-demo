package com.maturin;

import static com.maturin.Bike.BrakeType.caliper;
import static com.maturin.Bike.BrakeType.disc;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class StreamOrderingTest {

    List<String> mountainsOR = Arrays.asList("Cascades", "Hood", "Steens", "Jefferson", "Bachelor");

    @Test
    public void streamLaziness() {
        // no terminal operation
        mountainsOR.stream()
            .map(mountain -> {
                    System.out.println(mountain);
                    return mountain + ", OR";
                }
            );
    }

    @Test
    public void streamStillLazyAndVeritcal() {
        // with terminal operation
        mountainsOR.stream()
            .map(mountain -> {
                    System.out.println(mountain);
                    return mountain + ", OR";
                }
            )
            .forEach(mountain -> System.out.println(mountain + ", USA"));
    }

    /*
    operations don't occur horizontally across each element in the collection.  Almost always, each element moves along
    the chain of operations vertically and sequentially
     */
    @Test
    public void streamLazyAndVertMeansFewerOps() {
        // can cut down on the number of operations that need to be performed
        boolean isMatch = mountainsOR.stream()
            .map(mountain -> {
                    System.out.println(mountain);
                    return mountain + ", OR";
                }
            )
            .anyMatch(mountain -> {
                System.out.println("match step: " + mountain);
                return mountain.startsWith("S");
            });

        System.out.println(isMatch);
    }


    /*
    Because most stream operations are both non-interfering (doesn't modify the underlying source of the stream) and
    stateless (execution of each operation is deterministic), you can minimize the number of operations by thinking about
    how you order them
     */

    List<Bike> bikes = Arrays.asList(
        new Bike(1, "green", disc),
        new Bike(2, "blue", caliper),
        new Bike(2, "red", disc),
        new Bike(3, "green", caliper)
    );

    @Test
    public void couldBeEvenFewerIfWeReorderOperations() {
        bikes.stream()
            // from each, make similar bike except 2-wheeled, please
            .map(bike -> {
                System.out.println("map: " + bike);
                return new Bike(2, bike.color, bike.brakes);
            })
            // only red bikes please
            .filter(bike -> {
                System.out.println("filter: " + bike);
                return bike.color.equals("red");
            })
            // print each result
            .forEach(bike -> System.out.println("forEach: " + bike));

        System.out.println(bikes);
    }

    @Test
    public void fewerWithReorderedOperations() {
        bikes.stream()
            // only red bikes, please
            .filter(bike -> {
                System.out.println("filter: " + bike);
                return bike.color.equals("red");
            })
            // from each, make similar bike except two-wheeled, please
            .map(bike -> {
                System.out.println("map: " + bike);
                return new Bike(2, bike.color, bike.brakes);
            })
            // print each result
            .forEach(bike -> System.out.println("forEach: " + bike));
    }

    @Test
    public void butWhenStateful() {
        bikes.stream()
            // but sorting a collection means you need to compare the *whole* collection at a go (horizontal!)
            // and maintain that sorted collection (stateful!)
            .sorted((bike1, bike2) -> {
                System.out.printf("sorting: %s; %s \n", bike1, bike2);
                return bike1.numberOfWheels.compareTo(bike2.numberOfWheels);
            })
            // only 2-wheeled bikes, please
            .filter(bike -> {
                System.out.println("filter: " + bike);
                return bike.numberOfWheels.equals(2);
            })
            // from each, make similar bike except pink, please
            .map(bike -> {
                System.out.println("map: " + bike);
                return new Bike(bike.numberOfWheels, "pink", bike.brakes);
            })
            // print each result
            .forEach(bike -> System.out.println("forEach: " + bike));
    }

    @Test
    public void knowingThatSortWorksHorizontally() {
        // let's minimize the number of operations now that we know how sorting works

        bikes.stream()
            // only 2-wheeled bikes, please
            .filter(bike -> {
                System.out.println("filter: " + bike);
                return bike.numberOfWheels.equals(2);
            })
            // but sorting a collection means you need to compare the *whole* collection at a go (horizontal!)
            // and maintain that sorted collection (stateful!)
            .sorted((b1, b2) -> {
                System.out.printf("sorting: %s; %s \n", b1, b2);
                return b1.numberOfWheels.compareTo(b2.numberOfWheels);
            })
            // from each, make similar bike except pink, please
            .map(bike -> {
                System.out.println("map: " + bike);
                return new Bike(bike.numberOfWheels, "pink", bike.brakes);
            })
            // print each result
            .forEach(bike -> System.out.println("forEach: " + bike));
    }
}
