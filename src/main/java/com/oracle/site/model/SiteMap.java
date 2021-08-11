package com.oracle.site.model;

public class SiteMap {

    private Block[][] blocks;

    public SiteMap(final Block[][] blocks) {
        this.blocks = blocks;
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
