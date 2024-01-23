package model;

public abstract class MusicalInstrument implements IFixable, IPlayable, Comparable<MusicalInstrument> {
    private double price;

    public MusicalInstrument(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public abstract String makeSound();

    public abstract String getPitchType();

    @Override
    public int compareTo(MusicalInstrument other) {
        return Double.compare(this.price, other.price);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
