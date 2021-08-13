package com.oracle.site.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.oracle.site.command.Command;
import com.oracle.site.exception.ValidationException;
import com.oracle.site.model.Block;
import com.oracle.site.model.BlockType;
import com.oracle.site.model.Bulldozer;
import com.oracle.site.model.Direction;
import com.oracle.site.model.ItemisedCost;
import com.oracle.site.model.Location;
import com.oracle.site.model.Site;
import com.oracle.site.model.SiteMap;
import com.oracle.site.model.Turn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Queue;

@ExtendWith(MockitoExtension.class)
public class SiteServiceTest {

    @Mock
    private CostService costService;

    @Mock
    private Bulldozer bulldozer;

    @Mock
    private Direction direction;

    @Mock
    private Direction direction2;

    @Mock
    private Site site;

    @Mock
    private Command command;

    @Mock
    private Queue commandQueue;

    @Mock
    private SiteMap siteMap;

    @Mock
    private ItemisedCost itemisedCost;

    private SiteServiceImpl siteService;

    @BeforeEach
    public void init() {
        siteService = new SiteServiceImpl(costService);
    }

    @Test
    public void test_turn() {
        when(bulldozer.getCurrentDirection()).thenReturn(direction);
        when(direction.getNextDirection(Turn.LEFT)).thenReturn(direction2);
        siteService.turn(bulldozer, Turn.LEFT);
        verify(bulldozer).setCurrentDirection(direction2);
    }

    @Test
    public void test_displayItemisedCost() {
        when(site.getItemisedCost()).thenReturn(itemisedCost);
        siteService.displayItemisedCost(site);
        verify(costService).displayItemisedCost(itemisedCost);
    }

    @Test
    public void test_addCommand() {
        when(site.getCommands()).thenReturn(commandQueue);
        siteService.addCommand(site, command);
        verify(commandQueue).add(command);
    }

    @Test
    public void test_displaySiteMap() {
        when(site.getSiteMap()).thenReturn(siteMap);
        siteService.displaySiteMap(site);
        verify(siteMap).printSiteMap();
    }

    @Test
    public void test_validLocation_Valid() {
        Block[][] blocks = new Block[5][5];
        when(site.getSiteMap()).thenReturn(siteMap);
        when(siteMap.getBlocks()).thenReturn(blocks);
        assertTrue(siteService.isValidLocation(site, new int[]{3, 3}));
    }

    @Test
    public void test_validLocation_Invalid() {
        Block[][] blocks = new Block[5][5];
        when(site.getSiteMap()).thenReturn(siteMap);
        when(siteMap.getBlocks()).thenReturn(blocks);
        assertFalse(siteService.isValidLocation(site, new int[]{4, 5}));
        assertFalse(siteService.isValidLocation(site, new int[]{5, 1}));
    }

    @Test
    public void test_isAProtectedLocation_valid() {
        int row = 3;
        int column = 3;
        Block[][] blocks = new Block[5][5];
        blocks[row][column] = new Block(BlockType.PRESERVED_TREES);
        when(site.getSiteMap()).thenReturn(siteMap);
        when(siteMap.getBlocks()).thenReturn(blocks);
        assertTrue(siteService.isAProtectedLocation(site, new int[]{row, column}));
    }

    @Test
    public void test_isAProtectedLocation_Invalid() {
        int row = 3;
        int column = 3;
        Block[][] blocks = new Block[5][5];
        blocks[row][column] = new Block(BlockType.ROCKY);
        when(site.getSiteMap()).thenReturn(siteMap);
        when(siteMap.getBlocks()).thenReturn(blocks);
        assertFalse(siteService.isAProtectedLocation(site, new int[]{row, column}));
    }

    @Test
    public void test_validateNextLocation_shouldReturn() {
        int row = 3;
        int column = 3;
        Block[][] blocks = new Block[5][5];
        blocks[row][column] = new Block(BlockType.ROCKY);
        when(site.getSiteMap()).thenReturn(siteMap);
        when(siteMap.getBlocks()).thenReturn(blocks);
        siteService.validateNextLocation(site, new int[]{row, column});
    }

    @Test
    public void test_validateNextLocation_shouldThrow1() {
        int row = 3;
        int column = 3;
        Block[][] blocks = new Block[5][5];
        blocks[row][column] = new Block(BlockType.PRESERVED_TREES);
        when(site.getSiteMap()).thenReturn(siteMap);
        when(site.getItemisedCost()).thenReturn(itemisedCost);
        when(siteMap.getBlocks()).thenReturn(blocks);
        assertThrows(ValidationException.class, () -> siteService.validateNextLocation(site, new int[]{row, column}));
        verify(itemisedCost).incrementProtectedTreeDamageQty(1);
    }

    @Test
    public void test_validateNextLocation_shouldThrow2() {
        Block[][] blocks = new Block[5][5];
        when(site.getSiteMap()).thenReturn(siteMap);
        when(siteMap.getBlocks()).thenReturn(blocks);
        assertThrows(ValidationException.class, () -> siteService.validateNextLocation(site, new int[]{6,6}));
    }

    @Test
    public void test_updateCostWithUnvisitedBlocksCount() {
        Block[][] blocks = new Block[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                blocks[i][j] = new Block(BlockType.ROCKY);
            }
        }

        blocks[0][2].setVisited(true);
        blocks[2][2].setVisited(true);
        blocks[3][2] = new Block(BlockType.PRESERVED_TREES);
        blocks[4][3] = new Block(BlockType.PRESERVED_TREES);

        when(site.getItemisedCost()).thenReturn(itemisedCost);
        when(site.getSiteMap()).thenReturn(siteMap);
        when(siteMap.getBlocks()).thenReturn(blocks);
        siteService.updateCostWithUnvisitedBlocksCount(site);
        verify(itemisedCost).setUnclearedBlocksQty(21);
    }

    @Test
    public void test_advance() {
        Block[][] blocks = new Block[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                blocks[i][j] = new Block(BlockType.ROCKY);
            }
        }

        blocks[0][2].setVisited(true);
        blocks[2][2].setVisited(true);
        blocks[3][2] = new Block(BlockType.PRESERVED_TREES);
        blocks[4][3] = new Block(BlockType.PRESERVED_TREES);

        when(site.getItemisedCost()).thenReturn(itemisedCost);
        when(site.getSiteMap()).thenReturn(siteMap);
        when(site.getBulldozer()).thenReturn(bulldozer);
        when(bulldozer.getCurrentLocation()).thenReturn(new Location());
        when(bulldozer.getCurrentDirection()).thenReturn(Direction.EAST);
        when(siteMap.getBlocks()).thenReturn(blocks);
        siteService.advance(site, 5);
        verify(costService, times(5)).processCostForAVisit(eq(itemisedCost), any(Block.class), anyBoolean());
    }
}