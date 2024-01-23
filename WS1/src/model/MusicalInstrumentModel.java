package model;

import java.util.Arrays;

public class MusicalInstrumentModel {
    private MusicalInstrument[] instruments;

    public MusicalInstrumentModel() {
        instruments = new MusicalInstrument[]{
                new Drum(0),
                new Xylophone(0),
                new Flute(0),
                new Guitar(0),
                new Harp(0)

        };
    }

    public MusicalInstrument[] getAllInstruments() {
        Arrays.sort(instruments);
        return instruments;
    }

    public MusicalInstrument getMostExpensiveInstrument(MusicalInstrument... instruments) {
        return Arrays.stream(instruments)
                .max(MusicalInstrument::compareTo)
                .orElse(null);
    }

    public MusicalInstrument[] getInstrumentsByFamily(String familyName) {
        return Arrays.stream(instruments)
                .filter(instrument -> instrument.getClass().getSimpleName().toLowerCase().contains(familyName.toLowerCase()))
                .toArray(MusicalInstrument[]::new);
    }
}
