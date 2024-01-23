package model;

public class Xylophone extends PercussionFamily {
    public Xylophone(double price) {
        super(price);
    }

    // Implement abstract methods for xylophone
    @Override
    public String makeSound() {
        return "through resonators";
    }

    @Override
    public String getPitchType() {
        return "Each bar produces different pitch";
    }

    @Override
    public String howToPlay() {
        return "with two mallets";
    }

    @Override
    public String howToFix() {
        return "replace bars";
    }
}
