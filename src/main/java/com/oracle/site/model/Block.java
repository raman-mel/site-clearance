package com.oracle.site.model;

public class Block {

    private BlockType blockType;

    public Block(BlockType blockType) {
        this.blockType = blockType;
    }

    @Override
    public String toString() {
        return blockType.getType();
    }
}
