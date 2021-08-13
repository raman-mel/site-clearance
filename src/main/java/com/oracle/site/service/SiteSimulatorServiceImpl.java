package com.oracle.site.service;

import com.oracle.site.command.Command;
import com.oracle.site.command.CommandMapper;
import com.oracle.site.exception.ValidationException;
import com.oracle.site.input.FileProcessor;
import com.oracle.site.model.Site;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class SiteSimulatorServiceImpl implements SiteSimulatorService {

    private final CostService costService;
    private final SiteService siteService;
    private final CommandService commandService;
    private final FileProcessor fileProcessor;
    private final InputStream inputStream;

    public SiteSimulatorServiceImpl() {
        this.costService = new CostServiceImpl();
        this.siteService = new SiteServiceImpl(costService);
        this.commandService = new CommandServiceImpl(new CommandMapper(siteService));
        this.fileProcessor = new FileProcessor();
        this.inputStream = System.in;
    }

    public SiteSimulatorServiceImpl(final CostService costService, final SiteService siteService,
            final CommandService commandService, final FileProcessor fileProcessor, final InputStream inputStream) {
        this.costService = costService;
        this.siteService = siteService;
        this.commandService = commandService;
        this.fileProcessor = fileProcessor;
        this.inputStream = inputStream;
    }

    @Override
    public void startSimulation(final String filePath) {
        final Site site = fileProcessor.prepareSite(filePath);
        System.out.println("Welcome to the Aconex site clearing simulator. This is a map of the site:\n");
        siteService.displaySiteMap(site);
        System.out.println("\nThe bulldozer is currently located at the Northern edge of the "
                + "site, immediately to the West of the site, and facing East.\n");
        processUserInput(site, inputStream);
    }

    void processUserInput(final Site site, final InputStream inputStream) {
        final Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8.name());
        System.out.print("(l)eft, (r)ight, (a)dvance <n>, (q)uit: ");
        while (scanner.hasNext()) {
            final Command command = commandService.getCommand(scanner.nextLine());
            siteService.addCommand(site, command);
            try {
                command.execute(site);
            } catch (ValidationException ex) {
                System.out.println("\n Program exiting due to a command error: " + ex.getMessage());
                stopSimulation(site);
            } finally {
                site.getItemisedCost().incrementCommunicationQty(1);
            }
            System.out.print("(l)eft, (r)ight, (a)dvance <n>, (q)uit: ");
        }
    }

    @Override
    public void stopSimulation(final Site site) {
        siteService.stopSimulation(site);
    }
}
