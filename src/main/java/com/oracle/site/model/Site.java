package com.oracle.site.model;

import com.oracle.site.command.Command;

import java.util.LinkedList;
import java.util.Queue;

public class Site {

    private SiteMap siteMap;
    private Bulldozer bulldozer;
    private Queue<Command> commands;
    private ItemisedCost itemisedCost;

    public Site(final SiteMap siteMap) {
        this(siteMap, new Bulldozer(), new LinkedList<>(), new ItemisedCost());
    }

    public Site(final SiteMap siteMap, final Bulldozer bulldozer,
            final Queue commands, final ItemisedCost itemisedCost) {
        this.siteMap = siteMap;
        this.bulldozer = bulldozer;
        this.commands = commands;
        this.itemisedCost = itemisedCost;
    }

    public SiteMap getSiteMap() {
        return siteMap;
    }

    public Bulldozer getBulldozer() {
        return bulldozer;
    }

    public ItemisedCost getItemisedCost() {
        return itemisedCost;
    }

    public Queue<Command> getCommands() {
        return commands;
    }
}
