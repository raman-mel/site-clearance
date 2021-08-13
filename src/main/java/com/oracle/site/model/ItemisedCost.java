package com.oracle.site.model;

public class ItemisedCost {
    private long communicationQty;
    private long fuelQty;
    private long unclearedBlocksQty;
    private long protectedTreeDamageQty;
    private long bulldozerDamageQty;

    public void incrementCommunicationQty(int incrementBy) {
        this.communicationQty = communicationQty + incrementBy;
    }

    public void incrementFuelQty(int incrementBy) {
        this.fuelQty = fuelQty + incrementBy;
    }

    public void incrementProtectedTreeDamageQty(int incrementBy) {
        this.protectedTreeDamageQty = protectedTreeDamageQty + incrementBy;
    }

    public void incrementBulldozerDamageQty(int incrementBy) {
        this.bulldozerDamageQty = bulldozerDamageQty + incrementBy;
    }

    public void setUnclearedBlocksQty(long unclearedBlocksQty) {
        this.unclearedBlocksQty = unclearedBlocksQty;
    }

    public long getCommunicationQty() {
        return communicationQty;
    }

    public long getFuelQty() {
        return fuelQty;
    }

    public long getUnclearedBlocksQty() {
        return unclearedBlocksQty;
    }

    public long getProtectedTreeDamageQty() {
        return protectedTreeDamageQty;
    }

    public long getBulldozerDamageQty() {
        return bulldozerDamageQty;
    }
}
