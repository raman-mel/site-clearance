package com.oracle.site.service;

import com.oracle.site.model.Block;
import com.oracle.site.model.ItemisedCost;

public interface CostService {

    void displayItemisedCost(ItemisedCost cost);

    void processCostForAVisit(ItemisedCost cost, Block block, boolean isBulldozerStopping);

    long getCommunicationOverheadCost(ItemisedCost cost);

    long getFuelCost(ItemisedCost cost);

    long getProtectedTreeDamageCost(ItemisedCost cost);

    long getBulldozerPaintDamageCost(ItemisedCost cost);

    long getUnclearedSiteCost(ItemisedCost cost);

    long getTotalCost(ItemisedCost cost);
}
