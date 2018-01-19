package com.maturin;

import com.google.common.base.MoreObjects;

import java.util.List;

public class MotorizedBike {
    public enum Type {
        electric,
        motorcycle,
        dirtbike
    }

    public String owner;
    public final Type type;
    public final String color;
    public List<String> gears;

    public MotorizedBike(String owner, Type type, String color, List<String> gears) {
        this.owner = owner;
        this.type = type;
        this.color = color;
        this.gears = gears;
    }

    public MotorizedBike(Type type, String color) {
        this.type = type;
        this.color = color;
    }

    public MotorizedBike(String owner, Type type, String color) {
        this.owner = owner;
        this.type = type;
        this.color = color;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("owner", owner)
            .add("type", type)
            .add("color", color)
            .toString();
    }
}
