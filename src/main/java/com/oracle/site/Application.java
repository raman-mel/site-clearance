package com.oracle.site;

import com.oracle.site.exception.ValidationException;
import com.oracle.site.service.SiteSimulatorService;
import com.oracle.site.service.SiteSimulatorServiceImpl;

public class Application {

    private final SiteSimulatorService siteSimulatorService;

    public Application() {
        this(new SiteSimulatorServiceImpl());
    }

    public Application(SiteSimulatorService siteSimulatorService) {
        this.siteSimulatorService = siteSimulatorService;
    }

    public static void main(String... args) {
        new Application().start(args);
    }

    void start(String... args) {
        if (args != null && args.length == 1) {
            startSimulation(args[0]);
        } else {
            System.out.println("Invalid number of parameters. Program accepts only one parameter (source file path)");
        }
    }

    void startSimulation(String filePath) {
        try {
            siteSimulatorService.startSimulation(filePath);
        } catch (ValidationException ex) {
            System.out.println("Program exiting due to error\n" + ex.getMessage());
        }
    }
}
