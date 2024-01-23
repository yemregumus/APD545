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

package view;

import model.*;

import java.util.Scanner;

public class MusicalInstrumentView {
    private Scanner scanner;

    public MusicalInstrumentView(Scanner scanner) {
        this.scanner = scanner;
    }

    public double getInstrumentPrice(String instrumentName) {
        System.out.print("Enter the price for " + instrumentName + ": ");
        return scanner.nextDouble();

    }

    public void displayInstrumentInfo(MusicalInstrument instrument) {
        System.out.println();
        System.out.println("--: Requirement 2 :--");
        System.out.println("The most expensive instrument is: " + instrument);
        System.out.println(instrument + "’s cost is: $" + instrument.getPrice());
        System.out.println(instrument + " is played: " + instrument.howToPlay());
        System.out.println(instrument + " fixing: " + instrument.howToFix());
        System.out.println(instrument + " pitch type: " + instrument.getPitchType());
        System.out.println();
    }

    public void sortDescendingToPrice(MusicalInstrument[] instruments) {
        System.out.println("--: Requirement 3 :--");
        System.out.println("Instruments in price descending order:");
        for (MusicalInstrument instrument : instruments) {
            System.out.print("[" + instrument + "]");
        }
        System.out.println();
    }

    public void displayInstrumentFamilyInfo(MusicalInstrumentModel model, Scanner scanner) {
        System.out.println();
        System.out.print("--: Requirement 4 :--");
        System.out.print("\nEnter an instrument family: ");
        String familyName = scanner.next();

        switch (familyName.toLowerCase()) {
            case "string":
                displayFamilyInfo(new StringFamily[]{new Guitar(0), new Harp(0)});
                break;
            case "percussion":
                displayFamilyInfo(new PercussionFamily[]{new Drum(0), new Xylophone(0)});
                break;
            case "woodwind":
                displayFamilyInfo(new WoodwindFamily[]{new Flute(0)});
                break;
            default:
                System.out.println("Invalid family name");
        }
    }

    public void displayFamilyInfo(MusicalInstrument[] instruments) {
        for (MusicalInstrument instrument : instruments) {
            System.out.println(instrument + " makes sound " + instrument.makeSound() + ".");
        }
    }
}
