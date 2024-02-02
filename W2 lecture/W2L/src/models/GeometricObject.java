package models;

import java.util.Date;//available for the whole class file
//import java.sql.Date;

public abstract class GeometricObject{ //extends Object{
	private String color = "white";
	private boolean filled;
	//private java.sql.Date dateCreated; //in line declaration
	//private Date date1;
	//private Date date2;
	private Date dateCreated;
	
	public GeometricObject() {
		dateCreated = new Date();
	}

	public GeometricObject(String color, boolean filled) {
		//super();//calling the default constructor of the parent class of GO
		this.color = color;
		this.filled = filled;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public boolean isFilled() {
		return filled;
	}

	public void setFilled(boolean filled) {
		this.filled = filled;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	public abstract double getArea();
	
}
