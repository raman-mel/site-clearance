package com.oracle.site.model;

public class Bulldozer {
    private Direction currentDirection;
    private Location currentLocation;

    public Bulldozer() {
        this.currentDirection = Direction.EAST;
        this.currentLocation = new Location();
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(Direction currentDirection) {
        this.currentDirection = currentDirection;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }
}
