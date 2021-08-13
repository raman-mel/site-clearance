package com.oracle.site;

import com.oracle.site.service.SiteSimulatorServiceImpl;

public class Application {

    public static void main(String... args) {
        if (args.length == 0) {
            System.out.println("Please provide source file path as the argument");
            System.exit(1);
        }

        new SiteSimulatorServiceImpl().startSimulation(args[0]);
    }

}
