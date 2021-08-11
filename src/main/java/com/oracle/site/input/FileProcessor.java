package com.oracle.site.input;

import static java.lang.String.format;

import com.oracle.site.exception.FileNotFoundException;
import com.oracle.site.exception.ValidationException;
import com.oracle.site.model.Block;
import com.oracle.site.model.BlockType;
import com.oracle.site.model.SiteMap;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

public class FileProcessor {

    private Stream<String> processFileInput(String filePath) {
        try {
            return Files.lines(Paths.get(filePath));
        } catch (IOException ex) {
           throw new FileNotFoundException(format("Source file [%s] not found", filePath));
        }
    }

    public SiteMap fileToSiteMap(String filePath) {
        return new SiteMap(processFileInput(filePath)
                .map(s-> s.replaceAll(" ", ""))
                .map( s -> s.split(""))
                .map(arr -> toBlockArray(arr))
                .toArray(Block[][]::new));
    }

    private Block[] toBlockArray(String[] input) {
        return  Arrays.asList(input)
                .stream()
                .map(c -> new Block(getBlockType(c)))
                .toArray(Block[]::new);
    }

    private BlockType getBlockType(String type) {
        for(BlockType blockType: BlockType.values()) {
            if(blockType.getType().equals(type)) {
                return blockType;
            }
        }
        throw new ValidationException(format("Invalid block type [%s] found in source file", type));
    }

}