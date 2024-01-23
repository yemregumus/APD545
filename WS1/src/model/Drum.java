package model;

public class Drum extends PercussionFamily {
    public Drum(double price) {
        super(price);
    }

    @Override
    public String makeSound() {
        return "vibrating stretched membrane";
    }

    @Override
    public String getPitchType() {
        return "Sonic pitch";
    }

    @Override
    public String howToPlay() {
        return "by hitting the membrane";
    }

    @Override
    public String howToFix() {
        return "replace the membrane";
    }
}


