package com.oracle.site.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import com.oracle.site.model.Site;
import com.oracle.site.service.SiteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AdvanceCommandTest {

    @Mock
    private SiteService siteService;

    @Mock
    private Site site;

    private static final int STEPS = 10;

    private AdvanceCommand command;

    @BeforeEach
    public void init() {
        command = new AdvanceCommand(siteService);
        command.setSteps(STEPS);
    }

    @Test
    public void isShouldExecute() {
        command.execute(site);
        verify(siteService).advance(site, STEPS);
    }

    @Test
    public void testToString() {
        assertEquals(command.toString(), "advance 10");
    }
}