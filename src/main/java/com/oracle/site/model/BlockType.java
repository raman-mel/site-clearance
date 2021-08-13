package com.oracle.site.model;

public enum BlockType {
    PLAIN("o", 1),
    ROCKY("r", 2),
    REMOVABLE_TREES("t", 2),
    PRESERVED_TREES("T", 2);

    private final String type;
    private final Integer fuelUnits;

    BlockType(final String type, final int fuelUnits) {
        this.type = type;
        this.fuelUnits = fuelUnits;
    }

    public String getType() {
        return type;
    }

    public Integer getFuelUnits() {
        return fuelUnits;
    }
}
