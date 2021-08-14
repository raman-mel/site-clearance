package com.oracle.site.command;

import com.oracle.site.model.Site;
import com.oracle.site.service.SiteService;

public class QuitCommand implements Command {

    private final SiteService siteService;

    public QuitCommand(final SiteService siteService) {
        this.siteService = siteService;
    }

    @Override
    public void execute(final Site site) {
        siteService.stopSimulation(site);
    }

    @Override
    public String toString() {
        return "quit";
    }
}
