package com.oracle.site.model;

public enum Direction {
    NORTH {
        public Direction getNextDirection(Turn turn) {
            switch (turn) {
                case LEFT:
                    return WEST;
                case RIGHT:
                    return EAST;
                default:
                    return this;
            }
        }

        public int[] getNextLocation(int currRow, int currColumn) {
            return new int[] {currRow - 1, currColumn};
        }
    },
    SOUTH {
        public Direction getNextDirection(Turn turn) {
            switch (turn) {
                case LEFT:
                    return EAST;
                case RIGHT:
                    return WEST;
                default:
                    return this;
            }
        }

        public int[] getNextLocation(int currRow, int currColumn) {
            return new int[] {currRow + 1, currColumn};
        }
    },
    WEST {
        public Direction getNextDirection(Turn turn) {
            switch (turn) {
                case LEFT:
                    return SOUTH;
                case RIGHT:
                    return NORTH;
                default:
                    return this;
            }
        }

        public int[] getNextLocation(int currRow, int currColumn) {
            return new int[] {currRow, currColumn - 1};
        }
    },
    EAST {
        public Direction getNextDirection(Turn turn) {
            switch (turn) {
                case LEFT:
                    return NORTH;
                case RIGHT:
                    return SOUTH;
                default:
                    return this;
            }
        }

        public int[] getNextLocation(int currRow, int currColumn) {
            return new int[] {currRow, currColumn + 1};
        }
    };

    public abstract Direction getNextDirection(Turn turn);
    public abstract int[] getNextLocation(int currRow, int currColumn);
}
