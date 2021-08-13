package com.oracle.site.command;

import static com.oracle.site.model.Turn.RIGHT;

import com.oracle.site.model.Site;
import com.oracle.site.service.SiteService;

public class RightCommand implements Command {

    private final SiteService siteService;

    public RightCommand(final SiteService siteService) {
        this.siteService = siteService;
    }

    @Override
    public void execute(final Site site) {
        siteService.turn(site.getBulldozer(), RIGHT);
    }

    @Override
    public String toString() {
        return "turn right";
    }
}
