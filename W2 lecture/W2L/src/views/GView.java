package views;

import java.util.Date;

public class GView {

	public void printGO(Date date, String color, boolean isFilled) {
		System.out.println("G O details");
		System.out.println("Color is "+color);
		System.out.println("Is Shape filled with color ? "+isFilled);
		System.out.println("The date on which the shape is created "+ date);
	}
}
