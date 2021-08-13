package com.oracle.site.command;

import static com.oracle.site.model.Turn.LEFT;

import com.oracle.site.model.Site;
import com.oracle.site.service.SiteService;

public class LeftCommand implements Command {

    private final SiteService siteService;

    public LeftCommand(final SiteService siteService) {
        this.siteService = siteService;
    }

    @Override
    public void execute(final Site site) {
        siteService.turn(site.getBulldozer(), LEFT);
    }

    @Override
    public String toString() {
        return "turn left";
    }
}
