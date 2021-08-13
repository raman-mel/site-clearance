package com.oracle.site.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import com.ginsberg.junit.exit.ExpectSystemExitWithStatus;
import com.oracle.site.model.Site;
import com.oracle.site.service.SiteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class QuitCommandTest {

    @Mock
    private SiteService siteService;

    @Mock
    private Site site;

    private QuitCommand command;

    @BeforeEach
    public void init() {
        command = new QuitCommand(siteService);
    }

    @Test
    @ExpectSystemExitWithStatus(0)
    public void itShouldExecute() {
        command.execute(site);
        verify(siteService).stopSimulation(site);
    }

    @Test
    public void testToString() {
        assertEquals(command.toString(), "quit");
    }
}