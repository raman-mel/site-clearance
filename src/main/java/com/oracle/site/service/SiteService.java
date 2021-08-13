package com.oracle.site.service;

import com.oracle.site.command.Command;
import com.oracle.site.model.Bulldozer;
import com.oracle.site.model.Site;
import com.oracle.site.model.Turn;

public interface SiteService {

    void advance(Site site, int steps);

    void turn(Bulldozer bulldozer, Turn turn);

    long getUnvisitedBlocksCount(Site site);

    void updateCostWithUnvisitedBlocksCount(Site site);

    void displayIssuedCommands(Site site);

    void addCommand(Site site, Command command);

    void displaySiteMap(Site site);

    void displayItemisedCost(Site site);

    void stopSimulation(Site site);
}
