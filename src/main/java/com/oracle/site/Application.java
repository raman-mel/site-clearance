package com.oracle.site;

import com.oracle.site.exception.ValidationException;
import com.oracle.site.service.SiteSimulatorServiceImpl;

public class Application {

    public static void main(String... args) {
        if (args.length == 0) {
            System.out.println("Please provide source file path as the argument");
            System.exit(1);
        }

        try {
            new SiteSimulatorServiceImpl().startSimulation(args[0]);
        } catch (ValidationException ex) {
            System.out.println("Program exiting due to error\n" + ex.getMessage());
        }
    }

}
