package com.oracle.site.service;

import static com.oracle.site.model.BlockType.REMOVABLE_TREES;

import com.oracle.site.model.Block;
import com.oracle.site.model.ItemisedCost;

public class CostServiceImpl implements CostService {

    static final Integer COMMUNICATION_COST = 1;
    static final Integer FUEL_COST = 1;
    static final Integer UNCLEARED_BLOCKS_COST = 3;
    static final Integer PROTECTED_TREE_DAMAGE_COST = 10;
    static final Integer BULLDOZER_DAMAGE_COST = 2;

    @Override
    public long getCommunicationOverheadCost(final ItemisedCost cost) {
        return cost.getCommunicationQty() * COMMUNICATION_COST;
    }

    @Override
    public long getFuelCost(final ItemisedCost cost) {
        return cost.getFuelQty() * FUEL_COST;
    }

    @Override
    public long getProtectedTreeDamageCost(final ItemisedCost cost) {
        return cost.getProtectedTreeDamageQty() * PROTECTED_TREE_DAMAGE_COST;
    }

    @Override
    public long getBulldozerPaintDamageCost(final ItemisedCost cost) {
        return cost.getBulldozerDamageQty() * BULLDOZER_DAMAGE_COST;
    }

    @Override
    public long getUnclearedSiteCost(final ItemisedCost cost) {
        return cost.getUnclearedBlocksQty() * UNCLEARED_BLOCKS_COST;
    }

    @Override
    public long getTotalCost(final ItemisedCost cost) {
        return getCommunicationOverheadCost(cost) + getFuelCost(cost) + getProtectedTreeDamageCost(cost)
                + getBulldozerPaintDamageCost(cost) + getUnclearedSiteCost(cost);
    }

    @Override
    public void processCostForAVisit(final ItemisedCost cost, final Block block, final boolean isBulldozerStopping) {
        if (block.isVisited()) {
            cost.incrementFuelQty(1);
        } else {
            if (block.getBlockType().equals(REMOVABLE_TREES) && !isBulldozerStopping) {
                cost.incrementBulldozerDamageQty(1);
            }
            cost.incrementFuelQty(block.getBlockType().getFuelUnits());
            block.setVisited(true);
        }
    }

    @Override
    public void displayItemisedCost(final ItemisedCost cost) {
        System.out.println("The costs for this land clearing operation were:");
        System.out.println("\nItem                       \t\t\tQuantity\t\tCost");
        System.out.println("communication overhead       \t\t\t" + cost.getCommunicationQty() + "\t\t\t" + getCommunicationOverheadCost(cost));
        System.out.println("fuel usage                   \t\t\t" + cost.getFuelQty() + "\t\t\t" + getFuelCost(cost));
        System.out.println("uncleared squares            \t\t\t" + cost.getUnclearedBlocksQty() + "\t\t\t" + getUnclearedSiteCost(cost));
        System.out.println("destruction of protected tree\t\t\t" + cost.getProtectedTreeDamageQty() + "\t\t\t" + getProtectedTreeDamageCost(cost));
        System.out.println("paint damage to bulldozer    \t\t\t" + cost.getBulldozerDamageQty() + "\t\t\t" + getBulldozerPaintDamageCost(cost));
        System.out.println("----");
        System.out.println("Total                        \t\t\t\t\t\t" + getTotalCost(cost));
    }
}
