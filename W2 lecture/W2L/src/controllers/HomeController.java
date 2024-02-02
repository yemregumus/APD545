package controllers;

import java.util.Date;

import models.Circle;
import models.GeometricObject;
import views.CView;
import views.GView;

public class HomeController {

    private Circle cir;
    private GeometricObject goem;

    private CView cv;
    private GView gv;

    public HomeController(Circle c, GeometricObject goem, CView cv, GView gv) {
        this.cir = c;
        this.goem = goem;
        this.cv = cv;
        this.gv = gv;
    }

    public String getShapeColor() {
        return goem.getColor();
    }

    public void setShapeColor(String c) {
        goem.setColor(c);
    }

    public boolean getShapeisFilled() {
        return goem.isFilled();
    }

    public void setShapeisFilled(boolean isFilled) {
        goem.setFilled(isFilled);
    }

    public Date getShapeDate(Date date) {
        return goem.getDateCreated();
    }

    public void setShapeDate(Date d) {
        goem.setDateCreated(d);
    }

    public void setCRadius(double radius) {
        cir.setRadius(radius);
    }

    public double getCRadius() {
        return cir.getRadius();
    }

    public void printCicle() {
        cv.printCircle(cir.getDateCreated(), cir.getRadius());
    }
}
