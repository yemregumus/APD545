package views;

import java.util.Date;

import models.Circle;

public class CView {

	//private GeometricObject go = new GeometricObject();
	private Circle cir = new Circle();
	public CView(Circle cir) {
		this.cir = cir;
	}
	
	public void printCircle(Date date, double radius) {
		System.out.println("The circle is created " + cir.getDateCreated() + 
				" and the radius is " + radius);
	}
}
