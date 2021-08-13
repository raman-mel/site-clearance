package com.oracle.site.model;

public class Location {
    private int currRow = 0;
    private int currColumn = -1;

    public int getCurrRow() {
        return currRow;
    }

    public void setCurrRow(int currRow) {
        this.currRow = currRow;
    }

    public int getCurrColumn() {
        return currColumn;
    }

    public void setCurrColumn(int currColumn) {
        this.currColumn = currColumn;
    }
}
