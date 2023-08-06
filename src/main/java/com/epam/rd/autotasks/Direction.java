package com.epam.rd.autotasks;

public enum Direction {
    N(0), NE(45), E(90), SE(135), S(180), SW(225), W(270), NW(315);

    private final int degrees;

    Direction(final int degrees) {
        this.degrees = degrees;
    }

    public static Direction ofDegrees(int degrees) {
        Direction[] d = Direction.values();
        if (degrees == 360) {
            return Direction.N;
        } else if (degrees > 360 && degrees <= 765) {
            return Direction.NE;
        } else if (degrees == - 45) {
            return Direction.NW;
        } else if (degrees == - 135) {
            return Direction.SW;
        } else {
            for (Direction direction : d) {
                if (direction.degrees == degrees)
                    return direction;
            }
            return null;
        }
    }

    public static Direction closestToDegrees(int degrees) {
        degrees %= 360;
        if (degrees < 0) {
            degrees += 360;
        }

        Direction closestDirection = null;
        int minDifference = Integer.MAX_VALUE;

        for (Direction direction : Direction.values()) {
            int difference = Math.abs(direction.degrees - degrees);
            if (difference < minDifference) {
                minDifference = difference;
                closestDirection = direction;
            }
        }

        return closestDirection;
    }


    public Direction opposite() {
        Direction[] directions = Direction.values();
        int oppositeDegrees;
        if (this.degrees >= 0 && this.degrees <= 180) {
            oppositeDegrees = this.degrees + 180;
        } else {
            oppositeDegrees = this.degrees - 180;
        }

        if (oppositeDegrees == 360) return Direction.N;
        for (Direction direction : directions) {
            if (direction.degrees == oppositeDegrees) {
                return direction;
            }
        }
        return null;

    }

    public int differenceDegreesTo(Direction direction) {

        int res = direction.degrees - this.degrees;
        if (direction == Direction.NW && this == Direction.N) res = 360 - direction.degrees;
        return Math.abs(res);
    }
}
