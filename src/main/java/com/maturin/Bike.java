package com.maturin;

import com.google.common.base.MoreObjects;

import java.util.Objects;

public class Bike {

    public enum BrakeType {
        caliper,
        disc,
        cantilever
    }

    public String owner;
    public final Integer numberOfWheels;
    public final String color;
    public final BrakeType brakes;

    public Bike(int numberOfWheels, String color, BrakeType brakes) {
        this.numberOfWheels = numberOfWheels;
        this.color = color;
        this.brakes = brakes;
    }

    public Bike(String owner, Integer numberOfWheels, String color, BrakeType brakes) {
        this.owner = owner;
        this.numberOfWheels = numberOfWheels;
        this.color = color;
        this.brakes = brakes;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("numberOfWheels", numberOfWheels)
            .add("color", color)
            .add("brakes", brakes)
            .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Bike bike = (Bike) o;
        return Objects.equals(numberOfWheels, bike.numberOfWheels) &&
            Objects.equals(color, bike.color) &&
            brakes == bike.brakes;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfWheels, color, brakes);
    }
}
