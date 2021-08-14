package com.oracle.site;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

import com.oracle.site.exception.ValidationException;
import com.oracle.site.service.SiteSimulatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ApplicationTest {

    @Mock
    private SiteSimulatorService siteSimulatorService;

    private Application application;

    @BeforeEach
    public void init() {
        application = new Application(siteSimulatorService);
    }

    @Test
    public void itShouldValidateNullArgument() {
        application.start(null);
        verifyZeroInteractions(siteSimulatorService);
    }

    @Test
    public void itShouldValidateEmptyArgument() {
        application.start(new String[]{});
        verifyZeroInteractions(siteSimulatorService);
    }

    @Test
    public void itShouldValidateNumberOfArguments() {
        application.start("firstFile", "secondFile");
        verifyZeroInteractions(siteSimulatorService);
    }

    @Test
    public void itShouldProcessValidArgument() {
        final String filePath = "/usr/path";
        application.start(filePath);
        verify(siteSimulatorService).startSimulation(filePath);
    }

    @Test
    public void itShouldHandleValidationException() {
        final String filePath = "/usr/path";
        doThrow(new ValidationException("testing")).when(siteSimulatorService).startSimulation(filePath);
        application.start(filePath);
    }
}