package main;

import controllers.HomeController;
import models.Circle;
import models.GeometricObject;
import views.CView;
import views.GView;

public class Main {

	public static void main(String[] args) {
		Circle cirM = CircleCreation();
		CView cv = new CView(cirM);
		
		GeometricObject gM =  CircleCreation();
		GView gv = new GView();
		
		HomeController hc = new HomeController(cirM, gM, cv, gv);
		hc.printCicle();
		System.out.println("A Circle color is " + hc.getShapeColor());
		System.out.println("The radius of the circle " + hc.getCRadius());
		
		Circle circ = CircleCreation(4, "Purple");
		HomeController hcc = new HomeController(circ, gM, cv, gv);
		hcc.printCicle();
		System.out.println("A Circle color is " + hcc.getShapeColor());
		System.out.println("The radius of the circle " + hcc.getCRadius());
	}
	
	private static Circle CircleCreation(double i, String color) {
		// TODO Auto-generated method stub
		Circle cir = new Circle(i, color);
		return cir;
	}

	private static Circle CircleCreation() {
		Circle cir = new Circle(1);
		return cir;
	}
}
