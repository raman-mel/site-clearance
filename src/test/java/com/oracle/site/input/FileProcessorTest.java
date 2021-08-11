package com.oracle.site.input;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.oracle.site.exception.FileNotFoundException;
import com.oracle.site.exception.ValidationException;
import org.junit.jupiter.api.Test;

public class FileProcessorTest {

    private FileProcessor fileProcessor = new FileProcessor();

    @Test
    public void itShouldProcessValidFile() {
        fileProcessor.fileToSiteMap("src/test/resources/siteMap.txt").printSiteMap();
    }

    @Test
    public void itShouldProcessValidFileWithWhitespaces() {
        fileProcessor.fileToSiteMap("src/test/resources/siteMapWhitespace.txt").printSiteMap();
    }

    @Test
    public void itShouldThrowValidationExceptionForFileWithInvalidValue() {
        assertThrows(ValidationException.class, () -> fileProcessor.fileToSiteMap("src/test/resources/siteMapInvalid.txt"));
    }

    @Test
    public void itShouldThrowValidationExceptionForInvalidFile() {
        assertThrows(FileNotFoundException.class, () -> fileProcessor.fileToSiteMap("src/test/resources/something.txt"));
    }

}