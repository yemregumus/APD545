package models;

public class Circle extends GeometricObject{

	private double radius;
	
	public Circle() {}
	public Circle(double radius) {
		this.radius = radius;
	}

	public Circle(String color, boolean filled, double radius) {
		super(color, filled);
		this.radius = radius;
	}

	public Circle(double radius, String color) {
		super();
		this.radius = radius;
		setColor(color);
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}
	public double getDiameter() {
		return 2 * radius;
	}
	
	public double getPerimeter() {
		return 2 * radius * Math.PI;
	}
	@Override
	public double getArea() {
		// TODO Auto-generated method stub
		return 0;
	}
}
