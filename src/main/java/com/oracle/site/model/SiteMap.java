package com.oracle.site.model;

public class SiteMap {

    private final Block[][] blocks;

    public SiteMap(final Block[][] blocks) {
        this.blocks = blocks;
    }

    public Block[][] getBlocks() {
        return blocks;
    }

    public void printSiteMap() {
        for (Block[] rows : blocks) {
            for (Block block : rows) {
                System.out.print(block + " ");
            }
            System.out.println();
        }
    }
}
