/**********************************************
 Workshop #
 Course: APD545
 Semester: 5th Semester
 Last Name: Gumus
 First Name: Yunus Emre
 ID: 150331197
 Section: NAA

 This assignment represents my own work in accordance with Seneca Academic Policy.

 Signature: Y.E.G.
 Date: 23/01/2024
 **********************************************/

package controller;

import model.*;
import view.MusicalInstrumentView;

import java.util.Arrays;
import java.util.Scanner;

public class MusicalInstrumentPresenter {
    private MusicalInstrumentModel model;
    private MusicalInstrumentView view;

    public MusicalInstrumentPresenter(MusicalInstrumentModel model, MusicalInstrumentView view) {
        this.model = model;
        this.view = view;
    }

    public void run() {
        System.out.println("--: Requirement 1 :--");
        Drum drum = new Drum(view.getInstrumentPrice("Drum"));
        Flute flute = new Flute(view.getInstrumentPrice("Flute"));
        Guitar guitar = new Guitar(view.getInstrumentPrice("Guitar"));
        Harp harp = new Harp(view.getInstrumentPrice("Harp"));
        Xylophone xylophone = new Xylophone(view.getInstrumentPrice("Xylophone"));


        MusicalInstrument mostExpensive = model.getMostExpensiveInstrument(drum,flute,guitar,harp,xylophone);
        view.displayInstrumentInfo(mostExpensive);

        MusicalInstrument[] instruments = model.getAllInstruments();
        Arrays.sort(instruments);
        view.sortDescendingToPrice(instruments);

        view.displayInstrumentFamilyInfo(model, new Scanner(System.in));
    }
}
