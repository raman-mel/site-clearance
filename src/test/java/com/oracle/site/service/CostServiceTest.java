package com.oracle.site.service;

import static com.oracle.site.model.BlockType.REMOVABLE_TREES;
import static com.oracle.site.service.CostServiceImpl.BULLDOZER_DAMAGE_COST;
import static com.oracle.site.service.CostServiceImpl.COMMUNICATION_COST;
import static com.oracle.site.service.CostServiceImpl.FUEL_COST;
import static com.oracle.site.service.CostServiceImpl.PROTECTED_TREE_DAMAGE_COST;
import static com.oracle.site.service.CostServiceImpl.UNCLEARED_BLOCKS_COST;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.oracle.site.model.Block;
import com.oracle.site.model.ItemisedCost;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CostServiceTest {

    private ItemisedCost itemisedCost;
    private CostService costService;

    private static final int COMMUNICATION_OVERHEAD = 8;
    private static final int FUEL_QTY = 20;
    private static final int PROTECTED_TREES_DAMAGED = 1;
    private static final int PAINT_DAMAGE = 2;
    private static final int UNCLEARED_BOCKS = 10;

    @BeforeEach
    public void init() {
        costService = new CostServiceImpl();
        itemisedCost = new ItemisedCost();
        itemisedCost.incrementCommunicationQty(COMMUNICATION_OVERHEAD);
        itemisedCost.incrementFuelQty(FUEL_QTY);
        itemisedCost.incrementProtectedTreeDamageQty(PROTECTED_TREES_DAMAGED);
        itemisedCost.incrementBulldozerDamageQty(PAINT_DAMAGE);
        itemisedCost.setUnclearedBlocksQty(UNCLEARED_BOCKS);
    }

    @Test
    public void testGetCommunicationOverheadCost() {
        assertEquals(COMMUNICATION_OVERHEAD * COMMUNICATION_COST, costService.getCommunicationOverheadCost(itemisedCost));
    }

    @Test
    public void testGetFuelCost() {
        assertEquals( FUEL_QTY * FUEL_COST, costService.getFuelCost(itemisedCost));
    }

    @Test
    public void testGetProtectedTreeDamageCost() {
        assertEquals( PROTECTED_TREES_DAMAGED * PROTECTED_TREE_DAMAGE_COST, costService.getProtectedTreeDamageCost(itemisedCost));
    }

    @Test
    public void testGetBulldozerPaintDamageCost() {
        assertEquals( PAINT_DAMAGE * BULLDOZER_DAMAGE_COST, costService.getBulldozerPaintDamageCost(itemisedCost));
    }

    @Test
    public void testGetUnclearedSiteCost() {
        assertEquals( UNCLEARED_BOCKS * UNCLEARED_BLOCKS_COST, costService.getUnclearedSiteCost(itemisedCost));
    }

    @Test
    public void testGetTotalCost() {
        long totalCost = (COMMUNICATION_OVERHEAD * COMMUNICATION_COST)
                + (FUEL_QTY * FUEL_COST)
                + (PROTECTED_TREES_DAMAGED * PROTECTED_TREE_DAMAGE_COST)
                + (PAINT_DAMAGE * BULLDOZER_DAMAGE_COST)
                + (UNCLEARED_BOCKS * UNCLEARED_BLOCKS_COST);
        assertEquals( totalCost, costService.getTotalCost(itemisedCost));
    }

    @Test
    public void testDisplayItemisedCost() {
      costService.displayItemisedCost(itemisedCost);
    }

    @Test
    public void testProcessCostForAVisit_bulldozerStopping() {
        Block block = new Block(REMOVABLE_TREES);
        long previousDamage = itemisedCost.getBulldozerDamageQty();
        long previousFuelQty = itemisedCost.getFuelQty();
        costService.processCostForAVisit(itemisedCost, block, true);
        assertEquals(previousDamage, itemisedCost.getBulldozerDamageQty());
        assertEquals(previousFuelQty + block.getBlockType().getFuelUnits(), itemisedCost.getFuelQty());
        assertTrue(block.isVisited());
    }

    @Test
    public void testProcessCostForAVisit_bulldozerNotStopping() {
        Block block = new Block(REMOVABLE_TREES);
        long previousDamage = itemisedCost.getBulldozerDamageQty();
        long previousFuelQty = itemisedCost.getFuelQty();
        costService.processCostForAVisit(itemisedCost, block, false);
        assertEquals(previousDamage + 1, itemisedCost.getBulldozerDamageQty());
        assertEquals(previousFuelQty + block.getBlockType().getFuelUnits(), itemisedCost.getFuelQty());
        assertTrue(block.isVisited());
    }

    @Test
    public void testProcessCostForAlreadyVisitedBlock() {
        Block block = new Block(REMOVABLE_TREES);
        block.setVisited(true);
        long previousDamage = itemisedCost.getBulldozerDamageQty();
        long previousFuelQty = itemisedCost.getFuelQty();
        costService.processCostForAVisit(itemisedCost, block, false);
        assertEquals(previousDamage, itemisedCost.getBulldozerDamageQty());
        assertEquals(previousFuelQty + 1, itemisedCost.getFuelQty());
        assertTrue(block.isVisited());
    }
}