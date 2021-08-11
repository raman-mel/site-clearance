package com.oracle.site.model;

import static com.oracle.site.model.BlockType.PLAIN;
import static com.oracle.site.model.BlockType.PRESERVED_TREES;
import static com.oracle.site.model.BlockType.REMOVABLE_TREES;
import static com.oracle.site.model.BlockType.ROCKY;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BlockTest {

    @Test
    public void itShouldReturnValidForPlainBlock() {
        assertEquals("o", new Block(PLAIN).toString());
    }

    @Test
    public void itShouldReturnValidForRockyBlock() {
        assertEquals("r", new Block(ROCKY).toString());
    }

    @Test
    public void itShouldReturnValidForTreeRemovalBlock() {
        assertEquals("t", new Block(REMOVABLE_TREES).toString());
    }

    @Test
    public void itShouldReturnValidForPreservedTreeBlock() {
        assertEquals("T", new Block(PRESERVED_TREES).toString());
    }


}