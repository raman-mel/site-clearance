package com.oracle.site.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import com.oracle.site.command.Command;
import com.oracle.site.exception.ValidationException;
import com.oracle.site.input.FileProcessor;
import com.oracle.site.model.ItemisedCost;
import com.oracle.site.model.Site;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@ExtendWith(MockitoExtension.class)
public class SiteSimulatorServiceTest {

    @Mock
    private CostService costService;

    @Mock
    private SiteService siteService;

    @Mock
    private CommandService commandService;

    @Mock
    private FileProcessor fileProcessor;

    @Mock
    private Command command;

    @Mock
    private Site site;

    @Mock
    private ItemisedCost itemisedCost;

    private InputStream inputStream;

    private SiteSimulatorService siteSimulatorService;

    private static final String FILE_PATH = "/user/dummyPath";

    @BeforeEach
    public void init() throws FileNotFoundException {
        this.inputStream = new FileInputStream("src/test/resources/fileInput.txt");
        siteSimulatorService = new SiteSimulatorServiceImpl(costService, siteService, commandService, fileProcessor, inputStream);
    }

    @Test
    public void testStartSimulation() {
        when(fileProcessor.prepareSite(FILE_PATH)).thenReturn(site);
        when(commandService.getCommand(anyString())).thenReturn(command);
        when(site.getItemisedCost()).thenReturn(itemisedCost);

        siteSimulatorService.startSimulation(FILE_PATH);

        verify(siteService).displaySiteMap(site);
        verify(siteService, times(2)).addCommand(site, command);
        verify(itemisedCost, times(2)).incrementCommunicationQty(1);
    }

    @Test
    public void testStartSimulation_invalidFile() {
        when(fileProcessor.prepareSite(FILE_PATH)).thenThrow(ValidationException.class);
        assertThrows(ValidationException.class, () -> siteSimulatorService.startSimulation(FILE_PATH));
        verifyZeroInteractions(siteService);
    }

    @Test
    public void testStopSimulation() {
        siteSimulatorService.stopSimulation(site);
        verify(siteService).stopSimulation(site);
    }

}