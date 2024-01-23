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

package main;

import controller.MusicalInstrumentPresenter;
import model.MusicalInstrumentModel;
import view.MusicalInstrumentView;

import java.util.Scanner;

public class MusicalInstrumentMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MusicalInstrumentModel model = new MusicalInstrumentModel();
        MusicalInstrumentView view = new MusicalInstrumentView(scanner);
        MusicalInstrumentPresenter presenter = new MusicalInstrumentPresenter(model, view);

        presenter.run();

        scanner.close();
    }
}
