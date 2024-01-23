package model;

public class Harp extends StringFamily {
    public Harp(double price) {
        super(price);
    }

    @Override
    public String makeSound() {
        return "vibrating strings";
    }

    @Override
    public String getPitchType() {
        return "Has seven levels of pitch";
    }

    @Override
    public String howToPlay() {
        return "with the thumb and first three fingers";
    }

    @Override
    public String howToFix() {
        return "replace the strings";
    }
}