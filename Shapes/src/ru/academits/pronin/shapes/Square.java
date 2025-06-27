package ru.academits.pronin.shapes;

public class Square implements Shape {
    private final double side;

    public Square(double side) {
        this.side = side;
    }

    public double getSide() {
        return side;
    }

    @Override
    public double getWidth() {
        return side;
    }

    @Override
    public double getHeight() {
        return side;
    }

    @Override
    public double getArea() {
        return side * side;
    }

    @Override
    public double getPerimeter() {
        return 4 * side;
    }

    @Override
    public String toString() {
        return "Square [side = " + side + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Square square = (Square) o;
        return Double.compare(side, square.side) == 0;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        return prime * hash + Double.hashCode(side);
    }
}