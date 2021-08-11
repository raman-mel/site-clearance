package com.oracle.site.model;

public enum BlockType {
    PLAIN("o"),
    ROCKY("r"),
    REMOVABLE_TREES("t"),
    PRESERVED_TREES("T");

    private final String type;

    BlockType(final String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
