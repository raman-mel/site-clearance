package com.oracle.site.service;

import com.oracle.site.model.Site;

public interface SiteSimulatorService {

    void startSimulation(String filePath);

    void stopSimulation(Site site);
}
