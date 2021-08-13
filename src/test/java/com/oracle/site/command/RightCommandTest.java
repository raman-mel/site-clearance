package com.oracle.site.command;

import static com.oracle.site.model.Turn.RIGHT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.oracle.site.model.Bulldozer;
import com.oracle.site.model.Site;
import com.oracle.site.service.SiteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RightCommandTest {

    @Mock
    private SiteService siteService;

    @Mock
    private Site site;

    @Mock
    private Bulldozer bulldozer;

    private Command command;

    @BeforeEach
    public void init() {
        command = new RightCommand(siteService);
    }

    @Test
    public void itShouldExecute() {
       when(site.getBulldozer()).thenReturn(bulldozer);
       command.execute(site);
       verify(siteService).turn(bulldozer, RIGHT);
    }

    @Test
    public void testToString() {
        assertEquals(command.toString(), "turn right");
    }
}