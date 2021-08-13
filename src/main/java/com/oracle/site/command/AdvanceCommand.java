package com.oracle.site.command;

import com.oracle.site.model.Site;
import com.oracle.site.service.SiteService;

public class AdvanceCommand implements Command {

    private final SiteService siteService;

    private int steps;

    public AdvanceCommand(final SiteService siteService) {
        this.siteService = siteService;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    @Override
    public void execute(final Site site) {
        siteService.advance(site, steps);
    }

    @Override
    public String toString() {
        return "advance " + steps;
    }

}
