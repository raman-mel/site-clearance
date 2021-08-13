package com.oracle.site.input;

import static com.oracle.site.model.Direction.EAST;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.oracle.site.exception.FileNotFoundException;
import com.oracle.site.exception.ValidationException;
import com.oracle.site.model.Site;
import org.junit.jupiter.api.Test;

public class FileProcessorTest {

    private FileProcessor fileProcessor = new FileProcessor();

    @Test
    public void itShouldProcessValidFile() {
        final Site site = fileProcessor.prepareSite("src/test/resources/siteMap.txt");
        assertEquals(0, site.getBulldozer().getCurrentLocation().getCurrRow());
        assertEquals(-1, site.getBulldozer().getCurrentLocation().getCurrColumn());
        assertEquals(EAST, site.getBulldozer().getCurrentDirection());
        assertEquals(0, site.getCommands().size());
    }

    @Test
    public void itShouldProcessValidFileWithWhitespaces() {
        final Site site = fileProcessor.prepareSite("src/test/resources/siteMapWhitespace.txt");
        assertEquals(0, site.getBulldozer().getCurrentLocation().getCurrRow());
        assertEquals(-1, site.getBulldozer().getCurrentLocation().getCurrColumn());
        assertEquals(EAST, site.getBulldozer().getCurrentDirection());
        assertEquals(0, site.getCommands().size());
    }

    @Test
    public void itShouldThrowValidationExceptionForFileWithInvalidValue() {
        assertThrows(ValidationException.class, () -> fileProcessor.prepareSite("src/test/resources/siteMapInvalid.txt"));
    }

    @Test
    public void itShouldThrowValidationExceptionIfSiteIsNotSquare() {
        assertThrows(ValidationException.class, () -> fileProcessor.prepareSite("src/test/resources/siteMapNotSquare.txt"));
    }

    @Test
    public void itShouldThrowValidationExceptionForInvalidFile() {
        assertThrows(FileNotFoundException.class, () -> fileProcessor.prepareSite("src/test/resources/something.txt"));
    }

}