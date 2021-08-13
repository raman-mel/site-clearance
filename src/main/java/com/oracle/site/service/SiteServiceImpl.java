package com.oracle.site.service;

import static com.oracle.site.model.BlockType.PRESERVED_TREES;
import static java.util.stream.Collectors.joining;

import com.oracle.site.command.Command;
import com.oracle.site.exception.ValidationException;
import com.oracle.site.model.Block;
import com.oracle.site.model.Bulldozer;
import com.oracle.site.model.Direction;
import com.oracle.site.model.Location;
import com.oracle.site.model.Site;
import com.oracle.site.model.Turn;

import java.util.stream.Stream;

public class SiteServiceImpl implements SiteService {

    private final CostService costService;

    public SiteServiceImpl(final CostService costService) {
        this.costService = costService;
    }

    @Override
    public void advance(final Site site, final int steps) {
        for (int i=0; i<steps; i++) {
            Location currentLocation = site.getBulldozer().getCurrentLocation();
            Direction currentDirection = site.getBulldozer().getCurrentDirection();
            int[] nextLocation = currentDirection.getNextLocation(currentLocation.getCurrRow(), currentLocation.getCurrColumn());
            validateNextLocation(site, nextLocation);
            Block nextBlock = site.getSiteMap().getBlocks()[nextLocation[0]][nextLocation[1]];
            currentLocation.setCurrRow(nextLocation[0]);
            currentLocation.setCurrColumn(nextLocation[1]);
            costService.processCostForAVisit(site.getItemisedCost(), nextBlock, i == steps-1);
        }
    }

    void validateNextLocation(final Site site, final int[] nextLocation) {
        if (!isValidLocation(site, nextLocation)) {
            throw new ValidationException("Bulldozer went out of the site : " + nextLocation[0] + "," + nextLocation[1]);
        }
        if (isAProtectedLocation(site, nextLocation)) {
            site.getItemisedCost().incrementProtectedTreeDamageQty(1);
            throw new ValidationException("Bulldozer attempted destruction of protected trees");
        }
    }

    @Override
    public void turn(final Bulldozer bulldozer, final Turn turn) {
        final Direction nextDirection = bulldozer.getCurrentDirection().getNextDirection(turn);
        bulldozer.setCurrentDirection(nextDirection);
    }

    @Override
    public long getUnvisitedBlocksCount(final Site site) {
        return Stream.of(site.getSiteMap().getBlocks())
                .flatMap(Stream::of)
                .filter(block -> !block.isVisited() && !block.getBlockType().equals(PRESERVED_TREES))
                .count();
    }

    @Override
    public void updateCostWithUnvisitedBlocksCount(final Site site) {
        site.getItemisedCost().setUnclearedBlocksQty(getUnvisitedBlocksCount(site));
    }

    @Override
    public void displayIssuedCommands(final Site site) {
        System.out.println("These are the commands you issued:\n");
        System.out.println(site.getCommands().stream()
                .map(command -> command.toString()).collect(joining(", ")));
        System.out.println();
    }

    @Override
    public void displayItemisedCost(final Site site) {
        costService.displayItemisedCost(site.getItemisedCost());
    }

    @Override
    public void addCommand(final Site site, final Command command) {
        site.getCommands().add(command);
    }

    @Override
    public void displaySiteMap(final Site site) {
        site.getSiteMap().printSiteMap();
    }

    @Override
    public void stopSimulation(final Site site) {
        System.out.print("The simulation has ended. ");
        displayIssuedCommands(site);
        updateCostWithUnvisitedBlocksCount(site);
        displayItemisedCost(site);
        System.out.println("Thank you for using the Aconex site clearing simulator.");
        System.exit(0);
    }

    boolean isValidLocation(final Site site, final int[] location) {
        int maxRows = site.getSiteMap().getBlocks().length;
        int maxColumns = site.getSiteMap().getBlocks()[0].length;

        return location[0] >=0 && location[0] < maxRows
                && location[1] >=0 && location[1] < maxColumns;
    }

    boolean isAProtectedLocation(final Site site, final int[] location) {
        return site.getSiteMap().getBlocks()[location[0]][location[1]].getBlockType().equals(PRESERVED_TREES);
    }
}
