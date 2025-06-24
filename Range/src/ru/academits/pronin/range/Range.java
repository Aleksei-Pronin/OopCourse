package ru.academits.pronin.range;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getRangeLength() {
        return this.to - this.from;
    }

    public boolean isInside(double number) {
        return number >= from && number <= to;
    }

    public Range getIntersection(Range range) {
        if (this.getTo() <= range.getFrom()) {
            return null;
        }

        return new Range(range.getFrom(), this.getTo());
    }

    public Range[] getUnion(Range range) {
        if (this.getTo() >= range.getFrom()) {
            return new Range[]{new Range(this.getFrom(), range.getTo())};
        }

        return new Range[]{this, range};
    }

    public Range[] getDifference(Range range) {
        if (this.getFrom() >= range.getFrom() && this.getTo() <= range.getTo()) {
            return new Range[0];
        }

        if (this.getFrom() < range.getFrom() && this.getTo() > range.getTo()) {
            return new Range[]{new Range(this.getFrom(), range.getFrom()), new Range(range.getTo(), this.getTo())};
        }

        if (this.getFrom() >= range.getTo() || this.getTo() <= range.getFrom()) {
            return new Range[]{this};
        }

        return (this.getFrom() < range.getFrom()) ?
                new Range[]{new Range(this.getFrom(), range.getFrom())} :
                new Range[]{new Range(range.getTo(), this.getTo())};
    }

    @Override
    public String toString() {
        return "[" + this.getFrom() + ", " + this.getTo() + "]";
    }
}